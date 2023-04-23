package com.shardingspherejdbc.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.shardingspherejdbc.mybatisplus.mapper"})
public class WxTest {

  public static void main(String[] args) {
    SpringApplication.run(WxTest.class, args);
  }
}
