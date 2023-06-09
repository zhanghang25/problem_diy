package com.shardingspherejdbc.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.entity.Students;
import org.apache.ibatis.jdbc.Null;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IStudentsService;
import com.shardingspherejdbc.mybatisplus.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;

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
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private IStudentsService iStudentsService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Students>> list(@RequestParam(required = false) Integer current,
            @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Students> aPage = iStudentsService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }


    @NotNull
    private QueryWrapper<Students> getQueryWrapper(Map<String, String> headers) {
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", headers.get("x-wx-openid"));
        return wrapper;
    }

    @GetMapping(value = "/myInfo")
    public ResponseEntity<Students> getInfo(@RequestHeader Map<String,String> headers){
        QueryWrapper<Students> wrapper = getQueryWrapper(headers);
        Students one = iStudentsService.getOne(wrapper);
        return new ResponseEntity<>(one,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Students> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iStudentsService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Students params, @RequestHeader Map<String, String> headers) {
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", headers.get("x-wx-openid"));
        if (iStudentsService.getOne(wrapper) != null) {
            return new ResponseEntity<>("已经注册过了", HttpStatus.OK);

        }
        params.setOpenId(headers.get("x-wx-openid"));

        iStudentsService.save(params);

        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iStudentsService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Students params) {
        iStudentsService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
