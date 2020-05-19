package com.exercise.budgetreal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exercise.budgetreal.entity.Tquestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exercise.budgetreal.model.vo.TQuestionVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface TquestionMapper extends BaseMapper<Tquestion> {


    IPage<TQuestionVO> getTestByTitle(IPage iPage, @Param("testName")String testName,@Param("userName")String userName,@Param("userId")int userId);
}
