package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.service.impl.LogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Log Controller")
@RestController
@RequestMapping("logs")
public class LogController {

    private final LogServiceImpl logServiceImpl;

    public LogController(LogServiceImpl logServiceImpl) {
        this.logServiceImpl = logServiceImpl;
    }

    @Operation(summary = "Извлекает логи за определенное число всех",
            description = "Принимает дату, формирует файл .log за "
                    + "эту дату и возвращает логи из этого файла"
    )
    @GetMapping("get")
    public byte[] getLogs(@RequestParam @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Дата должна быть в формате yyyy-MM-dd") String date) {
        return logServiceImpl.creatLogsByDate(date);
    }
}
