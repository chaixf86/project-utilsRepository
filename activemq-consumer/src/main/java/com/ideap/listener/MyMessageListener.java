package com.ideap.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideap.pojo.MailContent;
import com.ideap.service.MailService;

/**
 * 编写监听器，专门处理消息
 * @author Administrator
 *
 */
@Component(value = "myListener")
public class MyMessageListener implements MessageListener{
	
	@Autowired
	private MailService mailServiceImpl;

	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof ObjectMessage){
				ObjectMessage om = (ObjectMessage)message;
				Object data = om.getObject();
				if(data instanceof MailContent){
					MailContent mailContent = (MailContent)data;
					this.mailServiceImpl.sendMail(mailContent);;
				}
			} else {
				System.out.println(message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
