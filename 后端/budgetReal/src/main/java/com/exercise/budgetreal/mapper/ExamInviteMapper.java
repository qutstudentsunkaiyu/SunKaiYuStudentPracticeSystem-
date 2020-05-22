package com.exercise.budgetreal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exercise.budgetreal.entity.ExamInvite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface ExamInviteMapper extends BaseMapper<ExamInvite> {
    IPage getList(IPage page, @Param("user") int user,@Param("types") int types);
}
