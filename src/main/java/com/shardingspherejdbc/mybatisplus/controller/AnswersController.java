package com.shardingspherejdbc.mybatisplus.controller;

import com.shardingspherejdbc.mybatisplus.entity.Questions;
import com.shardingspherejdbc.mybatisplus.service.IQuestionsService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IAnswersService;
import com.shardingspherejdbc.mybatisplus.entity.Answers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/answers")
public class AnswersController {


    @Autowired
    private IAnswersService iAnswersService;

    @Autowired
    private IQuestionsService iQuestionsService;

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Answers>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Answers> aPage = iAnswersService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Answers> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iAnswersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Answers params) {
        handleAnswer(params);
        iAnswersService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    private void handleAnswer(Answers params) {

        Questions question = iQuestionsService.getById(params.getQuestionId());
        if (isRight(params, question)) {
            params.setStatus(1);
            params.setGetScore(params.getScore());
        } else {
            params.setStatus(0);
            params.setGetScore(0);
        }


    }

    private boolean isRight(Answers params, Questions question) {
        String answerContent = question.getAnswerContent();
        String[] answers = answerContent.split(",");
        String answerContent2 = params.getStuAnswerContent();
        String[] answers2 = answerContent2.split(",");
        Arrays.sort(answers);
        Arrays.sort(answers2);

        return Arrays.equals(answers, answers2);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iAnswersService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Answers params) {
        iAnswersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
