package com.ntc.paas.common.config;

/**
 * Public key 配置-系统
 * 
 * @author allen.yuan
 * @date 2021年6月29日 下午5:28:06
 */
public interface PublicKey {

	// redis key 前缀
	String PAAS_ROOT = "PAAS" + "::";

	// 登录用户redis存储(set)(%s为token)
	String LOGIN_SESSION = PAAS_ROOT + "LOGIN_SESSION::%s";

	// 登录用户redis存储(set)(%s为账号角色,%s为账号ID,%登录时间 )
	String LOGIN_ACCOUNT = PAAS_ROOT + "LOGIN_ACCOUNT";

	// 登录用户redis存储(hashset)
	String REQUEST_API_COUNT = PAAS_ROOT + "REQUEST_API_COUNT";
	
	
	// 存储数据-field(如：%s：主键ID)
	String PS_FIELD = "%s";

	// 存储数据-field(如：%s::%s：用户ID_投票ID)
	String PPS_FIELD = "%s::%s";

	// 存储数据 -field(如：%s_%s：下划线分割参数)
	String P_PS_FIELD = "%s_%s";

	// 存储数据 -field(如：%s_%s_%s：下划线分割参数)
	String P_P_PS_FIELD = "%s_%s_%s";

}
