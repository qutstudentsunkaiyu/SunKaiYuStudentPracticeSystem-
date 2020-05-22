package com.exercise.budgetreal.service.impl;


import com.exercise.budgetreal.entity.TquestionItemWrite;
import com.exercise.budgetreal.mapper.TquestionItemWriteMapper;
import com.exercise.budgetreal.model.vo.ReturnInfoUtil;
import com.exercise.budgetreal.service.TquestionItemWriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.budgetreal.util.ReturnUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuKeGuo
 * @since 2019-01-05
 */
@Service
public class TquestionItemWriteServiceImpl extends ServiceImpl<TquestionItemWriteMapper, TquestionItemWrite> implements TquestionItemWriteService {
    @Autowired
    TquestionItemWriteMapper excelDao;

    /**
     *上传试题
     * @param fileName
     * @param file
     * @return    TquestionItemWrite
     * @throws Exception
     */
    @Override
    public ReturnInfoUtil getExcelInfo(String fileName, MultipartFile file, long tId) throws Exception {
        ReturnInfoUtil returnInfoUtil = new ReturnInfoUtil();
        int[] resultCell = new int[]{0,1,2};//要将表中的哪几列传入数据库中，从0开始计数
        List<TquestionItemWrite> resultList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return ReturnUtil.error("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        resultList = getSheetVal(sheet, resultCell,tId);
        System.out.println("结果集---"+resultList.size());
        System.out.println("结果集---"+resultList);
        try{
            excelDao.addUser(resultList);
            returnInfoUtil = ReturnUtil.success("");
        }catch (Exception e){
            e.printStackTrace();
            returnInfoUtil = ReturnUtil.error("数据导入失败");
        }
        return returnInfoUtil;
    }

    /**
     *
     * @param sheet
     * @param resultCell 需要将哪些列插入至数据库
     * @return
     */
    public List getSheetVal(Sheet sheet, int[] resultCell,long tId){
        List<TquestionItemWrite> bankList = new ArrayList<>();//返回的结果集
        int[] resultIndex = new int[resultCell.length];//存储需要上传字段的下标
        TquestionItemWrite bank;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            bank = new TquestionItemWrite();
            for (int i = 0;i<row.getPhysicalNumberOfCells();i++){
                String temp = getCellVal(row.getCell(i)).toString().trim();
                for (int j=0;j<resultCell.length;j++){

                    if (i==resultCell[j]){
                        bank.setTquestionId((int)tId);
                        switch (i){
                            case 0:
                                bank.setTitle(temp);
                                break;
                            case 1:
                                bank.setAnswer(temp);
                                break;
                            case 2:
                                bank.setScore(temp);
                                break;

                            default:
                                break;
                        }

                    }else{
                        continue;
                    }
                }
            }
            bankList.add(bank);
        }
        return bankList;
    }

    /**
     *
     * @param cell
     * @return
     */
    public Object getCellVal(Cell cell){
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

}
