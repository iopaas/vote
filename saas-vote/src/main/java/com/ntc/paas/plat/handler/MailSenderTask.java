package com.ntc.paas.plat.handler;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.ActivityMember;
import com.ntc.paas.plat.model.ActivityQuery;
import com.ntc.paas.plat.model.ToEmail;
import com.ntc.paas.plat.service.ActivityService;

/**
 * 异步发送邮件
 * @author allen.yuan
 *
 */
@EnableAsync
@Service
public class MailSenderTask {

	private static final Logger log = LoggerFactory.getLogger(MailSenderTask.class);
	@Value("${spring.mail.from:}")
	private String from;
	
	@Autowired
	private MailSenderHandler mailSenderHandler;
	@Autowired
	private ActivityService activityService = null;
	
	@Scheduled(cron = "* * */1 * * ?")
	public void exectorTask() throws MessagingException {

		try {
			
			ActivityQuery query= new  ActivityQuery(0, 10, DateUtil.getDateTime());
			while(true) {
				query.setPageNum(query.getPageNum()+1);
				Page<Activity> pages = activityService.queryListByPage(query);
				if(pages == null || CollectionUtils.isEmpty(pages.getResult())){
					return;
				}
				List<Activity> activitys = pages.getResult();
				activitys.forEach(act->{
					transMail((act.getActivityTitle()+act.getActivityDesc()),act.getActMemberList());
				});
			}
		
		} catch (Exception e) {
			log.error("[定时发送邮件]异常,error={}", e);
		}
		
	  }
	
	private void transMail(String activityConent, List<ActivityMember> members) {
		
		
		members.forEach(m->{
			try {
			String content = "<html>\n" +
			            "<body>\n" +
			            "    <h1>【投票信息】</h1>\n" +
			            "    <li>"+activityConent+"</li>\n" +
			            "    <h1>【投票结果】<h1>\n" ;
			content = content +  "</body>\n";
			content = content +         "</html>";
		
			ToEmail toEmail= new ToEmail(from,new String[] {m.getUserEmail()},"【投票结果】-通知",content);
			mailSenderHandler.sendEmail(toEmail);
			} catch (Exception e) {
				log.error("[定时发送邮件]异常,error={}", e);
			}
		});
	
	}
}
