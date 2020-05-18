package com.exercise.budgetreal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 */

public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;

    private String tname;

    private String telephone;

    private String email;

    private String school;

    private String age;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return major;
    }

    public void setProfession(String major) {
        this.major = major;
    }

    private String major;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return password;
    }

    public void setPasswd(String password) {
        this.password = password;
    }

    public String getRealName() {
        return tname;
    }

    public void setRealName(String tname) {
        this.tname = tname;
    }

    public String getPhone() {
        return telephone;
    }

    public void setPhone(String phone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", passwd=" + password +
        ", realName=" + tname +
        ", phone=" + telephone +
        ", email=" + email +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
