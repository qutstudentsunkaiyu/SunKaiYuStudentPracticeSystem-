package com.exercise.budgetreal.mapper;

import com.exercise.budgetreal.entity.TquestionItemWrite;
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
public interface TquestionItemWriteMapper extends BaseMapper<TquestionItemWrite> {
    public void  addUser(@Param("list")List<TquestionItemWrite> list);

    @Select("select * from tquestion_item_write  order by rand() limit  ${num}")
    List<TquestionItemWrite> getListRandom(@Param("num") int num);
}
