/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 下午1:36:50
 */
package com.nickboyer.core.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @title
 * @description
 * @since JDK1.8
 */
@Configuration
@ConfigurationProperties(prefix = "cust", ignoreUnknownFields = true)
public class Properties {

}
