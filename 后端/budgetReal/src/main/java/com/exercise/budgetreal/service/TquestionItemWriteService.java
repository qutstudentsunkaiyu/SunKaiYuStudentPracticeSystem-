package com.exercise.budgetreal.service;

import com.exercise.budgetreal.entity.TquestionItemWrite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.budgetreal.model.vo.ReturnInfoUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface TquestionItemWriteService extends IService<TquestionItemWrite> {
    public ReturnInfoUtil getExcelInfo(String fileName, MultipartFile file, long tId) throws Exception ;

}
