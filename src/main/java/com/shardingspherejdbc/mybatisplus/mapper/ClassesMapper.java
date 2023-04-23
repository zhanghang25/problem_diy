package com.shardingspherejdbc.mybatisplus.mapper;

import com.shardingspherejdbc.mybatisplus.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import com.shardingspherejdbc.mybatisplus.dto.SelectByClassIdResultDto;
import com.shardingspherejdbc.mybatisplus.dto.SelectByClassIdParamDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-04-23
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {

    List<SelectByClassIdResultDto> selectByClassId(SelectByClassIdParamDto params);
}
