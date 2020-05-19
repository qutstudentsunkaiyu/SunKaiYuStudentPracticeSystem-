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
public class Tquestion extends Model<Tquestion> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer userId;

    private Integer testNumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer courseId;

    private Integer chooseNu;
    private Integer writeNu;

    private int totalTime;

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getOptionNum() {
        return chooseNu;
    }

    public void setOptionNum(Integer chooseNu) {
        this.chooseNu = chooseNu;
    }

    public Integer getWriteNum() {
        return writeNu;
    }

    public void setWriteNum(Integer writeNu) {
        this.writeNu = writeNu;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return courseId;
    }

    public void setSubjectId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getExamNumber() {
        return testNumber;
    }

    public void setExamNumber(Integer testNumber) {
        this.testNumber = testNumber;
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
        return "Tquestion{" +
        "id=" + id +
        ", name=" + name +
        ", userId=" + userId +
        ", examNumber=" + testNumber +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
