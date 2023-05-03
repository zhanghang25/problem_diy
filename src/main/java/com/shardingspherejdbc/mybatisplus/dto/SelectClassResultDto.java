package com.shardingspherejdbc.mybatisplus.dto;
/**
 * mapper.ClassesMapper.selectClass的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-04-30
 */
public class SelectClassResultDto {

    private Integer id;

    private String className;

    private String classId;

    private Integer testId;

    private String testName;

    private Long paperCount;

    private Integer time;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    private Integer statusId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Long getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Long paperCount) {
        this.paperCount = paperCount;
    }


    @Override
    public String toString() {
        return "SelectClassResultDto{" +
        ", id=" + id +
        ", className=" + className +
        ", classId=" + classId +
        ", testId=" + testId +
        ", testName=" + testName +
        ", paperCount=" + paperCount +
        "}";
    }
}
