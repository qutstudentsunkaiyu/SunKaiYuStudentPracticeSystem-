package com.exercise.budgetreal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 */
public class TquestionItemWrite extends Model<TquestionItemWrite> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Integer tquestionId;

    private String rightAnswer;

    private String score;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTquestionId() {
        return tquestionId;
    }

    public void setTquestionId(Integer tquestionId) {
        this.tquestionId = tquestionId;
    }

    public String getAnswer() {
        return rightAnswer;
    }

    public void setAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        return "TquestionItemWrite{" +
        "id=" + id +
        ", title=" + title +
        ", tquestionId=" + tquestionId +
        ", answer=" + rightAnswer +
        ", score=" + score +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
