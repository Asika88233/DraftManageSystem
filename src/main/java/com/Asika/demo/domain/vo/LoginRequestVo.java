package com.Asika.demo.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestVo {
    private String userName;
    private String passWord;
    private String verifyCode;
}
