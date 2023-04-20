package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class Paper {

    private  int id;

    private int testId;

    private String testName;

    private int questionId;

    private int score;

    private int teacherId;

    private int classId;

    // 1未发布  2 已发布
    private int status;

    private int time;


}
