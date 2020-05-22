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
public class Exam extends Model<Exam> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer courseId;

        /**
     * 当exam_type为0 该数位0
     */
         private Integer tquestionId;

    private Integer testTime;

        /**
     * 共时长总
     */
         private Integer totalTime;

        /**
     * 0 随机试题   1指定试题
     */
         private Integer testType;

        /**
     * 择题选数目
     */
         private Integer chooseNu;

        /**
     * 面题书数目
     */
         private Integer writeNu;

    private double totalScore;

    private  int flag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTquestionId() {
        return tquestionId;
    }

    public void setTquestionId(Integer tquestionId) {
        this.tquestionId = tquestionId;
    }

    public Integer getUseTime() {
        return testTime;
    }

    public void setUseTime(Integer testTime) {
        this.testTime = testTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getExamType() {
        return testType;
    }

    public void setExamType(Integer testType) {
        this.testType = testType;
    }

    public Integer getSelectNum() {
        return chooseNu;
    }

    public void setSelectNum(Integer chooseNu) {
        this.chooseNu = chooseNu;
    }

    public Integer getWriteNum() {
        return writeNu;
    }

    public void setWriteNum(Integer writeNu) {
        this.writeNu = writeNu;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
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
        return "Exam{" +
        "id=" + id +
        ", userId=" + userId +
        ", subjectId=" + courseId +
        ", tquestionId=" + tquestionId +
        ", useTime=" + testTime +
        ", totalTime=" + totalTime +
        ", examType=" + testType +
        ", selectNum=" + chooseNu +
        ", writeNum=" + writeNu +
        ", totalScore=" + totalScore +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
