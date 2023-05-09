package com.shardingspherejdbc.mybatisplus.dto;
import java.util.Date;
import lombok.Data;
/**
 * mapper.AnswersMapper.errorKeyword的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-05-09
 */
@Data
public class ErrorKeywordResultDto {

    private Integer id;

    private Integer questionId;

    private Integer testId;

    private Integer studentId;

    private Date startTime;

    private Date endTime;

    private String stuAnswerContent;

    private String answerContent;

    private Integer score;

    private Integer status;

    private Integer getScore;

    private Integer time;

}
