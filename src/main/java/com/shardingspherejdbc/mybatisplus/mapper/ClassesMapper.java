package com.shardingspherejdbc.mybatisplus.mapper;

import com.shardingspherejdbc.mybatisplus.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import com.shardingspherejdbc.mybatisplus.dto.SelectClassResultDto;
import com.shardingspherejdbc.mybatisplus.dto.SelectClassParamDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
public interface ClassesMapper extends BaseMapper<Classes> {

    List<SelectClassResultDto> selectClass(SelectClassParamDto params);
}
