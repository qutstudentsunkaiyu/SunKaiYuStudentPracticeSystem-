package com.exercise.budgetreal.model.vo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TQuestionVO {
    private int id;
   private String tquestionName;
   private String userName;
   private int chooseNu;
   private int writeNu;
   private int examNum;
   private String subjectName;
   private int courseId;
   private int totalTime;
   private LocalDateTime createTime;

    public TQuestionVO(String tquestionName, String userName, int chooseNu, int writeNu, int examNum) {
        this.tquestionName = tquestionName;
        this.userName = userName;
        this.chooseNu = chooseNu;
        this.writeNu = writeNu;
        this.examNum = examNum;
    }
}
