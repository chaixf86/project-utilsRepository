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
	 * ������Ϣ��ActiveMQ��
	 * 1����Ҫ���ӣ�connection�����󣬻Ự��session��������Ϣ��������producer������
	 * 2��������Ϣ
	 * ʹ��spring����JMS��ش���ʱ������Ҫ�������ӣ��Ự����Դ����
	 * ʹ��spring�ṩ��JMSTemplate����ʵ�ַ���
	 * JMSTemplate - ��spring��װ��һ����ר�ŷ���MOM������ģ��������ж������ɷ�����ʵ����Ϣ�ķ��ͺͽ���
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
			 * ����һ��Ҫ���͵���Ϣ����
			 * �����������Ϣ�Ķ���
			 * template�Զ�����Ϣ�����͵�MOM������
			 */
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createObjectMessage(mail);
				return message;
			}
		});
	}
}
