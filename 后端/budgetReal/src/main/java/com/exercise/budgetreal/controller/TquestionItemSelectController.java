package com.exercise.budgetreal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exercise.budgetreal.entity.TquestionItemSelect;
import com.exercise.budgetreal.model.vo.ApiRes;
import com.exercise.budgetreal.model.vo.ReturnInfoUtil;
import com.exercise.budgetreal.service.TquestionItemSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/tquestionItemSelect")
public class TquestionItemSelectController {

    @Autowired
    private TquestionItemSelectService excelService;

    /**
     * 上传试题
     * @param file
     * @param tId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/import")
    public ReturnInfoUtil fileImport(@RequestParam("file") MultipartFile file, @RequestParam("tId") long tId,Model model) throws Exception {
        String fileName = file.getOriginalFilename();
        ReturnInfoUtil returnInfoUtil = excelService.getExcelInfo(fileName, file,tId);
        model.addAttribute("retInfo",returnInfoUtil);
        return returnInfoUtil;
    }


    /*下载路径
    *   http://localhost:8082/static/考试导入.xls
    * */

    /**
     * 修改选择题
      * @param tquestion
     * @return
     */
    @PutMapping
    public Object upTquestion(@RequestBody TquestionItemSelect tquestion){
        QueryWrapper<TquestionItemSelect> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
       tquestionItemSelectQueryWrapper.eq("id", tquestion.getId());
       System.out.println(tquestion.toString());
        boolean update = excelService.update(tquestion,tquestionItemSelectQueryWrapper);
        if (update){
            return ApiRes.success("1","修改成功");
        }else {
            return ApiRes.fail("0","修改失败");
        }


    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Object deleteTquesion(@RequestParam("id") int id){
//        System.out.println(deleteArray);
//        String replace = deleteArray.replace("{", "[");
//        String deleteArraya = replace.replace("}", "]");
//        System.out.println(deleteArraya);
//        List<Integer> array = JSONObject.parseObject(deleteArraya, List.class);
//        System.out.println(array);
        boolean b = excelService.removeById(id);
        if (b){
            return ApiRes.success("1","删除成功");
        }else {
            return ApiRes.fail("0","sc失败");
        }
    }

    /**
     * 添加单个试题
     * @param tquestionItemSelect
     * @return
     *
     * {
     *
     * 	"tquestionId":9,
     *     "title":"java2",
     *     "selectAItem":"A",
     *     "selectBItem":"B",
     *     "selectCItem":"c",
     *     "selectDItem":"d",
     *     "correct":"A",
     *     "score":"2"
     * }
     */
    @PostMapping
    public Object addTquestion(@RequestBody TquestionItemSelect tquestionItemSelect){
        boolean save = excelService.save(tquestionItemSelect);
        if (save){
            return ApiRes.success("1","添加成功");
        }else {
            return ApiRes.fail("0","添加失败");
        }
    }



    /*只能用于出题人调用，因为会传递过去答案*/
    @GetMapping
    public List getAllSelectById(@RequestParam("tquestionId") int tquestionId){
        QueryWrapper<TquestionItemSelect> tquestionItemSelectQueryWrapper = new QueryWrapper<>();
        tquestionItemSelectQueryWrapper.eq("tquestion_id",tquestionId);
        List<TquestionItemSelect> list = excelService.list(tquestionItemSelectQueryWrapper);
        return list;
    }


}

