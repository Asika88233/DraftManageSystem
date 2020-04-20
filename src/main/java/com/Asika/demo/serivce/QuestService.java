package com.Asika.demo.serivce;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.Asika.demo.domain.vo.ResponseVo;
import com.Asika.demo.entity.Quest;
import com.Asika.demo.mapper.QuestMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class QuestService extends ServiceImpl<QuestMapper,Quest>{
  public ResponseVo getQuest() {
	  
	  QueryWrapper<Quest> warpper=new QueryWrapper<Quest>();
	  warpper.isNull("quest_receive");
	  ResponseVo result= ResponseVo.success();
	  result.setData(this.list(warpper));
	  return result;
	  
}
  public ResponseVo questReceive(String QuestId) {
	  Subject subject =SecurityUtils.getSubject();
	  Session session =subject.getSession();
	  String userId=(String) session.getAttribute("userId");
	  Quest quest=new Quest();
	  quest.setId(Integer.valueOf(QuestId));
	  quest.setQuestReceive(userId);
	  this.updateById(quest);
	  ResponseVo result= ResponseVo.success();
	  result.setData("任务领取成功");
	  return result;
}
}
