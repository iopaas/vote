package com.ntc.paas.plat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.dao.MemberDao;
import com.ntc.paas.plat.entity.Member;
import com.ntc.paas.plat.model.MemberQuery;

/**
 * 被先投票人管理业务方法
 * 
 * @author allen.yuan
 * @date 2021年6月23日 上午10:21:40
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	public int insert(Member vo) throws BusinessException {

		vo.setStatus(StatusEnum.Enable.getStatus());
		vo.setCreateTime(DateUtil.getDateTime());
		vo.setUpdateTime(vo.getCreateTime());
		return memberDao.insert(vo);
	}

	public int deleteByPrimaryKey(Long id) throws BusinessException {

		return memberDao.deleteByPrimaryKey(id);
	}

	public Member selectByPrimaryKey(Long id) throws BusinessException {

		return memberDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Member record) throws BusinessException {

		return memberDao.updateByPrimaryKey(record);
	}

	public List<Member> queryList() throws BusinessException {
		
		return memberDao.selectList( );
	}

	public Page<Member> queryListByPage(MemberQuery query) throws BusinessException {
		
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return memberDao.queryListByPage(query);
	}

}
