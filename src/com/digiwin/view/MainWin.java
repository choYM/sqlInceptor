package com.digiwin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.digiwin.controller.InceptorHandler;
import com.digiwin.model.DBConfig;
import com.digiwin.model.DataBaseSetting;

public class MainWin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField dbNameTf;
	private JTextField userNameTf;
	private JTextField hostIPTf;
	private JTextField portTf;
	private JTextField sidTf;
	private JPasswordField passwordTf;
	private JPanel panel;
	private JButton executeBtn;
	private JLabel consoleLbl;
	private JLabel label_3;
	private JPanel panel_1;
	private JSpinner frequencySpr;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;

	protected JTable table;
	private JButton terminateBtn;

	protected Timer timer;
	private JLabel label_4;
	private JTextField searchTf;
	private JPanel panel_2;
	private JSpinner dataSizeSpr;
	private JLabel label_5;
	private JButton exportBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin frame = new MainWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWin() {
		setTitle("SQL Inceptor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("連線名稱");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		dbNameTf = new JTextField();
		dbNameTf.setText("B2B_DEV");
		GridBagConstraints gbc_dbNameTf = new GridBagConstraints();
		gbc_dbNameTf.insets = new Insets(0, 0, 5, 0);
		gbc_dbNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbNameTf.gridx = 1;
		gbc_dbNameTf.gridy = 0;
		contentPane.add(dbNameTf, gbc_dbNameTf);
		dbNameTf.setColumns(10);

		JLabel label = new JLabel("使用者名稱");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);

		userNameTf = new JTextField();
		userNameTf.setText("B2B_DEV");
		GridBagConstraints gbc_userNameTf = new GridBagConstraints();
		gbc_userNameTf.insets = new Insets(0, 0, 5, 0);
		gbc_userNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_userNameTf.gridx = 1;
		gbc_userNameTf.gridy = 1;
		contentPane.add(userNameTf, gbc_userNameTf);
		userNameTf.setColumns(10);

		JLabel label_1 = new JLabel("密碼");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		contentPane.add(label_1, gbc_label_1);

		passwordTf = new JPasswordField();
		GridBagConstraints gbc_passwordTf = new GridBagConstraints();
		gbc_passwordTf.insets = new Insets(0, 0, 5, 0);
		gbc_passwordTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTf.gridx = 1;
		gbc_passwordTf.gridy = 2;
		contentPane.add(passwordTf, gbc_passwordTf);

		JLabel lblNewLabel_1 = new JLabel("主機名稱");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		hostIPTf = new JTextField();
		hostIPTf.setText("10.40.41.217");
		GridBagConstraints gbc_hostIPTf = new GridBagConstraints();
		gbc_hostIPTf.insets = new Insets(0, 0, 5, 0);
		gbc_hostIPTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_hostIPTf.gridx = 1;
		gbc_hostIPTf.gridy = 3;
		contentPane.add(hostIPTf, gbc_hostIPTf);
		hostIPTf.setColumns(10);

		JLabel label_2 = new JLabel("連接埠");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 4;
		contentPane.add(label_2, gbc_label_2);

		portTf = new JTextField();
		portTf.setText("1521");
		GridBagConstraints gbc_portTf = new GridBagConstraints();
		gbc_portTf.insets = new Insets(0, 0, 5, 0);
		gbc_portTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_portTf.gridx = 1;
		gbc_portTf.gridy = 4;
		contentPane.add(portTf, gbc_portTf);
		portTf.setColumns(10);

		JLabel lblSid = new JLabel("SID");
		GridBagConstraints gbc_lblSid = new GridBagConstraints();
		gbc_lblSid.anchor = GridBagConstraints.WEST;
		gbc_lblSid.insets = new Insets(0, 0, 5, 5);
		gbc_lblSid.gridx = 0;
		gbc_lblSid.gridy = 5;
		contentPane.add(lblSid, gbc_lblSid);

		sidTf = new JTextField();
		sidTf.setText("orcl");
		GridBagConstraints gbc_sidTf = new GridBagConstraints();
		gbc_sidTf.insets = new Insets(0, 0, 5, 0);
		gbc_sidTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_sidTf.gridx = 1;
		gbc_sidTf.gridy = 5;
		contentPane.add(sidTf, gbc_sidTf);
		sidTf.setColumns(10);

		label_3 = new JLabel("攔截頻率");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 6;
		contentPane.add(label_3, gbc_label_3);

		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 6;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		frequencySpr = new JSpinner();
		frequencySpr.setModel(new SpinnerNumberModel(3000, 3000, 10000, 500));
		frequencySpr.setEditor(new JSpinner.DefaultEditor(frequencySpr));
		panel_1.add(frequencySpr);

		lblNewLabel_2 = new JLabel("毫秒/次");
		panel_1.add(lblNewLabel_2);

		consoleLbl = new JLabel("");
		GridBagConstraints gbc_consoleLbl = new GridBagConstraints();
		gbc_consoleLbl.gridwidth = 2;
		gbc_consoleLbl.anchor = GridBagConstraints.EAST;
		gbc_consoleLbl.insets = new Insets(0, 0, 5, 0);
		gbc_consoleLbl.gridx = 0;
		gbc_consoleLbl.gridy = 7;
		contentPane.add(consoleLbl, gbc_consoleLbl);

		panel = new JPanel();
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		executeBtn = new JButton("開始攔截");
		executeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 檢查必填
				if (checkNull()) {
					setConsoleStr(consoleLbl, "資料不完整! 請重新輸入", Color.orange);
					return;
				} else {
					setConsoleStr(consoleLbl, null, Color.red);
				}

				// 將連線資訊轉成model
				DBConfig dbConfig = new DBConfig(hostIPTf.getText(), portTf.getText(), sidTf.getText(),
						dbNameTf.getText(), userNameTf.getText(), new String(passwordTf.getPassword()));
				if (!testConnection(dbConfig)) {
					setConsoleStr(consoleLbl, "連線失敗! 請重新輸入", Color.red);
					return;
				} else {
					setConsoleStr(consoleLbl, null, Color.red);
				}

				// 啟動攔截thread
				timer = new Timer();
				timer.schedule(new InceptorHandler(dbConfig, table, searchTf, Integer.parseInt(dataSizeSpr.getValue().toString())),
						100, Integer.parseInt(frequencySpr.getValue().toString()));
			}
		});
		panel.add(executeBtn);

		terminateBtn = new JButton("停止攔截");
		terminateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.cancel();
			}
		});
		panel.add(terminateBtn);

		exportBtn = new JButton("匯出");
		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
					try {
						doSaveFile(table, chooser.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(exportBtn);

		label_4 = new JLabel("搜尋條件");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 9;
		contentPane.add(label_4, gbc_label_4);

		searchTf = new JTextField();
		searchTf.setColumns(10);
		GridBagConstraints gbc_searchTf = new GridBagConstraints();
		gbc_searchTf.insets = new Insets(0, 0, 5, 0);
		gbc_searchTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTf.gridx = 1;
		gbc_searchTf.gridy = 9;
		contentPane.add(searchTf, gbc_searchTf);

		label_5 = new JLabel("資料筆數");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 10;
		contentPane.add(label_5, gbc_label_5);

		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 10;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		dataSizeSpr = new JSpinner();
		dataSizeSpr.setModel(new SpinnerNumberModel(100, 100, 300, 50));
		dataSizeSpr.setEditor(new JSpinner.DefaultEditor(dataSizeSpr));
		panel_2.add(dataSizeSpr);

		table = new JTable();
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 11;
		contentPane.add(scrollPane, gbc_scrollPane);
	}

	/**
	 * implement save file
	 * 
	 * @param table
	 * @param selectedFile
	 * @throws IOException
	 */
	protected void doSaveFile(JTable table, File selectedFile) throws IOException {
		XSSFWorkbook workbook = null;
		FileOutputStream fileOut = null;

		try {
			// create excel instance
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();

			// prepare cell style for date cell
			CellStyle cellStyle = workbook.createCellStyle();
			CreationHelper createHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/MM/dd HH:mm:ss"));

			// get data from JTable
			TableModel model = table.getModel();

			// create column name
			Row r = sheet.createRow(0);
			for (int i = 0; i < model.getColumnCount(); i++) {
				Cell c = r.createCell(i);
				c.setCellValue(model.getColumnName(i));
			}

			// put data into sheet
			for (int i = 0; i < model.getRowCount(); i++) {
				Row row = sheet.createRow(i + 1);
				for (int j = 0; j < model.getColumnCount(); j++) {
					Cell cell = row.createCell(j);
					if (model.getValueAt(i, j) instanceof Date) {
						cell.setCellStyle(cellStyle);
						cell.setCellValue((Date) model.getValueAt(i, j));
					} else {
						cell.setCellValue(model.getValueAt(i, j).toString());
					}
				}
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);

			// set file name
			String fileName = selectedFile.getAbsolutePath().indexOf(".") == -1
					? selectedFile.getAbsolutePath() + ".xlsx" : selectedFile.getAbsolutePath();

			// save file
			fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			// show save success dialog
			JOptionPane.showMessageDialog(getContentPane(), "Successfully!", "Success!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null)
				fileOut.close();

			if (workbook != null)
				workbook.close();
		}
	}

	/**
	 * 設定console字串
	 * 
	 * @param label
	 *            label元件
	 * @param string
	 *            輸出文字
	 * @param color
	 *            文字顏色
	 */
	private void setConsoleStr(JLabel label, String string, Color color) {
		label.setText(string);
		label.setForeground(color);
	}

	/**
	 * 檢查填寫的資料是否為空
	 * 
	 * @return
	 */
	protected boolean checkNull() {
		return dbNameTf.getText() == null || dbNameTf.getText().isEmpty() || hostIPTf.getText() == null
				|| hostIPTf.getText().isEmpty() || portTf.getText() == null || portTf.getText().isEmpty()
				|| sidTf.getText() == null || sidTf.getText().isEmpty() || userNameTf.getText() == null
				|| userNameTf.getText().isEmpty() || new String(passwordTf.getPassword()) == null
				|| new String(passwordTf.getPassword()).isEmpty();
	}

	/**
	 * 測試連線
	 * 
	 * @param dbConfig
	 * @return
	 * @throws SQLException
	 */
	protected boolean testConnection(DBConfig dbConfig) {
		DataBaseSetting dbs = new DataBaseSetting(dbConfig.getHostIP(), dbConfig.getPort(), dbConfig.getSid(),
				dbConfig.getUserName(), dbConfig.getPassword(), dbConfig.getDbName());
		Connection conn = null;
		boolean flag = false;

		try {
			conn = dbs.getConnection();
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
