package com.Asika.demo.serivce;

import org.springframework.stereotype.Service;

import com.Asika.demo.entity.User;
import com.Asika.demo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
@Service
public class UserService extends ServiceImpl<UserMapper,User>{
}
