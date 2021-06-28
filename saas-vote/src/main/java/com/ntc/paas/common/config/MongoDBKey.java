package com.ntc.paas.common.config;

/**
 * MongoDB key 配置
 * 
 * @author allen.yuan
 * @date 2018年6月29日 下午5:28:06
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public interface MongoDBKey {

	// 存储SaaS-IOT传感器信息表
	String SAAS_SENSORS_DATA_INFO = "psaas_sensors_data";

	// 存储SaaS监控设备信息表
	String SAAS_EQUIPMENT_MONITOR_INFO = "psaas_equipment_monitor";

	// 存储SaaS监控预警信息表
	String SAAS_EQUIPMENT_ALARM_INFO = "psaas_equipment_alarm";

}
