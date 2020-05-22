package com.exercise.budgetreal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.ExamInvite;
import com.exercise.budgetreal.mapper.ExamInviteMapper;
import com.exercise.budgetreal.service.ExamInviteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-05-09
 */
@Service
public class ExamInviteServiceImpl extends ServiceImpl<ExamInviteMapper, ExamInvite> implements ExamInviteService {

    @Autowired
    private ExamInviteMapper examInviteMapper;
    @Override
    public IPage getList(Page page, int user, int types) {
        return examInviteMapper.getList(page,user,types);
    }
}
