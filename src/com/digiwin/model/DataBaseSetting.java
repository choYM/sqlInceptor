package com.digiwin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseSetting {
	/****
	 * DB Connetion設定(後續抽出為共用Class)
	 ****************************************************************************************************************************************/
	/**
	 * SQL DB DRIVER
	 */
//	private static final String DB_DRIVER = "com.mysql.jdbc.Driver"; //MySql
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver"; //Oracle
	
	/**
	 * DB名稱
	 */
//	public static final String DB_NAME = "B2B_DEV";
	private String DB_NAME = "";
	
	/**
	 * DB SID
	 */
	private String DB_SID = "";
//	public static final String DB_SID = "orcl";
//	public static final String DB_SID = "b2b";
	
	/**
	 * DB IP
	 */
	private String DB_IP = "";
//	private static final String DB_IP = "10.40.41.217";
//	private static final String DB_IP = "10.40.41.247";
	
	/**
	 * DB PORT
	 */
	private String DB_PORT = "";
//	private static final String DB_PORT = "1521";
	
	/**
	 * 使用者名稱
	 */
	private String USER_NAME = "";
//	private static final String USER_NAME = "system";

	/**
	 * 使用者密碼
	 */
	private String PASSWORD = "";
//	private static final String PASSWORD = "manager";
	
	public DataBaseSetting(String dbIP ,String dbPort ,String dbSID ,String user ,String password ,String dbName) {
		DB_IP = dbIP;
		DB_PORT = dbPort;
		DB_SID = dbSID;
		DB_NAME = dbName;
		USER_NAME = user;
		PASSWORD = password;
	}

	/****
	 * DB Connetion設定
	 ****************************************************************************************************************************************/
	public String getDB_NAME() {
		return DB_NAME;
	}

	public void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}

	public String getDB_SID() {
		return DB_SID;
	}

	public void setDB_SID(String dB_SID) {
		DB_SID = dB_SID;
	}

	public String getDB_IP() {
		return DB_IP;
	}

	public void setDB_IP(String dB_IP) {
		DB_IP = dB_IP;
	}

	public String getDB_PORT() {
		return DB_PORT;
	}

	public void setDB_PORT(String dB_PORT) {
		DB_PORT = dB_PORT;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public static String getDbDriver() {
		return DB_DRIVER;
	}
	
	//	public static final Connection getConnection() throws SQLException, ClassNotFoundException {
	//	Class.forName(DB_DRIVER);
	//	Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	//	return connection;
	//}
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		/**
		 * DB連線URL
		 */
	//	private static final String DB_URL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
		String DB_URL = "jdbc:oracle:thin:@" + getDB_IP() + ":" + getDB_PORT() + ":" + getDB_SID();
		Connection connection = DriverManager.getConnection(DB_URL, getUSER_NAME(), getPASSWORD());
		return connection;
	}
}
