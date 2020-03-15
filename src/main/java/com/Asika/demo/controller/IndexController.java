package com.Asika.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Asika.demo.util.MenuUtil;

@Controller
public class IndexController {
	private static final Logger logger =LoggerFactory.getLogger(DraftController.class);
    @RequestMapping("/index")
    public String index(ModelMap map, HttpServletRequest request) {
    	Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	String userName=(String) session.getAttribute("userName");
    	map.addAttribute("userName",userName);
    	try {
			map.addAttribute("menuArray",MenuUtil.readMenuToJson("student"));
		} catch (Exception e) {
			logger.error("目录文件读取失败");
			e.printStackTrace();
		}
    	return "index";
    }
} 
