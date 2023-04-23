package com.shardingspherejdbc.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-04-23
 */
@Getter
@Setter
@TableName(value = "Classes")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer classId;
    private String className;
    private Integer teacherId;
    @TableField(value = "createdAt")
    private Date createdAt;
    @TableField(value = "updatedAt")
    private Date updatedAt;
}
