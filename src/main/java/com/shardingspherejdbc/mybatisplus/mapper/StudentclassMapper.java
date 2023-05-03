package com.shardingspherejdbc.mybatisplus.mapper;

import com.shardingspherejdbc.mybatisplus.entity.Studentclass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import com.shardingspherejdbc.mybatisplus.dto.studentClass.MyJoinClassResultDto;
import com.shardingspherejdbc.mybatisplus.dto.studentClass.MyJoinClassParamDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
public interface StudentclassMapper extends BaseMapper<Studentclass> {

    List<MyJoinClassResultDto> myJoinClass(MyJoinClassParamDto params);
}
