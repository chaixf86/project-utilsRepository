package com.ideap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.ideap.pojo.MailContent;
import com.ideap.service.MailService;

@Service
public class MailContentImpl implements MailService{

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private SimpleMailMessage mailMessage;
	@Autowired
	private ThreadPoolTaskExecutor pool;
	
	@Override
	public void sendMail(final MailContent mail) {
		this.pool.execute(new Runnable() {
			
			@Override
			public void run() {
				mailMessage.setTo(mail.getTo());
				mailMessage.setSubject(mail.getSubject());
				mailMessage.setText(mail.getContent());
				sender.send(mailMessage);
			}
		});
	}

}
