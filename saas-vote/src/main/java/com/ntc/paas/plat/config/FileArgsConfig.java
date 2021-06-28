package com.ntc.paas.plat.config;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 加载配置项-参数
 * 
 * @author allen.yuan
 * @date 2018年5月17日 上午9:38:01
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
@Configuration
@Order(2)
public class FileArgsConfig {

	private static final Logger log = LoggerFactory.getLogger(FileArgsConfig.class);

	@Value("${spring.servlet.multipart.max-file-size:}")
	private String maxFileSize;

	@Value("${spring.servlet.multipart.max-request-size:}")
	private String maxRequestSize;

	@Value("${config.file.exporttmp.path:}")
	private String exportTmpPath;

	@Value("${config.file.exporttmp.baseUrl:}")
	private String exportTmpBaseUrl;

	public String getExportTmpPath() {
		return exportTmpPath;
	}

	public String getExportTmpBaseUrl() {
		return exportTmpBaseUrl;
	}

	/**
	 * 文件上传配置
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public MultipartConfigElement multipartConfigElement() {

		try {

			log.info("初始化文件上传配置参数开始======");
			log.info("初始化文件上传配置参数maxFileSize={}", maxFileSize);
			log.info("初始化文件上传配置参数maxRequestSize={}", maxRequestSize);
			log.info("初始化文件导出配置参数exportTmpPath={}", exportTmpPath);
			log.info("初始化文件导出配置参数exportTmpBaseUrl={}", exportTmpBaseUrl);

			MultipartConfigFactory factory = new MultipartConfigFactory();
			factory.setMaxFileSize(maxFileSize);
			factory.setMaxRequestSize(maxRequestSize);
			return factory.createMultipartConfig();
		} finally {
			log.info("初始化文件上传配置参数结束======");
		}

	}

}
