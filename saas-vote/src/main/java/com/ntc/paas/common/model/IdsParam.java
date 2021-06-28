package com.ntc.paas.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Long型的参数列表-实体
 * 
 * @author allen.yuan
 * @date 2018年7月31日 上午10:54:48
 * @version 中设E-电梯平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class IdsParam {

	private List<String> ids;

	public List<String> getIds() {
		if (ids == null) {
			ids = new ArrayList<String>();
		}
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
