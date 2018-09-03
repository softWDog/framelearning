package org.smart4j.chapter2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: gethin
 * @create: 2018-09-01 17:19
 * @description:
 **/
public class PropsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream input = null;
		try {
			input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (input == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(input);
		} catch (IOException e) {
			LOGGER.error("load properties failure", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return props;
	}
	
	/**
	 * 获取字符型属性
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props,String key){
		return getString(props,key,"");
	}
	
	/**
	 * 获取字符型属性（可指定默认值）
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value=defaultValue;
		if (props.contains(key)) {
		      value=props.getProperty(key);
		}
		return value;
	}
	
	public static int getInt(Properties props,String key){
		return getInt(props, key,0);
	}
	
	public static int getInt(Properties props, String key, int defaultValue) {
		int value=defaultValue;
		if (props.contains(key)) {
		      value= Integer.parseInt(props.getProperty(key));
		}
		return value;
	}
	
	public static boolean getBoolean(Properties props,String key){
		return getBoolean(props, key,false);
	}
	
	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value=defaultValue;
		if (props.contains(key)) {
			value= Boolean.parseBoolean(props.getProperty(key));
		}
		return value;
	}
	
}
