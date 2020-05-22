package com.exercise.budgetreal.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ExamInviteVo {
    private int ids;
    private int tqustionId;

    private int studentUserId;
    private String subjectName;
    private String tquestionName;
    private int teacherId;
    private String teacherName;
    private String studentName;
    private int totalTime;
    private LocalDateTime createTime;
    private  int finishRead;
    private  int finishTest;
    private int subjectId;

    private int testId;
}
