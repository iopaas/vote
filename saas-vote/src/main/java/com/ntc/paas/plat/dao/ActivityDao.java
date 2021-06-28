package com.ntc.paas.plat.dao;

import java.util.List;
import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.ActivityExt;
import com.ntc.paas.plat.model.ActivityQuery;

public interface ActivityDao {

	int deleteByPrimaryKey(Long id);

	int insert(Activity record);

	int insertSelective(Activity record);

	Activity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Activity record);

	int updateByPrimaryKeyWithBLOBs(Activity record);

	int updateByPrimaryKey(Activity record);

	Page<Activity> queryListByPage(ActivityQuery query);

	List<ActivityExt> queryList(Long userId);
}