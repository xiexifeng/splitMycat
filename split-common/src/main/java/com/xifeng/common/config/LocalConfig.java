package com.xifeng.common.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;

public class LocalConfig {
	private static final Log logger = CommonLogger.getInstance();
	public static String CONFIG_PATH ;
	
	static{
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream(LocalConfig.class.getResource("/local.properties").getPath()));
		} catch (FileNotFoundException e) {
			logger.error("local.properties fileNotFound ,system will exit");
			System.exit(-1);
		} catch (IOException e) {
			logger.error("read local.properties file error, system will exit");
			System.exit(-1);
		}
		CONFIG_PATH = pro.getProperty("config.path");
	}
}
