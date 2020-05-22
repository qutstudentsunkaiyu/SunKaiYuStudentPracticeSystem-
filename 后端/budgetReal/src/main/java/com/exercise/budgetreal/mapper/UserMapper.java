package com.exercise.budgetreal.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exercise.budgetreal.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exercise.budgetreal.model.vo.UserRoleVo;
import com.exercise.budgetreal.model.vo.UserSearchVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface UserMapper extends BaseMapper<User> {
    UserRoleVo getUserRole(@Param("id") int id);
   IPage getUser(IPage page, @Param("users")UserSearchVO userSearchVO);

}
