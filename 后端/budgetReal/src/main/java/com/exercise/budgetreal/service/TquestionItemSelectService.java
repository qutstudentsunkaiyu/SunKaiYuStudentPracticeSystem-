package com.exercise.budgetreal.service;

import com.exercise.budgetreal.entity.TquestionItemSelect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.budgetreal.model.vo.ReturnInfoUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface TquestionItemSelectService extends IService<TquestionItemSelect> {

    public ReturnInfoUtil getExcelInfo(String fileName, MultipartFile file, long tId) throws Exception ;
}
