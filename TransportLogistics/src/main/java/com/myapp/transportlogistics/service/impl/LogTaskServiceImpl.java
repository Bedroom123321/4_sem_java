package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.exception.LogsException;
import com.myapp.transportlogistics.model.LogTask;
import com.myapp.transportlogistics.service.LogTaskService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class LogTaskServiceImpl implements LogTaskService {

    private final Map<String, LogTask> tasks = new HashMap<>();
    private final LogFileServiceImpl logFileServiceImpl;

    public LogTaskServiceImpl(LogFileServiceImpl logFileServiceImpl) {
        this.logFileServiceImpl = logFileServiceImpl;
    }

    @Override
    public String generateLogFile(String date) throws InterruptedException {
        LogTask task = new LogTask();
        String taskId = UUID.randomUUID().toString();
        task.setId(taskId);

        logFileServiceImpl.creatLogFileByDate(date, task);

        tasks.put(taskId, task);

        return taskId;

    }

    @Override
    @Transactional
    public String getStatus(String taskId) {
        return tasks.get(taskId).getStatus();
    }

    @Override
    @Transactional
    public List<String> getLogs(String taskId) {

        Path dateLogFile = Path.of(tasks.get(taskId).getFilePath());
        if (!Files.exists(dateLogFile)) {
            throw new LogsException("Лог-файл за такую дату не найден");
        }

        List<String> logs;

        try {
            logs = Files.readAllLines(dateLogFile);
        } catch (IOException e) {
            throw new LogsException("Ошибка при чтении лог-файла по дате");
        }

        return logs;
    }
}
