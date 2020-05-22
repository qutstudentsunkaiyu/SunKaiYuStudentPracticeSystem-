package com.exercise.budgetreal.service.impl;

import com.exercise.budgetreal.entity.ExamUserAnswer;
import com.exercise.budgetreal.mapper.ExamUserAnswerMapper;
import com.exercise.budgetreal.model.vo.ExamResultPreview;
import com.exercise.budgetreal.service.ExamUserAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-01-07
 */
@Service
public class ExamUserAnswerServiceImpl extends ServiceImpl<ExamUserAnswerMapper, ExamUserAnswer> implements ExamUserAnswerService {

    @Autowired
    private ExamUserAnswerMapper examUserAnswerMapper;
    @Override
    public List<ExamResultPreview> getUserAnswer(int examId) {
        List<ExamResultPreview> userAnswer = examUserAnswerMapper.getUserAnswer(examId);
        return userAnswer;
    }
}
