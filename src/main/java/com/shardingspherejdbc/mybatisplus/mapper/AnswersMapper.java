package com.shardingspherejdbc.mybatisplus.mapper;

import com.shardingspherejdbc.mybatisplus.entity.Answers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.shardingspherejdbc.mybatisplus.dto.questions.HasPapersResultDto;
import com.shardingspherejdbc.mybatisplus.dto.AvgScoreResultDto;
import com.shardingspherejdbc.mybatisplus.dto.ErrorKeywordResultDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
public interface AnswersMapper extends BaseMapper<Answers> {

    List<HasPapersResultDto> hasPapers(@Param("studentId") String studentId, @Param("classId") String classId);

    List<AvgScoreResultDto> avgScore(@Param("testId") String testId);

    List<ErrorKeywordResultDto> errorKeyword(@Param("testId") String testId);
}
