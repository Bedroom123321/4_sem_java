package com.myapp.transportlogistics.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTask {

    private String id;
    private String status;
    private String filePath;

    public LogTask() {
    }

}
