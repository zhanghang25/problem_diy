package com.shardingspherejdbc.mybatisplus.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IKeywordsService;
import com.shardingspherejdbc.mybatisplus.entity.Keywords;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/keywords")
public class KeywordsController {


    @Autowired
    private IKeywordsService iKeywordsService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Keywords>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Keywords> aPage = iKeywordsService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "getValue")
    public ResponseEntity<List<String>> getValue(@RequestParam String ids){
        List<String> list = new ArrayList<>();

        if(ids.contains("，") ){

          list =  Arrays.asList(ids.split("，"));
        } else {

           list =  Arrays.asList(ids.split(","));
        }
        List<String> collect = list.stream().map(i -> {
            String keywords = iKeywordsService.getById(Integer.parseInt(i)).getKeywords();
            return keywords;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(collect,HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Keywords> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iKeywordsService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Keywords params) {
        iKeywordsService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iKeywordsService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Keywords params) {
        iKeywordsService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
