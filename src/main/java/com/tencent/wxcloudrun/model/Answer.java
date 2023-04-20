package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class Answer implements Serializable {

    private int id ;

    private int questionId;

    private int testId;

    private int studentId;

    private LocalTime startTime;

    private LocalTime endTime;

    private String stuAnswerContent;

    private String answerContent;

    private int score;

    // 1 对  2 错
    private int status;

    private int getScore;

    private int time;
}
