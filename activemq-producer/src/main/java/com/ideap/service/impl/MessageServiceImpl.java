package com.ideap.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.ideap.pojo.MailContent;
import com.ideap.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private JmsTemplate template;
	
	/**
	 * 发送消息到ActiveMQ中
	 * 1、需要连接（connection）对象，会话（session）对象，消息发送器（producer）对象
	 * 2、发送消息
	 * 使用spring整合JMS相关代码时，不需要定义连接，会话等资源对象。
	 * 使用spring提供的JMSTemplate对象实现访问
	 * JMSTemplate - 是spring封装的一个，专门访问MOM容器的模板对象，其中定义若干方法，实现消息的发送和接收
	 * 
	 */
	@Override
	public void sendMessage(String from, String to, String subject, String content) {
		if(to.indexOf(";") > -1){
			String[] tos = to.split(";");
			for(String t : tos){
				if(null == t && "".equals(t.trim())){
					continue;
				}
				final MailContent mail= this.transfer2Mail(from, t, subject, content);
				this.sendMail(mail);
			}
		} else {
			final MailContent mail= this.transfer2Mail(from, to, subject, content);
			this.sendMail(mail);
		}
	}
	
	private MailContent transfer2Mail(String from, String to, String subject, String content){
		MailContent mail = new MailContent();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setContent(content);
		return mail;
	}

	private void sendMail(final MailContent mail){
		template.send(new MessageCreator() {
			/**
			 * 创建一个要发送的消息对象
			 * 并返回这个消息的对象
			 * template自动将消息对象发送到MOM容器中
			 */
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createObjectMessage(mail);
				return message;
			}
		});
	}
}
