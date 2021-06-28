package com.ntc.paas.plat.handler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.plat.model.ToEmail;

/**
 * 异步发送邮件
 * @author allen.yuan
 *
 */
@EnableAsync
@Service
public class MailSenderHandler {

	private static final Logger log = LoggerFactory.getLogger(MailSenderHandler.class);
	@Autowired
	private JavaMailSender mailSender;
	
	@Async("taskScheduler")
	public int sendEmail(ToEmail toEmail) throws MessagingException {
	   
		//创建一个MINE消息
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
	    //发件人
	    minehelper.setFrom(toEmail.getFrom());
	    //收件人
	    minehelper.setTo(toEmail.getTos());
	    //邮件主题
	    minehelper.setSubject(toEmail.getSubject());
	    //邮件内容   true 表示带有附件或html
	    minehelper.setText(toEmail.getContent(), true);
	    
	    try {
	      mailSender.send(message);
	      return ConmmonEnum.SUCCESS.getCode();
	    } catch (MailException e) {
	      log.error("[MailSender]失败,error={}",e );
	    }
	    return ConmmonEnum.FAILURE.getCode();
	  }
 
}
