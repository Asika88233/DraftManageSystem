package com.Asika.demo.serivce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Asika.demo.domain.vo.LoginRequestVo;
import com.Asika.demo.domain.vo.LoginResultVo;
import com.Asika.demo.domain.vo.ResponseVo;
import com.Asika.demo.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Constants;

@Service
public class LoginService {
	@Autowired
	UserService userService;
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
		  Session session =subject.getSession();
		  QueryWrapper<User> warpper=new QueryWrapper<User>();
		  warpper.eq("user_id",request.getUserName());
		  warpper.last("limit 1");
		  List<User> list =userService.list(warpper);
		  session.setAttribute("userName", list.get(0).getUserName());
		  session.setAttribute("userId", list.get(0).getUserId());
		  session.setAttribute("auth", list.get(0).getAuth());
          return vo;
	}
}
