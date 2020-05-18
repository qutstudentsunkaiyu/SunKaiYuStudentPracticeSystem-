package com.exercise.budgetreal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.budgetreal.model.vo.UserRoleVo;
import com.exercise.budgetreal.model.vo.UserSearchVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface UserService extends IService<User> {
    public UserRoleVo getUserRole(int id);
    public IPage getUser(Page page, UserSearchVO userSearchVO);

}
