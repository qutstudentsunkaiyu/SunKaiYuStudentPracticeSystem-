package com.exercise.budgetreal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 */
public class ExamUserAnswer extends Model<ExamUserAnswer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer tquestionTitleId;

    private Integer examId;

    private Integer testType;

    private String userAnswerWrite;

    private String userAnswerOption;

    private String correct;

    private double score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTquestionTitleId() {
        return tquestionTitleId;
    }

    public void setTquestionTitleId(Integer tquestionTitleId) {
        this.tquestionTitleId = tquestionTitleId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public String getUserAnswerWrite() {
        return userAnswerWrite;
    }

    public void setUserAnswerWrite(String userAnswerWrite) {
        this.userAnswerWrite = userAnswerWrite;
    }

    public String getUserAnswerOption() {
        return userAnswerOption;
    }

    public void setUserAnswerOption(String userAnswerOption) {
        this.userAnswerOption = userAnswerOption;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExamUserAnswer{" +
        "id=" + id +
        ", tquestionTitleId=" + tquestionTitleId +
        ", examId=" + examId +
        ", testType=" + testType +
        ", userAnswerWrite=" + userAnswerWrite +
        ", userAnswerOption=" + userAnswerOption +
        ", correct=" + correct +
        ", score=" + score +
        "}";
    }
}
