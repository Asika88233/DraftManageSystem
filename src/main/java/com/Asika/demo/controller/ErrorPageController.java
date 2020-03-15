package com.Asika.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ErrorPageController {
	@RequestMapping("/errorpage/{code}")
	public String errorPage(@PathVariable("code") String code, ModelMap map, HttpServletRequest request) {
		map.addAttribute("code", code);
		return "errorpage";
	}

}