package com.exercise.budgetreal.model.vo;

import lombok.Data;

@Data
public class UserSearchVO {
    private String name;
    private String tname;
    private String school;
    private String major;
    private String roleName;

    @Override
    public String toString() {
        return "UserSearchVO{" +
                "name='" + name + '\'' +
                ", realName='" + tname + '\'' +
                ", school='" + school + '\'' +
                ", profession='" + major + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
