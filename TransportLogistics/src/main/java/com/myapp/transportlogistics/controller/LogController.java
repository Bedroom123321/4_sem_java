package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.exceprion.LogsException;
import com.myapp.transportlogistics.exceprion.ValidationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logs")
public class LogController {

    @GetMapping("get")
    public byte[] getLog(@RequestParam String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new ValidationException("Дата должна быть в формате yyyy-MM-dd");
        }

        String sourceLogFile = "logback/transportlogistics.log"; // общий файл логов
        String targetLogFileName = String.format("logback/transportlogistics_%s.log", date);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceLogFile))) {
            StringBuilder logs = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null && line.contains(date)) {
                logs.append(line).append(System.lineSeparator());
            }

            if (!logs.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(targetLogFileName))) {
                    writer.write(logs.toString());
                }
            } else {
                throw new LogsException("Логи за указанную дату отсутствуют");
            }

        } catch (IOException e) {
            throw new LogsException("Ошибка при обработке лог-файлов");
        }

        byte[] logFileBytes;
        try {
            logFileBytes = Files.readAllBytes(Path.of(targetLogFileName));
        } catch (IOException e) {
            throw new LogsException("Ошибка при чтении отфильтрованного лог-файла");
        }

        return logFileBytes;
    }
}
