package com.shardingspherejdbc.mybatisplus.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shardingspherejdbc.mybatisplus.dto.questions.SendOtherInfo;
import com.shardingspherejdbc.mybatisplus.dto.questions.SendQuestionsDto;
import com.shardingspherejdbc.mybatisplus.entity.Keywords;
import com.shardingspherejdbc.mybatisplus.entity.Papers;
import com.shardingspherejdbc.mybatisplus.service.IKeywordsService;
import com.shardingspherejdbc.utils.Utils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shardingspherejdbc.mybatisplus.service.IPapersService;
import com.shardingspherejdbc.mybatisplus.service.IQuestionsService;
import com.shardingspherejdbc.mybatisplus.entity.Questions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private IQuestionsService iQuestionsService;

    @Autowired
    private IPapersService iPapersService;

    @Autowired
    private IKeywordsService iKeywordsService;

    public String handleKeyword(String keywords) {
        String[] arr = keywords.split(",");
        List<String> tmp_id = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LambdaQueryWrapper<Keywords> wrapper = new LambdaQueryWrapper<>();
            Keywords one = iKeywordsService.getOne(wrapper.eq(Keywords::getKeywords, arr[i]));
            if (one == null) {
                boolean save = iKeywordsService.save(new Keywords(arr[i]));
                if (save) {
                    one = iKeywordsService.getOne(wrapper.eq(Keywords::getKeywords, arr[i]));
                }
            }
            boolean add = tmp_id.add(one.getId() + "");

        }
        String join = String.join(",", tmp_id);
        return join;
    }

    public Questions convert(SendQuestionsDto dto) {

        Questions questions = new Questions();
        questions.setQuestionDescribe(dto.getQuestionDescribe());
        questions.setAnswerContent(dto.getAnswerContent());
        questions.setKeywords(this.handleKeyword(dto.getKeywords()));

        questions.setType(dto.getType());
        System.out.println(dto.toString());
        System.out.println(dto.getType());
        System.out.println(dto.getType() == "1");
        if (dto.getType().equals("1")) {
            questions.setOtherAnswer(
                    dto.getOptionA() + "," + dto.getOptionB() + "," + dto.getOptionC() + "," + dto.getOptionD());
            questions.setDisorder("1");

        } else {
            questions.setDisorder(dto.getOrder());
        }
        questions.setSource(dto.getSource());
        questions.setScore(dto.getScore());

        return questions;
    }

    @PostMapping(value = "/sendQuestions")
    public ResponseEntity<Object> sendQuestions(@RequestHeader Map<String, String> headers,
            @RequestBody SendOtherInfo params) {
        System.out.println("222222");
        System.out.println(params.getAllList());
        List<SendQuestionsDto> sendList = JSONUtil.parseArray(params.getAllList()).toList(SendQuestionsDto.class);
        System.out.println(sendList);
        List<Questions> collect = sendList.stream().map(this::convert).collect(Collectors.toList());
        System.out.println(collect);

        collect.stream().forEach(item -> {
            System.out.println("@@@@3333");
            System.out.println(item);
            boolean save = iQuestionsService.save(item);
            System.out.println(save);
            if (save) {
                System.out.println(item.getId());
                Papers papers = new Papers();
                papers.setTeacherId(params.getTeacherId());
                papers.setClassId(params.getClassId());
                papers.setTime(params.getTime());
                papers.setTestName(params.getTestName());
                papers.setTestId(params.getTestId());
                papers.setStatusId(1);
                papers.setQuestionId(item.getId());
                papers.setScore(item.getScore());
                iPapersService.save(papers);
            }
        });

        return new ResponseEntity<>("Success1", HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Questions>> list(@RequestParam(required = false) Integer current,
            @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Questions> aPage = iQuestionsService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Questions> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iQuestionsService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Questions params) {
        iQuestionsService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iQuestionsService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Questions params) {
        iQuestionsService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
