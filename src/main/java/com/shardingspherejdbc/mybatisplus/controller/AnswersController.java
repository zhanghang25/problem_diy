package com.shardingspherejdbc.mybatisplus.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingspherejdbc.mybatisplus.dto.AvgAndKeyword;
import com.shardingspherejdbc.mybatisplus.dto.AvgScoreResultDto;
import com.shardingspherejdbc.mybatisplus.dto.ErrorKeywordResultDto;
import com.shardingspherejdbc.mybatisplus.dto.paper.SendAnswer;
import com.shardingspherejdbc.mybatisplus.dto.questions.HasPapersResultDto;
import com.shardingspherejdbc.mybatisplus.dto.questions.QueryQuestionsResultDto;
import com.shardingspherejdbc.mybatisplus.entity.Questions;
import com.shardingspherejdbc.mybatisplus.mapper.AnswersMapper;
import com.shardingspherejdbc.mybatisplus.mapper.PapersMapper;
import com.shardingspherejdbc.mybatisplus.service.IKeywordsService;
import com.shardingspherejdbc.mybatisplus.service.IQuestionsService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shardingspherejdbc.mybatisplus.service.IAnswersService;
import com.shardingspherejdbc.mybatisplus.entity.Answers;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Autowired
    private PapersMapper papersMapper;

    @Autowired
    private IKeywordsService iKeywordsService;

    @Autowired
    private AnswersMapper answersMapper;

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


    @GetMapping("/hasPapers")
    public ResponseEntity<List<HasPapersResultDto>> hasPapers(@RequestParam String studentId,@RequestParam String classId){
        List<HasPapersResultDto> list = answersMapper.hasPapers(studentId,classId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    @PostMapping(value = "/check")
    public ResponseEntity<Object> check(@RequestBody SendAnswer params){
        QueryWrapper<Answers> wrapper = new QueryWrapper<>();

        wrapper.eq("student_id",params.getStudentId()).eq("test_id",params.getTestId());
        List<Answers> list = iAnswersService.list(wrapper);
        if(list.isEmpty()){
            return new ResponseEntity<>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("error", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/myPaper")
    public ResponseEntity<List<Answers>> myPaper(@RequestBody SendAnswer params){
        QueryWrapper<Answers> wrapper = new QueryWrapper<>();

        wrapper.eq("student_id",params.getStudentId()).eq("test_id",params.getTestId());
        List<Answers> list = iAnswersService.list(wrapper);
            return new ResponseEntity<>(list, HttpStatus.OK);
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

    @PostMapping(value = "/sendResult")
    public ResponseEntity<Object>  sendResult(@RequestBody SendAnswer params){

        List<String> strings = JSONUtil.parseArray(params.getListOfAnswers()).toList(String.class);
//        String[] split = StrUtil.strip(params.getListOfAnswers(), "[]").split(",");
//        List<String> strings = Arrays.asList(split);
        System.out.println(strings.toString());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+strings.size());
        List<QueryQuestionsResultDto> queryQuestionsResultDtos = papersMapper.queryQuestions(params.getTestId());
//        queryQuestionsResultDtos.stream().forEach(this::getAnswer);

        IntStream.range(0,strings.size()).forEach(i->{
           this.getAnswer(queryQuestionsResultDtos.get(i),strings.get(i) ,params.getStudentId());
        });
        return new ResponseEntity<>("send Success",HttpStatus.OK);
    }

    private void getAnswer(QueryQuestionsResultDto query,String answer,Integer studentId){
        Answers answers = new Answers();
        answers.setStuAnswerContent(answer);
        answers.setQuestionId(query.getId());
        answers.setScore(query.getScore());
        answers.setStudentId(studentId);
        answers.setAnswerContent(query.getAnswerContent());
        this.handleAnswer(answers);
        answers.setTestId(query.getTestId());
        iAnswersService.save(answers);
    }




    private void handleAnswer(Answers params) {

        Questions question = iQuestionsService.getById(params.getQuestionId());
        params.setTestId(999999);
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


    @GetMapping(value = "/avgScore")
    public ResponseEntity<AvgAndKeyword> avgScore(@RequestParam String testId){
        List<AvgScoreResultDto> avgScoreResultDtos = answersMapper.avgScore(testId);
        OptionalDouble average = avgScoreResultDtos.stream().mapToDouble(m -> {
            return m.getFactScore().divide(m.getRightScore(),2, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100)).doubleValue();
        }).average();


//        avgScoreResultDtos.stream().
        List<ErrorKeywordResultDto> errorKeywordResultDtos = answersMapper.errorKeyword(testId);

        if(errorKeywordResultDtos.isEmpty() || avgScoreResultDtos.isEmpty()){
            AvgAndKeyword avgAndKeyword = new AvgAndKeyword();
            avgAndKeyword.setKeyword("计算机历史");
            avgAndKeyword.setAvg(0.0);
            avgAndKeyword.setAllScore(0.0);
            return new ResponseEntity<>(avgAndKeyword,HttpStatus.OK);
        }
        Optional<Map.Entry<Integer, List<ErrorKeywordResultDto>>> collect = errorKeywordResultDtos.stream().collect(Collectors.groupingBy(ErrorKeywordResultDto::getQuestionId,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream()
                                .filter(dto -> dto.getStatus() == 0)
                                .collect(Collectors.toList())))).entrySet().stream().collect(
                Collectors.maxBy(Comparator.comparingInt(entry -> entry.getValue().size()))
        );

        Map<Integer, Double> collect1 = errorKeywordResultDtos.stream().collect(Collectors.groupingBy(ErrorKeywordResultDto::getQuestionId, Collectors.summingDouble(ErrorKeywordResultDto::getScore)));
        Double value = collect1.entrySet().iterator().next().getValue();

        Integer question_id = 0;
        if(collect.isPresent()){

            question_id = collect.get().getKey() ;
        }else{
             question_id = 14;
        }

        Questions question = iQuestionsService.getById(question_id);
        Integer s =  Integer.parseInt(question.getKeywords().split(",")[0]) ;

        String keywords = iKeywordsService.getById(s).getKeywords();

        AvgAndKeyword avgAndKeyword = new AvgAndKeyword();
        avgAndKeyword.setKeyword(keywords);
        avgAndKeyword.setAvg(average.getAsDouble());
        avgAndKeyword.setAllScore(value);
        return new ResponseEntity<>(avgAndKeyword,HttpStatus.OK);

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
