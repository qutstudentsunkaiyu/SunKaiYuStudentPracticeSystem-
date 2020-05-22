package com.exercise.budgetreal.model.vo;

import lombok.Data;

@Data
public class ExamResultPreview {
    private int userAnswerId;
    private String title;
    private String userAnswer;
    private int totalScore;
    private int userScore;
}
