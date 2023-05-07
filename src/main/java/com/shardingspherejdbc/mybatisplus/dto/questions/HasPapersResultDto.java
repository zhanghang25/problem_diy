package com.shardingspherejdbc.mybatisplus.dto.questions;
import java.math.BigDecimal;
import lombok.Data;
/**
 * mapper.AnswersMapper.hasPapers的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-05-07
 */
@Data
public class HasPapersResultDto {

    private String testName;

    private String testId;

    private Integer studentId;

    private BigDecimal yourScore;

    private BigDecimal officeScore;

}
