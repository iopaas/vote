package com.ntc.paas.plat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.dao.UserVoteDao;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.UserVote;

/**
 * 被先投票人管理业务方法
 * 
 * @author allen.yuan
 * @date 2021年6月23日 上午10:21:40
 */
@Service
public class UserVoteService {

	@Autowired
	private UserVoteDao userVoteDao;
	@Autowired
	private ActivityService activityService;

	public int insert(UserVote record) throws BusinessException {

		// 查询是否已经投递过
		UserVote uVote = userVoteDao.queryByCond(record.getUserId(), record.getActivityId());
		if (uVote != null) {
			return ConmmonEnum.EXISTS.getCode();
		}

		// 检查投递时间是否过期
		Activity activity = activityService.selectByPrimaryKey(record.getActivityId());
		if (activity.getEnabled() != 1) {
			return ConmmonEnum.EXPIRE.getCode();
		}

		record.getMemberIds().forEach(memberId -> {
			record.setMemberId(memberId);
			record.setVoteTime(DateUtil.getDateTime());
			record.setCreateTime(record.getVoteTime());
			record.setUpdateTime(record.getCreateTime());
			userVoteDao.insert(record);
		});
		return ConmmonEnum.SUCCESS.getCode();
	}

	public int deleteByPrimaryKey(Long id) throws BusinessException {

		return userVoteDao.deleteByPrimaryKey(id);
	}

	public UserVote selectByPrimaryKey(Long id) throws BusinessException {

		return userVoteDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(UserVote record) throws BusinessException {

		return userVoteDao.updateByPrimaryKey(record);
	}

	public Page<UserVote> queryListByPage(Integer pageNum, Integer pageSize) throws BusinessException {

		PageHelper.startPage(pageNum, pageSize);
		return userVoteDao.queryListByPage(pageNum, pageSize);
	}

}
