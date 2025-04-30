package com.myapp.transportlogistics.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface LogService {

    void creatLogsByDate(String date)  throws InterruptedException;

    List<String> getLogs(String date);

    ResponseEntity<String> getStatus(Long id);
}

