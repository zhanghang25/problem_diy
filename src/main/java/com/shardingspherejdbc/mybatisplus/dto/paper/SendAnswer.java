package com.shardingspherejdbc.mybatisplus.dto.paper;

import lombok.Data;

@Data
public class SendAnswer {
    private String listOfAnswers;

    private String testId;

    private Integer studentId;
}
