package com.exercise.budgetreal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exercise.budgetreal.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface ExamMapper extends BaseMapper<Exam> {

  IPage getExamList(IPage page, @Param("user") int user);
}
