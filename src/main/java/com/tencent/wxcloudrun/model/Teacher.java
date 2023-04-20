package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class Teacher {
    private int id;

    private int teacherId;

    private String teacherName;

    private String wechatId;

    private String teacherTel;
}
