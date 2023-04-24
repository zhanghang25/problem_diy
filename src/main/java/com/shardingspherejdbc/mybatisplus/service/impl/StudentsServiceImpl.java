package com.shardingspherejdbc.mybatisplus.service.impl;

import com.shardingspherejdbc.mybatisplus.entity.Students;
import com.shardingspherejdbc.mybatisplus.mapper.StudentsMapper;
import com.shardingspherejdbc.mybatisplus.service.IStudentsService;
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
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements IStudentsService {

}
