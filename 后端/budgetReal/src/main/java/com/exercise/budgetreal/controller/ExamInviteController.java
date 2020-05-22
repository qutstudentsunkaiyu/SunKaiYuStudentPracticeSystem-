package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.ExamInvite;
import com.exercise.budgetreal.entity.Tquestion;
import com.exercise.budgetreal.entity.User;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.service.ExamInviteService;
import com.exercise.budgetreal.service.TquestionService;
import com.exercise.budgetreal.service.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/examInvite")
public class ExamInviteController {

    @Autowired
    private ExamInviteService examInviteService;

    @Autowired
    private TquestionService tquestionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiRes addInvite(@RequestBody ExamInvite examInvite){

      System.out.println(examInvite.toString());
        Tquestion tquestion = tquestionService.getById(examInvite.getTqustionId());
        examInvite.setTquestionName(tquestion.getName());
        User userTeacher = userService.getById(examInvite.getUserIdTeacher());
        examInvite.setUserIdTeacherName(userTeacher.getRealName());
        boolean save = examInviteService.save(examInvite);
        if (save){

            return ApiRes.success("1","邀请成功");
        }else {
            return ApiRes.fail("0","系统错误，稍后重试");
        }

    }

    @GetMapping("/list")

    public IPage getExamHistory(@Param("current")long current, @Param("size") long size, @RequestParam("userId") int userId,@RequestParam("types") int types){
        Page<User> userPage = new Page<>(current, size);
        IPage list = examInviteService.getList(userPage, userId, types);
        return list;
    }

    @GetMapping("/update")
    public ApiRes upIsExam(@Param("examInviteId")int examInviteId,@Param("states")int states,@Param("examId") int examId){
        ExamInvite user = examInviteService.getById(examInviteId);
        user.setIsExam(1);
        user.setExamId(examId);
        boolean save = examInviteService.updateById(user);
        if (save){

            return ApiRes.success("1","成功");
        }else {
            return ApiRes.fail("0","系统错误，稍后重试");
        }

    }



}

