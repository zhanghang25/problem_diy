package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class Question {
    private int id;

    private String questionDescribe;

    // 1代表选择 2代表填空
    private int type;

    private String answerContent;

    private String otherAnswer;

    // 1代表非乱序  2代表乱序
    private int disorder;

    private String key;

    // 1题库  2用户
    private int source;

}
