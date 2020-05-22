package com.exercise.budgetreal.controller;


import com.exercise.budgetreal.entity.Subject;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/subject",produces="application/json;charset=UTF-8")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @PostMapping("")
    public Object addSubject(@RequestBody Subject subject){

        System.out.println(subject.toString());
        boolean save = subjectService.save(subject);

        if (save){
            return ApiRes.success("1","添加成功");
        }else {
            return ApiRes.fail("0","添加失败");
        }

    }
    @DeleteMapping
    public Object deleteSubject(int id){
        boolean save = subjectService.removeById(id);
        if (save){
            return ApiRes.success("1","删除成功");
        }else {
            return ApiRes.fail("0","删除失败");
        }
    }
}

