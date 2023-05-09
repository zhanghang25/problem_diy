package com.shardingspherejdbc.mybatisplus.dto;
import java.math.BigDecimal;
/**
 * mapper.AnswersMapper.avgScore的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-05-09
 */
public class AvgScoreResultDto {

    private BigDecimal factScore;

    private BigDecimal rightScore;

    public BigDecimal getFactScore() {
        return factScore;
    }

    public void setFactScore(BigDecimal factScore) {
        this.factScore = factScore;
    }

    public BigDecimal getRightScore() {
        return rightScore;
    }

    public void setRightScore(BigDecimal rightScore) {
        this.rightScore = rightScore;
    }


    @Override
    public String toString() {
        return "AvgScoreResultDto{" +
        ", factScore=" + factScore +
        ", rightScore=" + rightScore +
        "}";
    }
}
