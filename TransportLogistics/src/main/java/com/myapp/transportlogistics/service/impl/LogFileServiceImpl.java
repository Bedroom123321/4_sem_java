package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.exception.LogsException;
import com.myapp.transportlogistics.model.LogTask;
import com.myapp.transportlogistics.service.LogFileService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogFileServiceImpl implements LogFileService {

    @Override
    @Async("taskExecutor")
    public void creatLogFileByDate(String date, LogTask task) throws InterruptedException {
        task.setStatus("Processing");
        String failedTaskStatus = "Failed";

        Thread.sleep(15000);
        Path sourceLogPath = Path.of("logback", "transportlogistics.log");
        String dateLogFile = String.format("logback/transportlogistics-%s.log", date);

        task.setFilePath(dateLogFile);

        if (!Files.exists(sourceLogPath)) {
            task.setStatus(failedTaskStatus);
            throw new LogsException("Исходный лог-файл не найден");
        }

        List<String> logs;
        try (Stream<String> logsStream = Files.lines(sourceLogPath)) {

            logs = logsStream
                    .filter((log) -> log.contains(date))
                    .toList();

        } catch (IOException e) {
            task.setStatus(failedTaskStatus);
            throw new LogsException("Ошибка при чтении общего лог-файла");
        }

        if (logs.isEmpty()) {
            task.setStatus(failedTaskStatus);
            throw new LogsException("Нет логов с такой датой: " + date);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateLogFile))) {
            for (String log : logs) {
                writer.write(log + "\n");
            }

        } catch (IOException e) {
            task.setStatus(failedTaskStatus);
            throw new LogsException("Ошибка при записи отфильтрованного лог-файла");
        }

        task.setStatus("Completed");

    }
}
