package com.ntc.paas.common.config;

/**
 * Redis key配置-业务
 * 
 * @author allen.yuan
 * @date 2018年6月29日 下午5:28:06
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public interface RedisKey {

	// redis key 前缀
	String SAAS_ROOT = "PSAAS" + "::";

	// 存储数据-field(如：%s：主键ID)
	String PS_FIELD = "%s";

	// 存储数据-field(如：%s::%s：企业ID_设备ID)
	String PPS_FIELD = "%s::%s";

	// 存储数据 -field(如：%s::%s::%s：企业ID_设备ID_传感器ID)
	String PPPS_FIELD = "%s::%s::%s";

	// 存储数据 -field(如：%s_%s：下划线分割参数)
	String P_PS_FIELD = "%s_%s";

	// 存储数据 -field(如：%s_%s_%s：下划线分割参数)
	String P_P_PS_FIELD = "%s_%s_%s";

	// 企业信息表(ntc_user)(hashset)
	String USER_LIST = SAAS_ROOT + "USER_LIST";

	// 设备信息表(ntc_equipment)(hashset)
	String EQUIPMENT_LIST = SAAS_ROOT + "EQUIPMENT_LIST";

	// 存储SaaS-IOT平台信息表(ntc_iotplat_info)数据 -key
	String SAAS_IOT_INFO = SAAS_ROOT + "SAAS_IOT_INFO";

	// 存储SaaS-IOT平台配置表(ntc_iotplat_config)数据 -key
	String SAAS_IOT_CONFIG = SAAS_ROOT + "SAAS_IOT_CONFIG";

	// 存储IOT平台企业与SaaS平台企业关系(ntc_user_relation) 数据 -key (%s:iot平台ID)
	String SAAS_IOT_UU_RELATION = SAAS_ROOT + "SAAS_IOT_UU_RELATION::%s";

	// 存储IOT平台设备与SaaS平台设备关系(ntc_equipment_relation)数据 -key(%s:iot平台ID)
	String SAAS_IOT_DE_RELATION = SAAS_ROOT + "SAAS_IOT_DE_RELATION::%s";

	// 设备监测配置表(ntc_equipment_mc)(hashset)(%s:设备ID)
	String EQUIPMENT_MC = SAAS_ROOT + "EQUIPMENT_MC::%s";

	// 设备监测项目配置-IOT数据源表(ntc_equipment_mc_ids)(hashset)(%s:EMC_ID)
	String EQUIPMENT_MC_IDS = SAAS_ROOT + "EQUIPMENT_MC_IDS";

	// 设备监测项目配置-数转档表(ntc_equipment_mc)(hashset)(%s:EMC_ID)
	String EQUIPMENT_MC_NTG = SAAS_ROOT + "EQUIPMENT_MC_NTG";

	// 设备监测项目配置-合并22档位表(ntc_equipment_mc_mdg)(hashset)(%s:EMC_ID)
	String EQUIPMENT_MC_MDG = SAAS_ROOT + "EQUIPMENT_MC_MDG";

	// 设备监测配置表(ntc_equipment_mc_ccs)(hashset)(%s:EMC_ID)
	String EQUIPMENT_MC_CCS = SAAS_ROOT + "EQUIPMENT_MC_CCS";

	// 存储传感器实时数据 -key
	String IOT_SENSORS_DATA = SAAS_ROOT + "IOT_SENSORS_DATA";

	// 存储SaaS平台设备传感器表-key
	String EQUIPMENT_SENSORS = SAAS_ROOT + "EQUIPMENT_SENSORS";

	// 存储SaaS平台设备传感实时数据-key
	String EQUIPMENT_SENSORS_DATA = SAAS_ROOT + "EQUIPMENT_SENSORS_DATA";

	// 设备监测项目配置(hashset)
	String EQUIPMENT_MONITOR_CONFIG = SAAS_ROOT + "EQUIPMENT_MONITOR_CONFIG";

	// 存储SaaS平台设备-实时计算数据
	String EQUIPMENT_CALC_INFO = SAAS_ROOT + "EQUIPMENT_CALC_INFO";

}
