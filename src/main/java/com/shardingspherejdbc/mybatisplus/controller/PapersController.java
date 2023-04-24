package com.shardingspherejdbc.mybatisplus.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IPapersService;
import com.shardingspherejdbc.mybatisplus.entity.Papers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/papers")
public class PapersController {


    @Autowired
    private IPapersService iPapersService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Papers>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Papers> aPage = iPapersService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Papers> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iPapersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Papers params) {
        iPapersService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iPapersService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Papers params) {
        iPapersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}