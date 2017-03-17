package com.yijiajiao.server.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 各服务配置
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-28-14:31
 */
@Configuration
@ComponentScan("com.yijiajiao.server")
@ImportResource("classpath:memcahed.xml")
public class ServerConfig {

}
