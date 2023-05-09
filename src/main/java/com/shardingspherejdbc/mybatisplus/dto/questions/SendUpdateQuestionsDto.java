package com.shardingspherejdbc.mybatisplus.dto.questions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendUpdateQuestionsDto {
    private String type;

    private Integer id;

    private String source;

    private String keywords;

    private String questionDescribe;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private int score;

    private String order;

    private String answerContent;

}
