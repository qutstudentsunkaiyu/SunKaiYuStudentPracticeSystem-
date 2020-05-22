package com.exercise.budgetreal.service;

import com.exercise.budgetreal.entity.ExamUserAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.budgetreal.model.vo.ExamResultPreview;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ExamUserAnswerService extends IService<ExamUserAnswer> {
    List<ExamResultPreview>  getUserAnswer(int examId);
}
