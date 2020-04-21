package com.Asika.demo.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.Asika.demo.domain.vo.LoginRequestVo;
import com.Asika.demo.domain.vo.PasswordEditVo;
import com.Asika.demo.domain.vo.ResponseVo;
import com.Asika.demo.serivce.LoginService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class LoginController {
	@Autowired
    private Producer captchaProducer;
    @Autowired 
    private LoginService loginService;
    
    private static final Logger logger =LoggerFactory.getLogger(LoginController.class);
	@RequestMapping("/login")
	public String index(ModelMap map, HttpServletRequest request) {
		return "login";
	}
	@ResponseBody
	@PostMapping("/passwordEdit")
	public ResponseVo passwordEdit(@RequestBody PasswordEditVo request) {
		return loginService.passwordEdit(request);
	}
	@RequestMapping("/logout")
	public String logout() {
		  Subject subject = SecurityUtils.getSubject();
		  Session session = subject.getSession();
		  String userName=(String) session.getAttribute("userName");
		  subject.logout();
		  logger.info("当前用户注销,userid={}",userName);
		  return "login";
	}
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo userLogin(@RequestBody LoginRequestVo request,HttpServletRequest httpRequest) {
          return loginService.Login(request, httpRequest);
	}
	@RequestMapping(value ="/getCaptcha",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getCaptcha(HttpServletRequest request, HttpServletResponse response)
		throws IOException{
		response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        logger.info("当前用户的验证码为{}",request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString());
        return null;
	}
}
