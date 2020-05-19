package com.exercise.budgetreal.model.vo;

import lombok.Data;

@Data
public class PasswdVo {
    private int userId;
    private String oldPass;
    private String newPass;
}
