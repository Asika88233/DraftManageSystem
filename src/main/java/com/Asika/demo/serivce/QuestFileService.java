package com.Asika.demo.serivce;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.Asika.demo.entity.QuestFile;
import com.Asika.demo.mapper.QuestFileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class QuestFileService  extends ServiceImpl<QuestFileMapper,QuestFile>{
  public void questFileUpload(Integer num,Integer questId,String path) {
	Subject subject =SecurityUtils.getSubject();
	Session session =subject.getSession();
	String userId=(String) session.getAttribute("userId");
	QuestFile questFile=new QuestFile();
	questFile.setIsPassed(0);
	questFile.setNum(num);
	questFile.setTime(new Date(System.currentTimeMillis()));
	questFile.setPath(path);
	questFile.setQuestId(questId);
	questFile.setQuestReceive(userId);
	this.save(questFile);
}
}
