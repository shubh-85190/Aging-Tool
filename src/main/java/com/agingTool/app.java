package com.agingTool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class app extends JFrame{

	private static JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtDBServer;
	DBConnection db;
	private String dbname;
	private String kmsServer;
	private JTable tableTransactions;
	private ArrayList<String[]> transactions = new ArrayList<String[]>();
	private JPasswordField txtPassword;
	private JTextField txtSettelmentDate;
	private DefaultTableCellRenderer cellRenderer;
	private JTextField txtKMS;
	private JDialog loader;
	
//	String gifPath = "./src/main/resources/images/Spinner100.gif";
//	String gifPath=  "/com.agingTool/src/main/resources/images/Spinner100.gif";
	String gifPath="C:\\Users\\shubh\\Desktop\\Aging-Tool\\src\\main\\resources\\images\\Spinner100.gif";
    ImageIcon gifIcon = new ImageIcon(getClass().getResource(gifPath));
    JLabel gifLabel=new JLabel();
    JPanel panel = new JPanel();
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new app();
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
	private void showLoader()
	{		
		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		int height = this.getHeight();
		String str= "Position " + ((int)(x/2 + width/2)-50) + "," + ((int)(y/2 + height/2)-100) + "," + 200 + "," +100;
		System.out.print(str);
		loader.setLocationRelativeTo(this);
//		loader.setBounds((int)(x/2 + width/2)-50, (int)(y/2 + height/2)-100, 200, 100);
		loader.setVisible(true);
	}

	public app() {
		this.setTitle("AGING TOOL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 172, 172));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);

		System.out.println("Frames : " + Frame.getFrames()[0].getX());

		loader = new JDialog(Frame.getFrames()[0], "Loading...", true);
//		loader.setSize(200, 100);
		loader.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//		loader.setLocationRelativeTo(this.getFrames()[0]);
		
		panel.setLayout(new BorderLayout());
		gifLabel.setIcon(gifIcon);
	    panel.add(gifLabel, BorderLayout.CENTER);
//	    int centerX = panel.getWidth() / 2;
//        int centerY = panel.getHeight() / 2;
//        Point p=new Point(centerX, centerY);
//	    gifLabel.setLocation(p);
	    
	    
	    panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(gifLabel, gbc);
	    
	    loader.getContentPane().add(panel);
	    loader.setLocationRelativeTo(null);
        loader.setModalityType(ModalityType.APPLICATION_MODAL);
        loader.setSize(150, 150);
       
//        loader.setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		Color bgColor = new Color(204, 51, 51, 0);
		System.out.println("Transaparency : " + bgColor.getTransparency());
						
						JComboBox<String> comboFileType = new JComboBox<String>();
						comboFileType.setForeground(new Color(0, 0, 0));
						
						comboFileType.setBackground(new Color(255, 255, 255));
						comboFileType.setFont(new Font("Times New Roman", Font.BOLD, 14));
						comboFileType.setBounds(631, 272, 127, 30);
						contentPane.add(comboFileType);
						comboFileType.addItem("No Interchange");
						comboFileType.addItem("With Interchange");
						comboFileType.addItem("Charge Back File");
				
						JLabel lblNewLabel_2 = new JLabel("KMS  :");
						lblNewLabel_2.setForeground(new Color(255, 255, 255));
						lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
						lblNewLabel_2.setBounds(459, 184, 62, 35);
						contentPane.add(lblNewLabel_2);
		
				txtKMS = new JTextField();
				txtKMS.setText("VMIOLAB27");
				txtKMS.setHorizontalAlignment(SwingConstants.CENTER);
				txtKMS.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				txtKMS.setColumns(10);
				txtKMS.setBounds(605, 189, 139, 26);
				contentPane.add(txtKMS);
		
		JLabel lblDbName_1_1_1_1 = new JLabel("Select File Type :");
		lblDbName_1_1_1_1.setForeground(Color.WHITE);
		lblDbName_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDbName_1_1_1_1.setBounds(494, 266, 139, 36);
		contentPane.add(lblDbName_1_1_1_1);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(270, 175, 140, 26);
		contentPane.add(txtPassword);

		JLabel lblDbName_1_1 = new JLabel("Account Number :");
		lblDbName_1_1.setForeground(new Color(255, 255, 255));
		lblDbName_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDbName_1_1.setBounds(459, 147, 150, 35);
		contentPane.add(lblDbName_1_1);

		JComboBox<String> comboAccounts = new JComboBox<String>();
		comboAccounts.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboAccounts.setBounds(605, 151, 214, 29);
		contentPane.add(comboAccounts);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("TraceID");
		model.addColumn("RRN");
		model.addColumn("STAN");
		model.addColumn("MTI");
		model.addColumn("ProcCode");
		model.addColumn("ApprovalCode");
		model.addColumn("DataLocalTxn[MMDD]");
		model.addColumn("TxnAMT");
		model.addColumn("Card Number");
		tableTransactions = new JTable(model);
		tableTransactions.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableTransactions.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableTransactions.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		tableTransactions.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		tableTransactions.setBounds(10, 327, 674, 211);
		contentPane.add(tableTransactions);

		JScrollPane scrollPane = new JScrollPane(tableTransactions);
		scrollPane.setBounds(19, 313, 974, 246);
		contentPane.add(scrollPane);

		JButton btnGenerateFile = new JButton("Generate File");

		btnGenerateFile.setForeground(new Color(255, 255, 255));
		btnGenerateFile.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnGenerateFile.setBackground(new Color(30, 98, 95));
		btnGenerateFile.setBounds(574, 589, 140, 29);
		contentPane.add(btnGenerateFile);

		JButton btnClearAll = new JButton("Clear All");

		btnClearAll.setForeground(new Color(255, 255, 255));
		btnClearAll.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnClearAll.setBackground(new Color(204, 51, 51));
		btnClearAll.setBounds(133, 589, 107, 29);
		contentPane.add(btnClearAll);

		JButton btnSelectAll = new JButton("Select All");

		btnSelectAll.setForeground(new Color(255, 255, 255));
		btnSelectAll.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnSelectAll.setBackground(new Color(51, 153, 51));
		btnSelectAll.setBounds(415, 589, 119, 29);
		contentPane.add(btnSelectAll);
		tableTransactions.setSelectionForeground(new Color(0, 0, 0));
		tableTransactions.setSelectionBackground(new Color(0, 204, 204));

		JButton btnUnselectAll = new JButton("Unselect All");

		btnUnselectAll.setForeground(new Color(255, 255, 255));
		btnUnselectAll.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnUnselectAll.setBackground(new Color(204, 51, 51));
		btnUnselectAll.setBounds(260, 589, 142, 29);
		contentPane.add(btnUnselectAll);



		JLabel lblDbName = new JLabel("CoreIssue DB Name :");
		lblDbName.setForeground(new Color(255, 255, 255));
		lblDbName.setBounds(459, 59, 174, 35);
		contentPane.add(lblDbName);
		lblDbName.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JComboBox<String> comboCAuthDB = new JComboBox<String>();
		comboCAuthDB.setBounds(631, 65, 205, 29);
		contentPane.add(comboCAuthDB);
		comboCAuthDB.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JLabel lblAccount = new JLabel("");
//		lblAccount.setIcon(new ImageIcon(app.class.getResource("/images/415x215.png")));
		lblAccount.setBounds(449, 51, 415, 229);
		contentPane.add(lblAccount);

		txtDBServer = new JTextField();
		txtDBServer.setBorder(new LineBorder(new Color(0, 102, 0), 2, true));
		txtDBServer.setHorizontalAlignment(SwingConstants.CENTER);
		txtDBServer.setBounds(270, 83, 140, 26);
		contentPane.add(txtDBServer);
		txtDBServer.setText("BPLQADB01");
		txtDBServer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDBServer.setColumns(10);

		JLabel lblNewLabel = new JLabel("DB Server  :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(133, 78, 127, 35);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JLabel lblAuthentication = new JLabel("Authentication :");
		lblAuthentication.setForeground(new Color(255, 255, 255));
		lblAuthentication.setBounds(131, 108, 126, 35);
		contentPane.add(lblAuthentication);
		lblAuthentication.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JComboBox<String> comboAuthMode = new JComboBox<String>();
		comboAuthMode.setBorder(new LineBorder(new Color(51, 102, 0), 2, true));
		comboAuthMode.setBounds(270, 115, 142, 21);
		contentPane.add(comboAuthMode);
		comboAuthMode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboAuthMode.addItem("WA");
		comboAuthMode.addItem("SA");

		txtUserName = new JTextField();
		txtUserName.setBounds(270, 144, 140, 26);
		contentPane.add(txtUserName);
		txtUserName.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtUserName.setColumns(10);

		JLabel lblUser = new JLabel("User Name :");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(133, 147, 119, 35);
		contentPane.add(lblUser);
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JButton btnConnect = new JButton("Connect DB Server");
		btnConnect.setBounds(162, 154, 193, 31);
		contentPane.add(btnConnect);
		btnConnect.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnConnect.setBackground(new Color(1, 123, 255));
		btnConnect.setForeground(new Color(255, 255, 255));

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(133, 180, 85, 35);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JLabel lblNewLabel_1 = new JLabel("VISA RECON FILE GENERATOR");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(306, 11, 406, 34);
		contentPane.add(lblNewLabel_1);

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
//						loader.setVisible(true);
						String DBServer = txtDBServer.getText();
						comboCAuthDB.removeAllItems();
						try {
							String user=txtUserName.getText();
							String password = new String(txtPassword.getPassword());
							String authMode = comboAuthMode.getSelectedItem().toString();
							db = new DBConnection(DBServer,authMode,user,password);
							ArrayList<String> dbnames = db.getDBNames();
							if(dbnames==null)
							{
								System.out.println("Dbnames is null............");
								loader.setVisible(false);
								JOptionPane.showMessageDialog(frame,"Unable to connect database.","Error",JOptionPane.ERROR_MESSAGE);
								return null;
							}
							if(dbnames.isEmpty())
							{
								loader.setVisible(false);
								JOptionPane.showMessageDialog(frame,"No DB found with \"CI\" in it's Name");
								return null;
							}
							for (int i = 0; i < dbnames.size(); i++) {
								if (dbnames.get(i).contains("CI"))
									comboCAuthDB.addItem(dbnames.get(i));
							}
							

//					System.out.println(dbnames.toString());
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						return null;
					}

					@Override
					protected void done() {
						loader.setVisible(false);
					}
				};
				worker.execute();
				showLoader();
			}
		});
		comboAuthMode.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (comboAuthMode.getSelectedItem().toString().equals("WA")) {
					lblUser.setVisible(false);
					txtUserName.setVisible(false);
					lblPassword.setVisible(false);
					txtPassword.setVisible(false);

				} else {
					lblUser.setVisible(true);
					txtUserName.setVisible(true);
					lblPassword.setVisible(true);
					txtPassword.setVisible(true);

				}
			}
		});
		comboAuthMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboAuthMode.getSelectedItem().toString().equals("WA")) {
					lblUser.setVisible(false);
					txtUserName.setVisible(false);
					lblPassword.setVisible(false);
					txtPassword.setVisible(false);
					btnConnect.setBounds(162, 154, 193, 31);

				} else {
					lblUser.setVisible(true);
					txtUserName.setVisible(true);
					lblPassword.setVisible(true);
					txtPassword.setVisible(true);
					btnConnect.setBounds(162, 220, 193, 31);
				}

			}
		});


//		scrollPane.
//		Object[] obj= new String[] {
//				"TraceID", "RRN", "STAN", "MTI", "ProcCode", "ApprovalCode", "DateLocalTransaction"
//			};
//		model.addRow(obj);
		tableTransactions.getAutoscrolls();
		JTableHeader header = tableTransactions.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 14));
		header.setBackground(new Color(3, 4, 94));
		header.setForeground(new Color(255, 255, 255));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tableTransactions.getColumnCount(); i++) {
			tableTransactions.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
//        tableTransactions.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				if(tableTransactions.getRowCount()==0)
//				{
//					btnClearAll.setEnabled(false);
//					btnSelectAll.setEnabled(false);
//				}
//				else {
//					btnClearAll.setEnabled(true);
//					btnSelectAll.setEnabled(true);
//					
//				}
//				if(tableTransactions.getSelectedRow()==-1)
//				{
//					btnUnselectAll.setEnabled(false);
//					btnGenerateFile.setEnabled(false);
//				}
//				else
//				{
//					btnUnselectAll.setEnabled(true);
//					btnGenerateFile.setEnabled(true);
//				}
//			}
//		});
		btnClearAll.setEnabled(false);
		btnSelectAll.setEnabled(false);
		btnUnselectAll.setEnabled(false);
		btnGenerateFile.setEnabled(false);
		
		JLabel lblDB = new JLabel("");
//		lblDB.setIcon(new ImageIcon(app.class.getResource("/images/p2.JPG")));
		lblDB.setBounds(114, 51, 325, 229);
		contentPane.add(lblDB);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(app.class.getResource("/images/p2.png")));
		lblNewLabel_3.setBounds(0, 0, 1014, 646);
		contentPane.add(lblNewLabel_3);
		
//		JLabel lblNewLabel_3 = new JLabel("New label");
//		lblNewLabel_3.setIcon(new ImageIcon(app.class.getResource("/images/loader.gif")));
////		lblNewLabel_3.setBounds(19, 116, 62, 54);
//		contentPane.add(lblNewLabel_3);
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (tableTransactions.getRowCount() == 0) {
					btnClearAll.setEnabled(false);
					btnSelectAll.setEnabled(false);
				} else {
					btnClearAll.setEnabled(true);
					btnSelectAll.setEnabled(true);

				}
				if (tableTransactions.getSelectedRow() == -1) {
					btnUnselectAll.setEnabled(false);
					btnGenerateFile.setEnabled(false);
				} else {
					btnUnselectAll.setEnabled(true);
					btnGenerateFile.setEnabled(true);
				}
			}

//			public void valueChanged(ListSelectionEvent e) {
//				if (tableTransactions.getRowCount() == 0) {
//					btnClearAll.setEnabled(false);
//					btnSelectAll.setEnabled(false);
//				} else {
//					btnClearAll.setEnabled(true);
//					btnSelectAll.setEnabled(true);
//
//				}
//				if (tableTransactions.getSelectedRow() == -1) {
//					btnUnselectAll.setEnabled(false);
//					btnGenerateFile.setEnabled(false);
//				} else {
//					btnUnselectAll.setEnabled(true);
//					btnGenerateFile.setEnabled(true);
//				}
//			}
		});
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) tableTransactions.getModel();
				tableModel.setRowCount(0); // Removes all rows from the table
				transactions.removeAll(transactions);
				btnUnselectAll.setEnabled(false);
				btnSelectAll.setEnabled(false);
				// btnGenerateFile.setEnabled(false);
			}
		});
		btnUnselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableTransactions.clearSelection();
				btnSelectAll.setEnabled(true);
//				btnGenerateFile.setEnabled(false);
				btnUnselectAll.setEnabled(false);
			}
		});}
//		btnSelectAll.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tableTransactions.selectAll();
//				btnSelectAll.setEnabled(false);
//				btnGenerateFile.setEnabled(true);
//				btnUnselectAll.setEnabled(true);
//			}
//		});


	public static void autofitColumns(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Minimum column width
			// Find the maximum width for the column's content
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
				Component component = table.prepareRenderer(cellRenderer, row, column);
				width = Math.max(component.getPreferredSize().width, width);
			}

			// Set the column width to the maximum content width
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

}
