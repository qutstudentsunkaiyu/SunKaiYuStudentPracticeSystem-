package com.exercise.budgetreal.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.User;
import com.exercise.budgetreal.entity.UserRole;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.model.vo.PasswdVo;
import com.exercise.budgetreal.model.vo.UserRoleVo;
import com.exercise.budgetreal.model.vo.UserSearchVO;
import com.exercise.budgetreal.service.UserRoleService;
import com.exercise.budgetreal.service.UserService;
import com.exercise.budgetreal.support.UserHolder;
import com.exercise.budgetreal.util.CookieUtil;
import com.exercise.budgetreal.util.Md5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

   @GetMapping("/person")
   public User getPerson(@RequestParam("id") int id){
       User byId = userService.getById(id);
       return byId;
   }

   @PutMapping("/pass")
   public ApiRes upPass(@RequestBody PasswdVo passwdVo){
       User user = userService.getById(passwdVo.getUserId());
      if (user.getPasswd().equals(Md5Util.generatePWD(passwdVo.getOldPass(), ""))){
          user.setPasswd(Md5Util.generatePWD(passwdVo.getNewPass(), ""));
          boolean flag = userService.updateById(user);
          if (flag){
              return ApiRes.fail("1","更新成功");
          }else {
              return ApiRes.fail("0","更新失败");
          }
      }else {
          return ApiRes.fail("0","旧密码错误");
      }
   }
    @PostMapping("/register")
    public ApiRes register(@RequestBody User user){

        if (user.getName()==null||user.getPasswd()==null){
        return ApiRes.fail("0","密码或账号不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", user.getName());
        User one = userService.getOne(userQueryWrapper);
        if (one!=null){
            return ApiRes.fail("0","该用户存在");
        }
        user.setPasswd(Md5Util.generatePWD(user.getPasswd(), ""));
        boolean save = userService.save(user);
        if (save){
            User two = userService.getOne(userQueryWrapper);
            UserRole userRole = new UserRole();
            userRole.setRoleId(3);
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);
           return ApiRes.success("1","注册成功");
        }else {
            return ApiRes.fail("0","系统错误，稍后重试");
        }

    }
    @PostMapping("/login")
    public Object user(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User one = userService.getOne(queryWrapper);
        if (one == null) {
            return ApiRes.fail("0","用户不存在");
        }
        if (Md5Util.generatePWD(user.getPasswd(), "").equals(one.getPasswd())) {

          one.setCreateTime(null);
          one.setUpdateTime(null);
            String jwtToken = Jwts.builder().setSubject(user.getName()).claim("user", one)
                    .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();


            CookieUtil.setCookie(req, res, "_token", jwtToken);
            return  ApiRes.fail("1", jwtToken);


        }
        else {
            return  ApiRes.fail("0", "错误的账号或密码");
        }

    }

    /**
     * 更新用户基本信息
     * @param user 要有id
     * @return
     */
    @PutMapping("/up")
    public Object upUser(@RequestBody User user){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",user.getId());
        user.setPasswd(null);
        user.setName(null);
        System.out.println(user.toString());
        boolean update = userService.update(user, userQueryWrapper);
        if (update){
            return ApiRes.success("1","更新成功");
        }else {
            return ApiRes.fail("0","更新失败");
        }
    }



    @GetMapping("/info")
    public UserRoleVo getUserRoleInfo(){
        User user = UserHolder.get();
        UserRoleVo userRole = userService.getUserRole(user.getId());
        return  userRole;
    }

    /**
     * 获取通过查询条件用户
     * @param current search= {"name":"","realName":"","school":"","profession":"","roleName":""}
     * @param size
     * @param search
     * @return
     */
    @GetMapping("/all")
    public IPage getAllUser(@Param("current")long current,@Param("size") long size,@Param("search") String search){
        Page<User> userPage = new Page<>(current, size);

        System.out.println(search);


        UserSearchVO  userSearchVO = JSONObject.parseObject(search, UserSearchVO.class);



        System.out.println(userSearchVO.toString());
        IPage user = userService.getUser(userPage, userSearchVO);
        return user;

    }

    @DeleteMapping("/dele")
    public ApiRes deleteUser(@RequestParam("id") int id){
        boolean b = userService.removeById(id);
        if (b){
            return ApiRes.success("1","删除成功");
        }else {
            return ApiRes.fail("0","删除失败");
        }
    }

}

