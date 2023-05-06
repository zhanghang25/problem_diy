package com.shardingspherejdbc.mybatisplus.dto.classes;
/**
 * mapper.ClassesMapper.classPapers的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-05-06
 */
public class ClassPapersResultDto {

    private String testName;

    private String testId;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }


    @Override
    public String toString() {
        return "ClassPapersResultDto{" +
        ", testName=" + testName +
        ", testId=" + testId +
        "}";
    }
}
