package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exercise.budgetreal.entity.*;
import com.exercise.budgetreal.mapper.ExamUserAnswerMapper;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.model.vo.ExamResultPreview;
import com.exercise.budgetreal.model.vo.ExamUserAnswerVo;
import com.exercise.budgetreal.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/examUserAnswer")
public class ExamUserAnswerController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamUserAnswerService examUserAnswerService;

    @Autowired
    private TquestionItemSelectService tquestionItemSelectService;


    @Autowired
    private TquestionItemWriteService tquestionItemWriteService;

    @Autowired
    private ExamUserAnswerMapper examUserAnswerMapper;

    @Autowired
    private ExamInviteService examInviteService;

    /**
     * [
     *     {
     *         "tquestionTitleId": 37,
     *         "examId": 2,
     *         "testType": 0,
     *         "userAnswerOption": "A"
     *
     *     },
     *     {
     *         "tquestionTitleId": 29,
     *         "examId": 2,
     *         "testType": 0,
     *         "userAnswerOption": "B"
     *     }
     *
     * ]
     * @param userList
     * @return
     */
    @PostMapping("/select")
    public Object savaAnswerSelect(@RequestBody List<ExamUserAnswer> userList){
        System.out.println(userList.toString());
        ArrayList<Integer> tquestionIds = new ArrayList<>();
        for (int i=0;i<userList.size();i++){
            tquestionIds.add(userList.get(i).getTquestionTitleId());
        }
        System.out.println(tquestionIds.toString());
        QueryWrapper<TquestionItemSelect> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
        tquestionItemSelectQueryWrapper.in("id",tquestionIds);
        List<TquestionItemSelect> selects = tquestionItemSelectService.list(tquestionItemSelectQueryWrapper);
        System.out.println(selects.toString());

        for (int i=0;i<userList.size();i++){
            for (int j=0;j<selects.size();j++){

                if (userList.get(i).getTquestionTitleId()==selects.get(j).getId()){
                    userList.get(i).setCorrect(selects.get(i).getCorrect());
                    if (userList.get(i).getUserAnswerOption().toUpperCase().equals(selects.get(i).getCorrect().toUpperCase())){
//                        分值设置
                        userList.get(i).setScore(Double.parseDouble(selects.get(i).getScore()));
                    }else {
                        userList.get(i).setScore(0);
                    }
                    break;
                }
            }
        }
        boolean b = examUserAnswerService.saveOrUpdateBatch(userList);
        /*设置考试分值*/
        double totalScore=0;
        for (int i=0;i<userList.size();i++){
            totalScore+=userList.get(i).getScore();
        }
        Exam exam = new Exam();
        exam.setId(userList.get(0).getExamId());
        exam.setTotalScore(totalScore);
        examService.updateById(exam);
        /*设置考试时常*/
        QueryWrapper<Exam> examQueryWrapper = new QueryWrapper<>();

        examQueryWrapper.eq("id",exam.getId());
        Exam one = examService.getOne(examQueryWrapper);
        LocalDateTime createTime = one.getCreateTime();
        LocalDateTime updateTime = one.getUpdateTime();
        Duration between = Duration.between(createTime, updateTime);
        long l = between.toMinutes();
        System.out.println(l);
        Exam exam1 = new Exam();
        exam1.setId(exam.getId());exam1.setUseTime((int)l);
        examService.updateById(exam1);


        if (b){
            return ApiRes.success("1","成功");
        }else {
            return ApiRes.fail("0","失败");
        }
    }

    /**
     * 保存书面题
     *
     *[
     *     {
     *         "tquestionTitleId": 1,
     *         "examId": 2,
     *         "testType":1,
     *         "userAnswerWrite": "sdfsdfA"
     *
     *     },
     *     {
     *         "tquestionTitleId": 7,
     *         "examId": 2,
     *         "testType": 1,
     *         "userAnswerWrite": "Bsdfsdf"
     *     }
     * ]
     *
     * @param userList
     * @return
     */
    @PostMapping("/write")
    public Object savaAnswerWrite(@RequestBody List<ExamUserAnswer> userList){
        ArrayList<Integer> tquestionIds = new ArrayList<>();
        for (int i=0;i<userList.size();i++){
            tquestionIds.add(userList.get(i).getTquestionTitleId());
        }
        System.out.println(tquestionIds.toString());
        QueryWrapper<TquestionItemWrite> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
        tquestionItemSelectQueryWrapper.in("id",tquestionIds);
        List<TquestionItemWrite> selects = tquestionItemWriteService.list(tquestionItemSelectQueryWrapper);
        System.out.println(selects.toString());

        for (int i=0;i<userList.size();i++){
            for (int j=0;j<selects.size();j++){
                if (userList.get(i).getTquestionTitleId()==selects.get(j).getId()){
                    userList.get(i).setCorrect(selects.get(i).getAnswer());
                    userList.get(i).setScore(0);
                    break;
                }
            }


        }
        boolean b = examUserAnswerService.saveOrUpdateBatch(userList);
        if (b){
            return ApiRes.success("1","成功");
        }else {
            return ApiRes.fail("0","失败");
        }
    }

    /**
     * 获取考试选择题
     * @param examId
     * @return
     */
    @GetMapping("/select")
    public List getEndExamSelect(@RequestParam("examId") int examId){
        QueryWrapper<ExamUserAnswer> examUserAnswerQueryWrapper = new QueryWrapper<>();
        examUserAnswerQueryWrapper.eq("exam_id",examId);
        examUserAnswerQueryWrapper.eq("test_type",0);

        List<ExamUserAnswerVo> list =examUserAnswerMapper.getSelectList(examUserAnswerQueryWrapper);


        return list;
    }

    /**
     * 获取考试书面题
     * @param examId
     * @return
     */
    @GetMapping("/write")
    public List getEndExamWrite(@RequestParam("examId") int examId){
        QueryWrapper<ExamUserAnswer> examUserAnswerQueryWrapper = new QueryWrapper<>();
        examUserAnswerQueryWrapper.eq("exam_id",examId);
        examUserAnswerQueryWrapper.eq("test_type",1);
        List<ExamUserAnswerVo> list =examUserAnswerMapper.getWriteList(examUserAnswerQueryWrapper);
        return list;
    }


    @GetMapping("/result")
    public void getExamResult(@RequestParam("examId") int examId){
        Exam examOne = examService.getById(examId);
        Duration duration = Duration.between(examOne.getCreateTime(),  examOne.getUpdateTime() );
        long useTime= duration.toMinutes();
        examUserAnswerMapper.sunScore(examId,useTime);

    }

    /**
     *
     * @param examId
     * @return
     */
    @GetMapping("/user_write")
    public List<ExamResultPreview> getUserList(@Param("examId") int examId){
      List<ExamResultPreview> userAnswerList = examUserAnswerService.getUserAnswer(examId);
        return userAnswerList;

    }

    @PutMapping("/save")
    public ApiRes saveAnswer(@Param("examId") int examId,@RequestBody List<ExamResultPreview> examResultPreviewList  ){
         double totalScore=0;
        for (int i=0;i<examResultPreviewList.size();i++){
            ExamResultPreview examResultPreview = examResultPreviewList.get(i);
            ExamUserAnswer examUserAnswer = examUserAnswerService.getById(examResultPreview.getUserAnswerId());
           examUserAnswer.setScore(examResultPreview.getUserScore());
            boolean save = examUserAnswerService.updateById(examUserAnswer);
       totalScore+= examResultPreview.getUserScore();
        }

        QueryWrapper<ExamInvite> examInviteQueryWrapper = new QueryWrapper<>();
        examInviteQueryWrapper.eq("exam_id",examId);
        ExamInvite examInvite = examInviteService.getOne(examInviteQueryWrapper);
        examInvite.setIsExam(3);
        examInvite.setIsCorrection(1);
        examInviteService.updateById(examInvite);

        Exam exam = examService.getById(examId);
        totalScore+=exam.getTotalScore();
        exam.setTotalScore(totalScore);

        boolean update = examService.updateById(exam);
        if (update){
            return ApiRes.success("1","批阅成功");
        }else {
            return ApiRes.fail("0","修改失败，稍后重试");
        }

    }

}

