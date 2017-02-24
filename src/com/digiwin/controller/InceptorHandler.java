package com.digiwin.controller;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.digiwin.model.DBConfig;
import com.digiwin.model.DataBaseSetting;

public class InceptorHandler extends TimerTask {
	private static final String SELECT_STR = "SELECT last_active_time, sql_id, sql_text ";
	private static final String FROM_STR = "FROM gv$sqlarea ";
	private static final String WHERE_STR = "WHERE parsing_schema_name = ? AND ROWNUM <= ? ";
	private static final String ORDER_STR = "ORDER BY last_active_time desc ";

	/**
	 * db connection data class
	 */
	private DBConfig dbConfig;

	/**
	 * JTable component for showing data
	 */
	private JTable jTable;
	
	/**
	 * JTextField component for adding condition on SQL
	 */
	private JTextField searchTf;
	
	/**
	 * determine the data size for retrieving in a SQL query
	 */
	private int dataSize;

	/**
	 * constructor
	 * @param dbConfig db connection class
	 * @param jTable JTable component for showing data
	 * @param searchTf JTextField component for adding condition on SQL
	 * @param dataSize determine the data size for retrieving in a SQL query
	 */
	public InceptorHandler(DBConfig dbConfig, JTable jTable, JTextField searchTf, int dataSize) {
		super();
		this.dbConfig = dbConfig;
		this.jTable = jTable;
		this.searchTf = searchTf;
		this.dataSize = dataSize;
	}

	@Override
	public void run() {
		try {
			// 1.get data from database
			Connection conn = getConnection(dbConfig);
			String newWhere = appendWhereClause(WHERE_STR);
			PreparedStatement ps = conn.prepareStatement(SELECT_STR + FROM_STR + newWhere + ORDER_STR, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, dbConfig.getDbName());
			ps.setInt(2, dataSize);
			if(!newWhere.equals(WHERE_STR)) {
				ps.setString(3, "%" + searchTf.getText() + "%");
			}
			ResultSet rs = ps.executeQuery();

			// 2.refresh JTable's dataModel
			reloadData(jTable, rs);

			// 3. resize table
//			resizeColumnWidth(jTable);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private String appendWhereClause(String whereStr) {
		StringBuilder sb = new StringBuilder(WHERE_STR);
		if(searchTf.getText() != null && !searchTf.getText().isEmpty()) {
			sb.append("AND sql_text like ? ");
		}
		return sb.toString();
	}

	/**
	 * 與DB連線
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	protected Connection getConnection(DBConfig config) throws ClassNotFoundException, SQLException {
		DataBaseSetting dbs = new DataBaseSetting(config.getHostIP(), config.getPort(), config.getSid(),
				config.getUserName(), config.getPassword(), config.getDbName());
		return dbs.getConnection();
	}

	/**
	 * reload JTable's data
	 * @param table JTable component
	 * @param rs SQL resultSet
	 * @throws SQLException
	 */
	private void reloadData(JTable table, ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		// set column name vector
		Vector<String> colNames = new Vector<String>();
		for (int i = 0; i < columnCount; i++) {
			// JDBC column indexes start from 1 and not 0
			colNames.addElement(rsmd.getColumnName(i + 1));
		}

		// set data vector
		Vector<Object> data = new Vector<>();
		while (rs.next()) {
			Vector<Object> row = new Vector<>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				// 因為sql_fulltext是clob型態 轉換成字串的時候要用
				// new BufferedReader(new InputStreamReader(rs.getObject(i).getAsciiStream())).readLine()
				row.addElement(rs.getObject(i));
			}
			data.addElement(row);
		}

		// set new table model in JTable
		DefaultTableModel tableModel = new DefaultTableModel(data, colNames);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	/**
	 * 自動調整table column的寬度
	 * 
	 * @param table
	 *            JTable元件
	 */
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}
