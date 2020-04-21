package com.Asika.demo.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Asika.demo.util.MenuUtil;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/menu")
public class MenuController {
   private static final Logger logger =LoggerFactory.getLogger(DraftController.class);
   @ResponseBody
   @GetMapping("/getMenu")
   public JSONArray getMenu() {
	   try {
		return MenuUtil.readMenuToJson("student");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.error("目录信息读取失败");
		e.printStackTrace();
	}
	   return null;
   }
   @RequestMapping("/changePass")
   public String changePass(ModelMap map, HttpServletRequest request) {
   	return "changePass";
   }
   @RequestMapping("/personInfo")
   public String personInfo(ModelMap map, HttpServletRequest request) {
	   map.addAttribute("birefNum", "12");
   	return "personInfo";
   }
   @RequestMapping("/questReceive")
   public String questRecive(ModelMap map, HttpServletRequest request) {
	   	return "questRecive";
   }
   @RequestMapping("/questUpdate")
   public String questUpdate(ModelMap map, HttpServletRequest request) {
	   	return "questUpdate";
  }
}
