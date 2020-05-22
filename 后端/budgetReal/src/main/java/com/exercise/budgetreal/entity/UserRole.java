package com.exercise.budgetreal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 */
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer Uid;

    private Integer Rid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return Uid;
    }

    public void setUserId(Integer Uid) {
        this.Uid = Uid;
    }

    public Integer getRoleId() {
        return Rid;
    }

    public void setRoleId(Integer Rid) {
        this.Rid = Rid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "id=" + id +
        ", userId=" + Uid +
        ", roleId=" + Rid +
        "}";
    }
}
