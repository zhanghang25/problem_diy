package com.shardingspherejdbc.mybatisplus.mapper;

import com.shardingspherejdbc.mybatisplus.entity.Papers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.shardingspherejdbc.mybatisplus.dto.questions.QueryQuestionsResultDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
public interface PapersMapper extends BaseMapper<Papers> {

    List<QueryQuestionsResultDto> queryQuestions(@Param("testId") String testId);
}
