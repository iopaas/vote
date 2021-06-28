package com.ntc.paas.plat.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 表实体父类
 * 
 * @author allen.yuan
 * @date 2018年5月16日 上午10:36:51
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public abstract class BaseEntity {

	// 创建人
	@JsonIgnore
	@ExcelIgnore
	protected String create_user;

	// 创建时间
	@ExcelProperty(value = "提交时间", index = 16)
	@ColumnWidth(value = 18)
	protected String create_time;

	// 最后修改人
	@JsonIgnore
	@ExcelIgnore
	protected String update_user;

	// 最后修改时间
	@JsonIgnore
	@ExcelIgnore
	protected String update_time;

	public String getCreate_user() {
		return create_user = (create_user == null) ? "" : create_user.trim();
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCreate_time() {
		this.create_time = (create_time == null) ? "" : create_time.trim();
		if (this.create_time.length() > 19) {
			this.create_time = create_time.substring(0, 19);
		}
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_user() {
		return update_user = (update_user == null) ? "" : update_user.trim();
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public String getUpdate_time() {
		this.update_time = (update_time == null) ? "" : update_time.trim();
		if (this.update_time.length() > 19) {
			this.update_time = update_time.substring(0, 19);
		}
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = (update_time == null) ? "" : update_time.trim();
	}
}
