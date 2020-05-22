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
public class ExamInvite extends Model<ExamInvite> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer teacherId;

    private Integer studentId;

    public String getUserIdTeacherName() {
        return teacherName;
    }

    public void setUserIdTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    private String studentName;

    private String teacherName;

    private Integer tqustionId;
    private Integer testId;

    private String tquestionName;


    /**
     * 是否参加考试
     */
         private Integer finishTest;

        /**
     * 是否批阅
     */
         private Integer finishRead;

    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserIdTeacher() {
        return teacherId;
    }

    public void setUserIdTeacher(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getUserIdStudent() {
        return studentId;
    }

    public void setUserIdStudent(Integer studentId) {
        this.studentId = studentId;
    }

    public String getUserIdStudentName() {
        return studentName;
    }

    public void setUserIdStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getTqustionId() {
        return tqustionId;
    }

    public void setTqustionId(Integer tqustionId) {
        this.tqustionId = tqustionId;
    }

    public String getTquestionName() {
        return tquestionName;
    }

    public void setTquestionName(String tquestionName) {
        this.tquestionName = tquestionName;
    }

    public Integer getIsExam() {
        return finishTest;
    }

    public void setIsExam(Integer finishTest) {
        this.finishTest = finishTest;
    }

    public Integer getIsCorrection() {
        return finishRead;
    }

    public void setIsCorrection(Integer finishRead) {
        this.finishRead = finishRead;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getExamId() {
        return testId;
    }

    public void setExamId(Integer testId) {
        this.testId = testId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExamInvite{" +
        "id=" + id +
        ", userIdTeacher=" + teacherId +
        ", userIdStudent=" + studentId +
        ", userIdStudentName=" + studentName +
        ", tqustionId=" + tqustionId +
        ", tquestionName=" + tquestionName +
        ", isExam=" + finishTest +
        ", isCorrection=" + finishRead +
        ", createTime=" + createTime +
        "}";
    }
}
