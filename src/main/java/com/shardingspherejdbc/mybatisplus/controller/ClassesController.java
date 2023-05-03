package com.shardingspherejdbc.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.dto.SelectClassParamDto;
import com.shardingspherejdbc.mybatisplus.dto.SelectClassResultDto;
import com.shardingspherejdbc.mybatisplus.entity.Students;
import com.shardingspherejdbc.mybatisplus.entity.Teachers;
import com.shardingspherejdbc.mybatisplus.mapper.ClassesMapper;
import com.shardingspherejdbc.mybatisplus.service.ITeachersService;
import com.shardingspherejdbc.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IClassesService;
import com.shardingspherejdbc.mybatisplus.entity.Classes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private IClassesService iClassesService;

    @Autowired
    private ClassesMapper iClassesMapper;

    @Autowired
    private ITeachersService iTeachersService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Classes>> list(@RequestParam(required = false) Integer current,
            @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Classes> aPage = iClassesService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @NotNull
    private QueryWrapper<Classes> getQueryWrapper(Map<String, String> headers) {
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        // wrapper.eq("open_id", headers.get("x-wx-openid"));
        return wrapper;
    }

    @PostMapping(value = "/myClass")
    public ResponseEntity<List<Classes>> myTeacherClass(@RequestHeader Map<String,String> headers){
        Teachers teacher = iTeachersService.getOne(new QueryWrapper<Teachers>().eq("open_id", headers.get("x-wx-openid")));
        List<Classes> classes = iClassesService.list(new QueryWrapper<Classes>().eq("teacher_id", teacher.getId()));

        return new ResponseEntity<List<Classes>>(classes,HttpStatus.OK);
    }

    @PostMapping(value = "/teacherClass")
    public ResponseEntity<List<SelectClassResultDto>> teacherClass(@RequestBody SelectClassParamDto params,
            @RequestHeader Map<String, String> headers) {
//        QueryWrapper<Classes> queryWrapper = this.getQueryWrapper(headers);
//        queryWrapper.like("class_id", params.getClassId());
//        queryWrapper.like("class_name", params.getClassName());
//        queryWrapper.lambda().eq(Classes::getTeacherId, params.getTeacherId());
        List<SelectClassResultDto> selectClassResultDtos = iClassesMapper.selectClass(params);
        return new ResponseEntity<>(selectClassResultDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Classes> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iClassesService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Classes params, @RequestHeader Map<String, String> headers) {

        iClassesService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iClassesService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Classes params) {
        iClassesService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
