package com.exercise.budgetreal.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamHistory {
    private int testId;
    private String subjectName;
    private String tquestionName;
    private int tquestionId;
    private int totalTime;
    private int useTime;
    private double totalScore;
    private LocalDateTime createTime;
}
