package com.Asika.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asika.demo.domain.vo.ResponseVo;
import com.Asika.demo.serivce.QuestService;

@RestController
public class QuestController {
	@Autowired
	private QuestService questService;
   @GetMapping("/getQuest")
   public ResponseVo getQuest(){
	   return questService.getQuest();
   }
   @PostMapping("/questReceive")
   public ResponseVo questReceive(@RequestParam(value="questId",required=true)String questId) {
	   return questService.questReceive(questId);
   }
   @GetMapping("/getQuestReceive")
   public ResponseVo getquestReceive() {
	   return questService.getQuestReceive();
   }
}
