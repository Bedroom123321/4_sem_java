package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.exception.LogsException;
import com.myapp.transportlogistics.service.LogService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public List<String> creatLogsByDate(String date) {
        Path sourceLogPath = Path.of("logback", "transportlogistics.log");
        String dateLogFile = String.format("logback/transportlogistics-%s.log", date);

        if (!Files.exists(sourceLogPath)) {
            throw new LogsException("Исходный лог-файл не найден");
        }

        List<String> logs;
        try (Stream<String> logsStream = Files.lines(sourceLogPath)) {

            logs = logsStream
                .filter((log) -> log.contains(date))
                .toList();

        } catch (IOException e) {
            throw new LogsException("Ошибка при чтении общего лог-файла");
        }

        if (logs.isEmpty()) {
            throw new LogsException("Нет логов с такой датой: " + date);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateLogFile))) {
            for (String log : logs) {
                writer.write(log + "\n");
            }

        } catch (IOException e) {
            throw new LogsException("Ошибка при записи отфильтрованного лог-файла");
        }

        try {
            logs = Files.readAllLines(Path.of(dateLogFile));
        } catch (IOException e) {
            throw new LogsException("Ошибка при чтении отфильтрованного лог-файла");
        }

        return logs;
    }
}
