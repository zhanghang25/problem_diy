package com.shardingspherejdbc.mybatisplus.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IClassesService;
import com.shardingspherejdbc.mybatisplus.entity.Classes;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-23
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {


    @Autowired
    private IClassesService iClassesService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Classes>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
//        System.out.println();
        Page<Classes> aPage = iClassesService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Classes> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iClassesService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Classes params) {
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
