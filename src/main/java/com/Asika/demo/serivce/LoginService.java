package com.Asika.demo.serivce;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Asika.demo.domain.vo.LoginRequestVo;
import com.Asika.demo.domain.vo.LoginResultVo;
import com.Asika.demo.domain.vo.ResponseVo;
import com.google.code.kaptcha.Constants;

@Service
public class LoginService {
      public ResponseVo Login(LoginRequestVo request,HttpServletRequest httpRequest) {
    	  Subject subject =SecurityUtils.getSubject();
          LoginResultVo result= new LoginResultVo();
          UsernamePasswordToken token= new UsernamePasswordToken(request.getUserName(),request.getPassWord());
          String verifyCode=httpRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
          if (!verifyCode.equals(request.getVerifyCode())) {
          	ResponseVo vo =ResponseVo.error();
				vo.setData(result);
				result.setCode("403");
				result.setMsg("验证码输入错误");
				return vo;
			}
          try {
          	 subject.login(token);
			} catch (UnknownAccountException e) {
				result.setCode("400");
				result.setMsg("用户名不存在");
				ResponseVo vo =ResponseVo.error();
				vo.setData(result);
				return vo;
			}catch (IncorrectCredentialsException e) {
				result.setCode("401");
				result.setMsg("密码错误");
				ResponseVo vo =ResponseVo.error();
				vo.setData(result);
				return vo;
			}
          result.setCode("500");
          result.setMsg("登陆成功");
          ResponseVo vo =ResponseVo.success();
			vo.setData(result);
         return vo;
	}
}
