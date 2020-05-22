package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.*;
import com.exercise.budgetreal.mapper.TquestionItemSelectMapper;
import com.exercise.budgetreal.mapper.TquestionItemWriteMapper;
import com.exercise.budgetreal.service.ExamService;
import com.exercise.budgetreal.service.TquestionItemSelectService;
import com.exercise.budgetreal.service.TquestionItemWriteService;
import com.exercise.budgetreal.service.TquestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private TquestionService tquestionService;

    @Autowired
    private TquestionItemSelectService tquestionItemSelectService;

    @Resource
    private TquestionItemSelectMapper tquestionItemSelectMapper;

    @Resource
    private TquestionItemWriteMapper tquestionItemWriteMapper;

    @Autowired
    private TquestionItemWriteService tquestionItemWriteService;

    @PostMapping
    public Object saveExam(@RequestBody Exam exam){
        System.out.println(exam+"----------------------------");
        if (exam.getExamType()==0){
            exam.setTquestionId(0);
        }else {
            /*获取该试题的选择，大题数目*/
            Tquestion tquestion = tquestionService.getById(exam.getTquestionId());
            exam.setSelectNum(tquestion.getOptionNum());
            exam.setWriteNum(tquestion.getWriteNum());
        }
        boolean save = examService.save(exam);
     if(save){
         return exam;
     }else {
         return null;
     }

    }

    /**
     * 获取选择题
     * @param tquestion
     * @param num
     * @return
     */
    @GetMapping("/select")
    public Object getSelect(@RequestParam("tquestion") int tquestion,@RequestParam("num") int num){
        QueryWrapper<TquestionItemSelect> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
        System.out.println(tquestion+num);
        if (tquestion==0){
            /*随机题*/
         List<TquestionItemSelect> list = tquestionItemSelectMapper.getListRandom(num);
         for (int i=0;i<list.size();i++){
             list.get(i).setCorrect(null);
         }
            return list;
        }else {
            tquestionItemSelectQueryWrapper.eq("tquestion_id",tquestion);
            List<TquestionItemSelect> list = tquestionItemSelectService.list(tquestionItemSelectQueryWrapper);
            for (int i=0;i<list.size();i++){
                list.get(i).setCorrect(null);
            }
            return list;
        }
    }

    /**
     *  获取书面题
     * @param tquestion
     * @param num
     * @return
     */
    @GetMapping("/write")
    public Object getWrite(@RequestParam("tquestion") int tquestion,@RequestParam("num") int num){
        QueryWrapper<TquestionItemWrite> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
        System.out.println(tquestion+num);
        if (tquestion==0){
            /*随机题*/
            List<TquestionItemWrite> list = tquestionItemWriteMapper.getListRandom(num);
            for (int i=0;i<list.size();i++){
                list.get(i).setAnswer(null);
            }
            return list;
        }else {
            tquestionItemSelectQueryWrapper.eq("tquestion_id",tquestion);
            List<TquestionItemWrite> list = tquestionItemWriteService.list(tquestionItemSelectQueryWrapper);
            for (int i=0;i<list.size();i++){
                list.get(i).setAnswer(null);
            }
            return list;
        }
    }

    @GetMapping("/history")
    public IPage getExamHistory(@Param("current")long current, @Param("size") long size, @RequestParam("userId") int userId){
        Page<User> userPage = new Page<>(current, size);
        return examService.getExamList( userPage, userId);
    }


    @GetMapping
    public Exam getResult(@RequestParam("examId") int examId){
        Exam examServiceById = examService.getById(examId);
        return examServiceById;
    }
}

