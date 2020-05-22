package com.exercise.budgetreal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.Exam;
import com.exercise.budgetreal.mapper.ExamMapper;
import com.exercise.budgetreal.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-01-07
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Override
    public IPage getExamList(Page page, int user) {
        return examMapper.getExamList(page,user);
    }
}
