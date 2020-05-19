package com.exercise.budgetreal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.Tquestion;
import com.exercise.budgetreal.mapper.TquestionMapper;
import com.exercise.budgetreal.service.TquestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-01-05
 */
@Service
public class TquestionServiceImpl extends ServiceImpl<TquestionMapper, Tquestion> implements TquestionService {

    @Resource
    private TquestionMapper tquestionMapper;


    @Override
    public IPage getTestByTitelOrUserName(Page page, String testName, String userName,int userId) {
        return tquestionMapper.getTestByTitle(page,testName,userName,userId);
    }
}
