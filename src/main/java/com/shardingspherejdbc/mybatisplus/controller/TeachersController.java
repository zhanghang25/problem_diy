package com.shardingspherejdbc.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.ITeachersService;
import com.shardingspherejdbc.mybatisplus.entity.Teachers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/teachers")
public class TeachersController {


    @Autowired
    private ITeachersService iTeachersService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Teachers>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize,@RequestHeader Map<String,String> headers) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        QueryWrapper<Teachers> queryWrapper = getQueryWrapper(headers);


        Page<Teachers> aPage = iTeachersService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/myInfo")
    public ResponseEntity<Teachers> getInfo(@RequestHeader Map<String,String> headers){
        QueryWrapper<Teachers> wrapper = getQueryWrapper(headers);
        Teachers one = iTeachersService.getOne(wrapper);
        return new ResponseEntity<>(one,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Teachers> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iTeachersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Teachers params, @RequestHeader Map<String,String> headers) {
        QueryWrapper<Teachers> wrapper = getQueryWrapper(headers);
        if (iTeachersService.getOne(wrapper) != null) {
            return new ResponseEntity<>("已经注册过了", HttpStatus.OK);

        }
        System.out.println(headers.get("x-wx-openid"));
        System.out.println(headers);
        params.setOpenId(headers.get("x-wx-openid"));

        iTeachersService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @NotNull
    private QueryWrapper<Teachers> getQueryWrapper(Map<String, String> headers) {
        QueryWrapper<Teachers> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", headers.get("x-wx-openid"));
        return wrapper;
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iTeachersService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Teachers params) {
        iTeachersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
