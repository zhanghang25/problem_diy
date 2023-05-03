package com.shardingspherejdbc.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.entity.Students;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Random;

public class Utils {
  public String getOpenId(Map<String, String> headers) {
    return headers.get("x-wx-openid");
  }
  private static final String BASIC = "123456789qwertyuiopasdfghjklzxcvbnm";

  public static String random(){
    char[] basicArray = BASIC.toCharArray();
    Random random = new Random();
    char[] result = new char[6];
    for (int i = 0; i < result.length; i++) {
      int index = random.nextInt(100) % (basicArray.length);
      result[i] = basicArray[index];
    }
    return new String(result);
  }
}
