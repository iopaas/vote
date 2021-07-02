package com.ntc.paas.plat.handler;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.dao.ActivityDao;
import com.ntc.paas.plat.dao.ActivityMemberDao;
import com.ntc.paas.plat.dao.UserDao;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.ActivityMember;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.model.ToEmail;
import com.ntc.paas.plat.model.VoteUserQuery;

/**
 * 异步发送邮件
 * 
 * @author allen.yuan
 *
 */
@EnableAsync
@Service
public class MailSenderTask {

	private static final Logger log = LoggerFactory.getLogger(MailSenderTask.class);
	public static final ThreadPoolExecutor clientThread = new ThreadPoolExecutor(1, 32, 60, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());// 接收消息推送线程
	@Value("${spring.mail.from:}")
	private String from;
	@Autowired
	private MailSenderHandler mailSenderHandler;
	@Autowired
	private ActivityDao ativityDao;
	@Autowired
	private ActivityMemberDao activityMemberDao;
	@Autowired
	private UserDao userDao;

	@Async
	public void toSendMail(Long activityId) {

		log.info("[发送邮件]activityId={}", activityId);
		Activity activity = ativityDao.selectByPrimaryKey(activityId);
		VoteUserQuery query = new VoteUserQuery();
		query.setActivityId(activityId);
		Page<User> uList = userDao.queryListByPage(query);
		if (uList == null || CollectionUtils.isEmpty(uList.getResult())) {
			return;
		}

		List<ActivityMember> mList = activityMemberDao.selectListByActId(activityId);
		if (mList == null || mList.size() == 0) {
			return;
		}

		String html = "<html>" + "<body>" + "    <h5>【投票信息】</h5>" + "    <li>" + activity.getActivityTitle()
				+ activity.getActivityDesc() + "</li>";
		html = html + "<h5>【投票结果】<h5>";
		for (ActivityMember member : mList) {
			html = html + "<li><span>名字/英文:</span><span style='margin-left:10px;'>" + member.getCandidateName()
					+ "</span><span  style='margin-left:50px;'>票数:</span><span  style='margin-left:10px;'>"
					+ member.getVotes() + "<span><li>\n";
		}
		html = html + "</body>";
		html = html + "</html>";
		log.info("[发送邮件]content={}", html);
		final String content = html;
		List<User> user = uList.getResult();
		user.forEach(m -> {
			try {
				ToEmail toEmail = new ToEmail(from, new String[] { m.getEmail() }, "【投票结果】-通知", content);
				mailSenderHandler.sendEmail(toEmail);
				log.info("[发送邮件]成功,tos={}", m.getEmail());
			} catch (Exception e) {
				log.error("[发送邮件]异常,error={}", e);
			}
		});
	}
}
