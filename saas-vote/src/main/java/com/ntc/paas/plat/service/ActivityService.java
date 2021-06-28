package com.ntc.paas.plat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.dao.ActivityDao;
import com.ntc.paas.plat.dao.ActivityMemberDao;
import com.ntc.paas.plat.dao.UserDao;
import com.ntc.paas.plat.dao.UserVoteDao;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.ActivityExt;
import com.ntc.paas.plat.entity.ActivityMember;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.entity.UserVote;
import com.ntc.paas.plat.model.ActivityQuery;
import com.ntc.paas.plat.model.VoteUserQuery;

/**
 * 选举活动业务方法
 * 
 * @author allen.yuan
 * @date 2021年6月23日 上午10:06:40
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
@Service
public class ActivityService {

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private ActivityMemberDao activityMemberDao;

	@Autowired
	private UserVoteDao userVoteDao;

	@Autowired
	private UserDao userDao;

	@Transactional
	public int insert(Activity record) throws BusinessException {

		record.setStatus(StatusEnum.Enable.getStatus());
		record.setCreateTime(DateUtil.getDateTime());
		record.setUpdateTime(record.getCreateTime());
		activityDao.insert(record);

		record.getMemberIds().forEach(memberId -> {
			ActivityMember actMember = new ActivityMember(0L, record.getId(), memberId, record.getCreateTime(),
					record.getUpdateTime());
			activityMemberDao.insert(actMember);

		});
		return ConmmonEnum.SUCCESS.getCode();
	}

	public int deleteByPrimaryKey(Long id) throws BusinessException {

		return activityDao.deleteByPrimaryKey(id);
	}

	public Activity selectByPrimaryKey(Long id) throws BusinessException {

		return activityDao.selectByPrimaryKey(id);
	}

	@Transactional
	public int updateByPrimaryKey(Activity record) throws BusinessException {

		int result = activityDao.updateByPrimaryKey(record);
		activityMemberDao.deleteByActivityId(record.getId());
		record.getMemberIds().forEach(memberId -> {
			ActivityMember actMember = activityMemberDao.selectByActMemberId(record.getId(),memberId);
			if(actMember == null){
			   actMember = new ActivityMember(0L, record.getId(), memberId, record.getCreateTime(),
					record.getUpdateTime());
			  activityMemberDao.insert(actMember);
			}

		});
		return result;
	}

	public Page<Activity> queryListByPage(ActivityQuery query) throws BusinessException {

		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return activityDao.queryListByPage(query);
	}

	public Page<User> queryVoteListForPage(VoteUserQuery query) throws BusinessException {

		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return userDao.queryListByPage(query);
	}

	public ActivityExt selectExtById(Long id) throws BusinessException {

		ActivityExt actExt = new ActivityExt();
		Activity vo = activityDao.selectByPrimaryKey(id);
		if (vo != null) {
			BeanUtils.copyProperties(vo, actExt);
			actExt.setActMemberList(activityMemberDao.selectExistList(id));
			List<Long> mIds =actExt.getActMemberList().stream().filter(m->m.getExistFlag().equals(1)).map(ActivityMember::getMemberId).
					collect(Collectors.toList());
			actExt.setMemberIds(mIds);
		} else {
			actExt.setActMemberList(activityMemberDao.selectAllList());
		}
		return actExt;
	}

	public ActivityExt selectById(Long id, Long userId) throws BusinessException {

		ActivityExt actExt = new ActivityExt();
		Activity vo = activityDao.selectByPrimaryKey(id);
		if (vo != null) {
			BeanUtils.copyProperties(vo, actExt);
			actExt.setActMemberList(activityMemberDao.selectListByActId(id));
			if (userId != null) {
				UserVote vote = userVoteDao.queryByCond(userId, id);
				actExt.setVoteFlag((vote == null) ? 0 : 1);
			}
		}
		return actExt;
	}

	public List<ActivityExt> queryList(Long userId, Integer pageNum, Integer pageSize) throws BusinessException {

		PageHelper.startPage(pageNum, pageSize);
		return activityDao.queryList(userId);
	}

	public List<ActivityMember> queryMemberList(Long activityId) throws BusinessException {

		if (activityId == null || activityId == 0) {
			return activityMemberDao.selectAllList();
		}
		return activityMemberDao.selectExistList(activityId);
	}

}
