package com.exercise.budgetreal.model.vo;

import lombok.Data;

@Data
public class ExamUserAnswerVo {
    private Integer id;

    private Integer tquestionId;

    private Integer testId;

    private Integer testType;

    private String userAnswerWrite;

    private String questionChoose;

    private String correct;

    private double score;


    private String title;

    private String chooseA;

    private String chooseB;

    private String chooseC;

    private String chooseD;


}
