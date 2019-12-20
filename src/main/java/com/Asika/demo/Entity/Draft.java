package com.Asika.demo.entity;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("file")
@Repository
public class Draft {
    private Integer id;
    private String title;
    private Integer num;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;
}