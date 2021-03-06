package com.Asika.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Asika.demo.entity.Draft;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface DraftMapper  extends BaseMapper<Draft>{
    int deleteByPrimaryKey(Integer id);

    int insert(Draft record);

    int insertSelective(Draft record);

    Draft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Draft record);

    int updateByPrimaryKey(Draft record);
}