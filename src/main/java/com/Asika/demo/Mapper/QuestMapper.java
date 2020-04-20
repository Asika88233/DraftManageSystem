package com.Asika.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Asika.demo.entity.Quest;
import com.Asika.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface QuestMapper extends BaseMapper<Quest> {
    int deleteByPrimaryKey(Integer id);

    int insert(Quest record);

    int insertSelective(Quest record);

    Quest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Quest record);

}