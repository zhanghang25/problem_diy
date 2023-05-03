package com.shardingspherejdbc.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.dto.studentClass.MyJoinClassParamDto;
import com.shardingspherejdbc.mybatisplus.dto.studentClass.MyJoinClassResultDto;
import com.shardingspherejdbc.mybatisplus.mapper.StudentclassMapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IStudentclassService;
import com.shardingspherejdbc.mybatisplus.entity.Studentclass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/studentclass")
public class StudentclassController {


    @Autowired
    private IStudentclassService iStudentclassService;

    @Autowired
    private StudentclassMapper studentclassMapper;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Studentclass>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Studentclass> aPage = iStudentclassService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @PostMapping(value = "/myClass")
    public ResponseEntity<List<MyJoinClassResultDto>> myClass(@RequestBody MyJoinClassParamDto params){
        List<MyJoinClassResultDto> myJoinClassResultDtos = studentclassMapper.myJoinClass(params);
        return new ResponseEntity<>(myJoinClassResultDtos,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Studentclass> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iStudentclassService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Studentclass params) {
        if(null == iStudentclassService.getOne(new QueryWrapper<Studentclass>().eq("class_id",params.getClassId()).eq("student_id",params.getStudentId()))){

        iStudentclassService.saveOrUpdate(params);
        }

        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iStudentclassService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Studentclass params) {
        iStudentclassService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
