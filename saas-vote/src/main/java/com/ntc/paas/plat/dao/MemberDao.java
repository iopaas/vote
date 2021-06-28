package com.ntc.paas.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.Member;
import com.ntc.paas.plat.model.MemberQuery;

public interface MemberDao {

	int deleteByPrimaryKey(Long id);

	int insert(Member record);

	int insertSelective(Member record);

	Member selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Member record);

	int updateByPrimaryKey(Member record);

	Page<Member> queryListByPage(MemberQuery query);
 
	List<Member> selectList();

	List<Member> selectListByIds(@Param("ids") List<Long> ids);
}