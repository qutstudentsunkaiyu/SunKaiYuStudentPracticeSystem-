package com.exercise.budgetreal.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exercise.budgetreal.entity.ExamUserAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exercise.budgetreal.model.vo.ExamResultPreview;
import com.exercise.budgetreal.model.vo.ExamUserAnswerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface ExamUserAnswerMapper extends BaseMapper<ExamUserAnswer> {

    List<ExamUserAnswerVo> getSelectList(@Param("ew") QueryWrapper ew);
    List<ExamUserAnswerVo> getWriteList(@Param("ew") QueryWrapper ew);
    void sunScore(@Param("examId") int examId,@Param("useTime") long useTime);
    List<ExamResultPreview>  getUserAnswer(@Param("examId") int examId);
}
