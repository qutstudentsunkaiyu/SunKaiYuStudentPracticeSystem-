package com.exercise.budgetreal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.Tquestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface TquestionService extends IService<Tquestion> {

    IPage getTestByTitelOrUserName(Page page,String testName, String userName ,int userId);

}
