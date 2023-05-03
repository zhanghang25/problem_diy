package com.shardingspherejdbc.mybatisplus.dto;
/**
 * 该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-04-30
 */
public class SelectClassParamDto {

    private String teacherId;

    private String classId;

    private String className;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public String toString() {
        return "SelectClassParamDto{" +
        ", teacherId=" + teacherId +
        ", classId=" + classId +
        ", className=" + className +
        "}";
    }
}
