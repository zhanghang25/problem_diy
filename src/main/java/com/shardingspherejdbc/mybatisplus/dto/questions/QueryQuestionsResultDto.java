package com.shardingspherejdbc.mybatisplus.dto.questions;
/**
 * mapper.PapersMapper.queryQuestions的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-05-06
 */
public class QueryQuestionsResultDto {

    private Integer id;

    private String questionDescribe;

    private String type;

    private String answerContent;

    private String otherAnswer;

    private String disorder;

    private String keywords;

    private String source;

    private Integer score;

    private String testName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getOtherAnswer() {
        return otherAnswer;
    }

    public void setOtherAnswer(String otherAnswer) {
        this.otherAnswer = otherAnswer;
    }

    public String getDisorder() {
        return disorder;
    }

    public void setDisorder(String disorder) {
        this.disorder = disorder;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


    @Override
    public String toString() {
        return "QueryQuestionsResultDto{" +
        ", id=" + id +
        ", questionDescribe=" + questionDescribe +
        ", type=" + type +
        ", answerContent=" + answerContent +
        ", otherAnswer=" + otherAnswer +
        ", disorder=" + disorder +
        ", keywords=" + keywords +
        ", source=" + source +
        ", score=" + score +
        ", testName=" + testName +
        "}";
    }
}
