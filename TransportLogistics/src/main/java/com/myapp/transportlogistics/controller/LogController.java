package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.exceprion.LogsException;
import com.myapp.transportlogistics.exceprion.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Log Controller")
@RestController
@RequestMapping("logs")
public class LogController {

    @Operation(summary = "Извлекает логи за определенное число всех",
            description = "Принимает дату, формирует файл .log за "
                    + "эту дату и возвращает логи из этого файла"
    )
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

            boolean hasLogs = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(date)) {
                    logs.append(line).append(System.lineSeparator());
                    hasLogs = true;
                }
            }

            if (!hasLogs) {
                throw new LogsException("Логи за указанную дату отсутствуют");
            }

            if (!logs.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(targetLogFileName))) {
                    writer.write(logs.toString());
                }
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
