package com.shardingspherejdbc.mybatisplus.service.impl;

import com.shardingspherejdbc.mybatisplus.entity.Questions;
import com.shardingspherejdbc.mybatisplus.mapper.QuestionsMapper;
import com.shardingspherejdbc.mybatisplus.service.IQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements IQuestionsService {

}
