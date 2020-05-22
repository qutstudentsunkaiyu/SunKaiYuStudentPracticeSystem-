package com.exercise.budgetreal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.User;
import com.exercise.budgetreal.mapper.UserMapper;
import com.exercise.budgetreal.model.vo.UserRoleVo;
import com.exercise.budgetreal.model.vo.UserSearchVO;
import com.exercise.budgetreal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserRoleVo getUserRole(int id) {
        return userMapper.getUserRole(id);
    }

    @Override
    public IPage getUser(Page page, UserSearchVO userSearchVO) {
        return userMapper.getUser(page,userSearchVO);
    }

}
