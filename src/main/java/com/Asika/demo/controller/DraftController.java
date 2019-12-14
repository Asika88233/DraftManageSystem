package com.Asika.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Asika.demo.Entity.Draft;
import com.Asika.demo.serivce.DraftService;

@Controller
@RequestMapping("/draft")
public class DraftController {
	@Autowired
	DraftService draftService;

	@RequestMapping("/getAllDraft")
	@ResponseBody
	public List<Draft> getAllDraft() {
		return draftService.list();
	}

	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("msg", draftService.count());
		return "login";
	}
}
