package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.service.impl.LogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("status")
    public ResponseEntity<String> getLogStatus(Long id) {
        return logServiceImpl.getStatus(id);
    }

    @GetMapping("download")
    public List<String> getLogFile(@RequestParam @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Дата должна быть в формате yyyy-MM-dd") String date) {
        return logServiceImpl.getLogs(date);
    }

}
