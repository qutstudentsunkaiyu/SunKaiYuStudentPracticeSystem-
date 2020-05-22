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
public class TquestionItemSelect extends Model<TquestionItemSelect> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Integer tquestionId;

    private String chooseA;

    private String chooseB;

    private String chooseC;

    private String chooseD;

    private String rightChoose;

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

    public String getSelectAItem() {
        return chooseA;
    }

    public void setSelectAItem(String chooseA) {
        this.chooseA = chooseA;
    }

    public String getSelectBItem() {
        return chooseB;
    }

    public void setSelectBItem(String chooseB) {
        this.chooseB = chooseB;
    }

    public String getSelectCItem() {
        return chooseC;
    }

    public void setSelectCItem(String chooseC) {
        this.chooseC = chooseC;
    }

    public String getSelectDItem() {
        return chooseD;
    }

    public void setSelectDItem(String chooseD) {
        this.chooseD = chooseD;
    }

    public String getCorrect() {
        return rightChoose;
    }

    public void setCorrect(String rightChoose) {
        this.rightChoose = rightChoose;
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
        return "TquestionItemSelect{" +
        "id=" + id +
        ", title=" + title +
        ", tquestionId=" + tquestionId +
        ", selectAItem=" + chooseA +
        ", selectBItem=" + chooseB +
        ", selectCItem=" + chooseC +
        ", selectDItem=" + chooseD +
        ", correct=" + rightChoose +
        ", score=" + score +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
