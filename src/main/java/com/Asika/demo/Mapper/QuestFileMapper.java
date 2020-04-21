package com.Asika.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Asika.demo.entity.Quest;
import com.Asika.demo.entity.QuestFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface QuestFileMapper extends BaseMapper<QuestFile> {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestFile record);

    int insertSelective(QuestFile record);

    QuestFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestFile record);

    int updateByPrimaryKey(QuestFile record);
}