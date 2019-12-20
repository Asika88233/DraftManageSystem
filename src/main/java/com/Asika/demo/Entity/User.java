package com.Asika.demo.entity;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("draftsys_user")
@Repository
public class User {
    private Integer id;

    private String userId;

    private String userName;

    private String password;

    private Integer auth;
}