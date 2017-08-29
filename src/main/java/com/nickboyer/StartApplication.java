/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 上午10:21:20
 */
package com.nickboyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 程序入口
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@SpringBootApplication
@EnableScheduling
public class StartApplication {

	public static void main(String[] args) {

		SpringApplication.run(StartApplication.class, args);
	}
}
