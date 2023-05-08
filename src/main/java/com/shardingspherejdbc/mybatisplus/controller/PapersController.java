package com.shardingspherejdbc.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.dto.errors.MyErrorsResultDto;
import com.shardingspherejdbc.mybatisplus.dto.papers.GetMyPapersResultDto;
import com.shardingspherejdbc.mybatisplus.dto.questions.QueryQuestionsResultDto;
import com.shardingspherejdbc.mybatisplus.mapper.PapersMapper;
import com.shardingspherejdbc.mybatisplus.mapper.QuestionsMapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IPapersService;
import com.shardingspherejdbc.mybatisplus.entity.Papers;
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
@RequestMapping("/papers")
public class PapersController {


    @Autowired
    private IPapersService iPapersService;

    @Autowired
    private PapersMapper papersMapper;



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

    @GetMapping(value = "/getMyPapers")
    public ResponseEntity<List<GetMyPapersResultDto>> getMyPapers(@RequestParam String studentId){
        List<GetMyPapersResultDto> myPapers = papersMapper.getMyPapers(studentId);
        return new ResponseEntity<>(myPapers,HttpStatus.OK);
    }

    @GetMapping(value = "/queryQuestions")
    public ResponseEntity<List<QueryQuestionsResultDto>> queryQuestions(@RequestParam String testId){
        List<QueryQuestionsResultDto> list = papersMapper.queryQuestions(testId);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @GetMapping(value = "/myErrors")
    public ResponseEntity<List<MyErrorsResultDto>> myErrors(@RequestParam String studentId){
        List<MyErrorsResultDto> list = papersMapper.myErrors(studentId);
        return new ResponseEntity<>(list,HttpStatus.OK);

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

    @PostMapping(value = "/updateBatch")
    public ResponseEntity<Object> updateBatch(@RequestBody Papers params) {
        QueryWrapper<Papers> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Papers::getTestId,params.getTestId());
        iPapersService.update(params,wrapper);
//        iPapersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
