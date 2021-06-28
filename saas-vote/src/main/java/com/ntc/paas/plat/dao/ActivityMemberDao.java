package com.ntc.paas.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntc.paas.plat.entity.ActivityMember;

public interface ActivityMemberDao {

	int deleteByPrimaryKey(Long id);
	
	int deleteByActivityId(@Param("activityId") Long activityId);

	int insert(ActivityMember record);

	int insertSelective(ActivityMember record);

	ActivityMember selectByPrimaryKey(Long id);
	
	ActivityMember selectByActMemberId(@Param("activityId") Long activityId, @Param("memberId") Long memberId);

	int updateByPrimaryKeySelective(ActivityMember record);

	int updateByPrimaryKey(ActivityMember record);

	List<ActivityMember> selectListByActId(Long activityId);
	
	List<ActivityMember> selectAllList();
	
	List<ActivityMember> selectExistList(Long activityId);
	
}