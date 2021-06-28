package com.ntc.paas.plat.dao;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.UserVote;

public interface UserVoteDao {
    
	int deleteByPrimaryKey(Long id);

    int insert(UserVote record);

    int insertSelective(UserVote record);

    UserVote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserVote record);

    int updateByPrimaryKey(UserVote record);
    
    Page<UserVote> queryListByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    
    UserVote queryByCond(@Param("userId") Long userId, @Param("activityId") Long activityId);
}