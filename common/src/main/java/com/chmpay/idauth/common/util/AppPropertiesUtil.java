package com.chmpay.idauth.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件的工具类
 */
public class AppPropertiesUtil {

	private static Properties properties;
	static {
		properties = new Properties();

		try {
			InputStream in = new FileInputStream(System.getProperty("user.home").concat(File.separator).concat("props")
					.concat(File.separator).concat("app.properties"));
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
