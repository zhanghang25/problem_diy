package com.shardingspherejdbc.mybatisplus.dto.questions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SendOtherInfo {
    private String allList;

    private String testName;

    private Integer time;

    private String testId;

    private Integer teacherId;

    private Integer classId;
}
