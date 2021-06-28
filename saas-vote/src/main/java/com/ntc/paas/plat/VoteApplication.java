package com.ntc.paas.plat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = { "com.ntc.paas" })
@MapperScan(basePackages = { "com.ntc.paas.plat.dao" })
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

}
