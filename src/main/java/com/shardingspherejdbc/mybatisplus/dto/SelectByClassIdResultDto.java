package com.shardingspherejdbc.mybatisplus.dto;
import java.util.Date;
import lombok.Data;
/**
 * mapper.ClassesMapper.selectByClassId的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-04-23
 */
@Data
public class SelectByClassIdResultDto {

    private Integer id;

    private Integer classId;

    private String className;

    private Integer teacherId;

    private Date createdAt;

    private Date updatedAt;

}
