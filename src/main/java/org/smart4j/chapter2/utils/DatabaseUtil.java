package org.smart4j.chapter2.utils;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: gethin
 * @create: 2018-09-01 22:59
 * @description:
 **/
public class DatabaseUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver");
		}
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("get connection failure ", e);
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure ", e);
			}
		}
	}
	
	/**
	 * 查询实体列表
	 *
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList;
		Connection connection = getConnection();
		try {
			entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
		} catch (Exception e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		return entityList;
	}
	
	/**
	 * 查询实体
	 *
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity;
		Connection connection = getConnection();
		try {
			entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		return entity;
	}
	
	/**
	 * 执行查询语句
	 *
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> excuteQuery(String sql, Object... params) {
		List<Map<String, Object>> result;
		Connection connection = getConnection();
		try {
			result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	/**
	 * 执行更新语句
	 *
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int row = 0;
		Connection connection = getConnection();
		try {
			row = QUERY_RUNNER.update(connection, sql, params);
		} catch (SQLException e) {
			LOGGER.error("execute update failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		return row;
	}
	
	
	/**
	 * 直接插入实体
	 *
	 * @param instance
	 * @return
	 */
	public static boolean insert(Object instance) {
		Map<String, Object> fieldMap = getDefaultFieldMap(instance);
		return insertEntity(instance.getClass(), fieldMap);
	}
	
	
	/**
	 * 根据映射关系插入实体
	 *
	 * @param entityClass
	 * @param fieldMap
	 * @param <T>
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (MapUtils.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity:fieldMap is empty");
			return false;
		}
		String sql = "insert into " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " values " + values;
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	public static boolean update(Object instance) {
		Map<String, Object> fieldMap = getDefaultFieldMap(instance);
		boolean result = false;
		try {
			long id = Long.valueOf(String.valueOf(instance.getClass().getDeclaredField("id").get(instance)));
			result = updateEntity(instance.getClass(), id, fieldMap);
		} catch (IllegalAccessException e) {
			LOGGER.error("can not access entity primary key id", e);
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新实体
	 *
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @param <T>
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (MapUtils.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity:fieldMap is empty");
			return false;
		}
		String sql = "UPDATE " + getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	/**
	 * 删除实体
	 *
	 * @param entityClass
	 * @param id
	 * @param <T>
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM " + getTableName(entityClass) + " where id=?";
		return executeUpdate(sql, id) == 1;
	}
	
	
	/**
	 * 获得column名以及对应值的关系
	 *
	 * @param instance
	 * @return
	 */
	public static Map<String, Object> getDefaultFieldMap(Object instance) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class clazz = instance.getClass();
		try {
			Field[] fieldList = clazz.getDeclaredFields();
			for (Field field : fieldList) {
				field.setAccessible(true);
				Object value = field.get(instance);
				String key = field.getName();
				map.put(camel2Underline(key), value);
			}
		} catch (IllegalAccessException e) {
			LOGGER.error("get default entity field map failure", e);
			throw new RuntimeException(e);
		}
		return map;
		
	}
	
	/**
	 * 驼峰法转下划线
	 *
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line) {
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase()
				.concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}
	
	private static <T> String getTableName(Class<T> entityClass) {
		return entityClass.getSimpleName();
	}
	
	public static void main(String[] args) {
		System.out.println(camel2Underline("student"));
	}
}
