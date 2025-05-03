package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.model.LogTask;

public interface LogFileService {

    void creatLogFileByDate(String date, LogTask task)  throws InterruptedException;
}
