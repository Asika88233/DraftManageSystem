package com.Asika.demo.entity;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("quest")
@Repository
public class Quest {
    private Integer id;

    private String questName;

    private String questBiref;

    private Integer questReward;

    private String questOrder;

    private String questReceive;

    private String path;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date receiveTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date finshTime;
}