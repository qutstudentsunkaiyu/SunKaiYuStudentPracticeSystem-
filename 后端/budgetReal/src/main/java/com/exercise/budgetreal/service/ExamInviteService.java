package com.exercise.budgetreal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.ExamInvite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ExamInviteService extends IService<ExamInvite> {
    IPage getList(Page page, int user,int types);
}
