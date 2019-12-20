package com.Asika.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Asika.demo.domain.vo.LoginResultVo;

@Controller
public class LoginController {
	// private static final Logger logger =
	// LoggerFactory.getLogger(DraftController.class);
	@RequestMapping("/index")
	public String index(ModelMap map, HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResultVo userLogin(@RequestParam("userName") String userName,
			@RequestParam("passWord") String passWord) {
            Subject subject =SecurityUtils.getSubject();
            LoginResultVo result= new LoginResultVo();
            UsernamePasswordToken token= new UsernamePasswordToken(userName,passWord);
            try {
            	 subject.login(token);
			} catch (UnknownAccountException e) {
				result.setCode("400");
				result.setMsg("用户名不存在");
				return result;
			}catch (IncorrectCredentialsException e) {
				result.setCode("401");
				result.setMsg("密码错误");
				return result;
			}
            result.setCode("500");
            result.setMsg("登陆成功");
           return result;
	}
}
