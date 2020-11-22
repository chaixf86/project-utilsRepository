package com.ideap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ideap.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageServiceImpl;
	/**
	 * 
	 * @param from	发件人
	 * @param to	收件人
	 * @param subject	邮件主题
	 * @param content	邮件正文
	 * @return
	 */
	@RequestMapping("/sendMessage")
	public String sendMessage(String from, String to, String subject, String content){
		messageServiceImpl.sendMessage(from, to, subject, content);
		return "/ok.jsp";
	}
}
