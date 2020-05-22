package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exercise.budgetreal.entity.UserRole;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.service.UserRoleService;
import com.exercise.budgetreal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @PutMapping
    public Object setUserRole(@RequestBody UserRole userRole){
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",userRole.getUserId());

        boolean save = userRoleService.update(userRole,userRoleQueryWrapper);
        if (save){
            return ApiRes.success("1","修改成功");
        }else {
            return ApiRes.success("0","更改失败");
        }
    }
}

