package com.shardingspherejdbc.mybatisplus.dto;

import lombok.Data;

@Data
public class AvgAndKeyword {
    private Double avg;

    private String keyword;

    private Integer allScore;
}
