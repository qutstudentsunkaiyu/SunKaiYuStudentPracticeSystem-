package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.exercise.budgetreal.entity.Tquestion;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.service.TquestionService;
import com.exercise.budgetreal.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/tquestion")
public class TquestionController {
    @Autowired
    private TquestionService tquestionService;

    @Autowired
    private UserService userService;


    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping
    public Object addTest(@RequestBody Tquestion tquestion){
        QueryWrapper<Tquestion> tquestionQueryWrapper = new QueryWrapper<>();
        tquestionQueryWrapper.eq("name",tquestion.getName());
        Tquestion one = tquestionService.getOne(tquestionQueryWrapper);
        if (one!=null){
            return ApiRes.success("0","名称存在");
        }
        boolean save = tquestionService.save(tquestion);
        if (save){
            return ApiRes.successId("1","试题名称添加成功",tquestion.getId());
        }else {
            return ApiRes.successId("0","添加试题",0);
        }
    }


    @DeleteMapping
    public Object deleteQuestion(int id){
        boolean b = tquestionService.removeById(id);
        if (b){
            return ApiRes.success("1","删除成功");
        }else {
            return ApiRes.fail("0","删除失败，稍后重试");
        }
    }

    /**
     * 通过搜索试题
     * @param testName
     * @return
     */
    @GetMapping("/title")
    public IPage getQuestionByTitel(@Param("testName") String testName,@Param("current")long current,@Param("size") long size){


        Page<Tquestion> tquestionPage = new Page<>(current,size);
        if (testName==null){
            testName="aa";
        }
        IPage page = tquestionService.getTestByTitelOrUserName(tquestionPage, testName, "aa",0);
        return page;

    }
    @GetMapping("/user")
    public IPage getTquestionByUserName(@Param("userName") String userName,@Param("current")long current,@Param("size") long size){

        if (userName==null){
            userName="aa";
        }
        Page<Tquestion> tquestionPage = new Page<>(current,size);
        IPage page = tquestionService.getTestByTitelOrUserName(tquestionPage, "aa", userName,0);
        return page;
    }

    @GetMapping("/userId")
    public IPage getUserIdQuestion(@Param("userId") int userId,@Param("current")long current,@Param("size") long size){

        Page<Tquestion> tquestionPage = new Page<>(current,size);
        IPage page = tquestionService.getTestByTitelOrUserName(tquestionPage, "aa", "aa",userId);
        return page;

    }

    /**
     * h  ttp://localhost:8082/tquestion/all?current=1&size=100
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/all")
    public IPage getTquestionAll(@Param("current")long current,@Param("size") long size){


        Page<Tquestion> tquestionPage = new Page<>(current,size);
        IPage page = tquestionService.getTestByTitelOrUserName(tquestionPage, "aa", "aa",0);
        return page;
    }

    @PutMapping
    public Object upTquetion(@RequestBody Tquestion tquestion){
        QueryWrapper<Tquestion> tquestionQueryWrapper = new QueryWrapper<>();
        tquestionQueryWrapper.eq("id",tquestion.getId());
        tquestion.setUserId(null);
        boolean update = tquestionService.update(tquestion, tquestionQueryWrapper);
        if (update){
            return ApiRes.success("1","修改成功");
        }else {
            return ApiRes.fail("0","修改失败，稍后重试");
        }
    }


}

