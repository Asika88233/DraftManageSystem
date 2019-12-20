package com.Asika.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Asika.demo.entity.Draft;
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
}
