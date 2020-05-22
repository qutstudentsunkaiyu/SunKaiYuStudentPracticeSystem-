package com.exercise.budgetreal.mapper;

import com.exercise.budgetreal.entity.TquestionItemSelect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface TquestionItemSelectMapper extends BaseMapper<TquestionItemSelect> {
    void  addUser(@Param("list")List<TquestionItemSelect> list);

   @Select("select * from tquestion_item_select  order by rand() limit  ${num}")
   List<TquestionItemSelect> getListRandom(@Param("num") int num);

}
