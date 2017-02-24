package com.digiwin.model;

/**
 * DB連線設定的Bean
 * @author choYM
 * @since 2016-09-14
 */
public class DBConfig {
	/**
	 * 主機名稱
	 */
	private String hostIP;
	
	/**
	 * 連接埠
	 */
	private String port;
	
	/**
	 * SID
	 */
	private String sid;
	
	/**
	 * 資料庫名稱
	 */
	private String dbName;
	
	/**
	 * 使用者名稱
	 */
	private String userName;
	
	/**
	 * 密碼
	 */
	private String password;

	public DBConfig(String hostIP, String port, String sid, String dbName, String userName, String password) {
		super();
		this.hostIP = hostIP;
		this.port = port;
		this.sid = sid;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DBConfig [hostIP=" + hostIP + ", port=" + port + ", sid=" + sid + ", dbName=" + dbName + ", userName="
				+ userName + ", password=" + password + "]";
	}
}
