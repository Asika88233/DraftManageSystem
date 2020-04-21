package com.Asika.demo.entity;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("quest_file")
@Repository
public class QuestFile {
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    private Integer num;

    private Integer isPassed;

    private String questReceive;

    private String path;

}