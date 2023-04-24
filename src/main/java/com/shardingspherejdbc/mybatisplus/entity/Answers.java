package com.shardingspherejdbc.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Time;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@Getter
@Setter
public class Answers implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer questionId;
    private Integer testId;
    private Integer studentId;
    private Time startTime;
    private Time endTime;
    private String stuAnswerContent;
    private String answerContent;
    private Integer score;
    private Short status;
    private Integer getScore;
    private Integer time;
}
