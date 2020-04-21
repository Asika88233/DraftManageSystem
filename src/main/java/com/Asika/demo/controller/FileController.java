package com.Asika.demo.controller;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Asika.demo.domain.vo.ResponseVo;
import com.Asika.demo.serivce.QuestFileService;
import com.Asika.demo.serivce.QuestService;
import com.Asika.demo.util.WordUtil;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	QuestFileService questFileService;
	@Autowired
	QuestService questService;
	private static final Logger logger =LoggerFactory.getLogger(FileController.class);
	@RequestMapping("/upload")
	@ResponseBody
	public ResponseVo upload (@RequestParam("file") MultipartFile file,@RequestParam("id") Integer questId) {
		// 获取原始名字
		String fileName = file.getOriginalFilename();
		// 获取后缀名
		// String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 文件保存路径
		String filePath = "d:/upload/";
		// 文件重命名，防止重复
		fileName = filePath + UUID.randomUUID() + fileName;
		// 文件对象
		File dest = new File(fileName);
		// 判断路径是否存在，如果不存在则创建
		if(!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			// 保存到服务器中
			file.transferTo(dest);
			int fileNum=WordUtil.findWordNum(dest);
			logger.info("文件名为{},文件字数为{}",fileName,fileNum);
			questFileService.questFileUpload(fileNum,questId,fileName);
			questService.questFileUpload(fileName, questId);
			ResponseVo result= ResponseVo.success();
			result.setMsg("文件上传成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseVo result=ResponseVo.error();
		result.setMsg("文件上传失败");
		return result;
	}
}
