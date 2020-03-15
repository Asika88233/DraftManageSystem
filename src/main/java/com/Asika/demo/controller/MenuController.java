package com.Asika.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Asika.demo.util.MenuUtil;
import com.alibaba.fastjson.JSONArray;

@RestController
public class MenuController {
   private static final Logger logger =LoggerFactory.getLogger(DraftController.class);
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
}
