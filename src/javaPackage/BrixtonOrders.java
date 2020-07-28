package javaPackage;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


public class BrixtonOrders {

	JFrame frame;
	private JTextField itemcodetfB;
	private JTextField itemcodetf;
	private JTextField quantitytfB;
	private JTextField itemquantitytf;
	private JTable tablepurchaseS;
	private JTable tablepurchaseB;
	private JTable table;
	static DefaultTableModel model;
	static DefaultTableModel modelS;
	static DefaultTableModel modelB;
	static DefaultTableModel branchmodel;
	
	private static int itemCode;
	private static String itemName;
	private static String itemCategory;
	private static String itemSupplier;
	private static int itemPrice;
	private static String itemUnit;
	private static String itemQuantity;
	private static String lowstockWarning;
	
	static JLabel lblItemCode;
	static JLabel lblQuantity;
	
	static JScrollPane scrollPaneAdd;
	static JButton btnAddS;
	static JButton btnAddB;
	static JLabel lblItemList;
	static JScrollPane scrollPane;
	
	static JPanel panelbranch;
	static JPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonOrders window = new BrixtonOrders();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BrixtonOrders() {
		initialize();
	}
	
	static Connection conn;
	static Statement st;
	static ResultSet rs;
	static PreparedStatement pst;
	private JButton btnViewOverallPrice;
	private JTextField overallitemtf;
	private JTextField overallpricetf;
	private JLabel lblDateToday;
	private JButton purchasedbtnB;
	private JTextField overallitemtfB;
	private JTextField overallpricetfB;
	private JButton btnRefresh;
	private JComboBox branchcb;
	private JTable branchtable;
	private JScrollPane branchtablescrollpane;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		conn = MySQL.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		lblDateToday = new JLabel();
		lblDateToday.setForeground(Color.BLACK);
		lblDateToday.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDateToday.setBounds(583, 11, 291, 14);
		frame.getContentPane().add(lblDateToday);
		
		
		
			Calendar cal = Calendar.getInstance();
						
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			
		
		lblDateToday.setText("Date Today:  "+(month+1)+" / "+day+" / "+year);
		
		
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setName("");
		tabbedPane.setBounds(10, 47, 864, 504);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Orders from Supplier                                        ]", null, panel, null);
		panel.setLayout(null);
		
		
		
		
		JLabel lblItemCode = new JLabel("Item Code:");
		lblItemCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemCode.setBounds(22, 40, 77, 14);
		panel.add(lblItemCode);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQuantity.setBounds(22, 65, 61, 14);
		panel.add(lblQuantity);
		
		itemcodetf = new JTextField();
		itemcodetf.setBounds(109, 37, 86, 20);
		panel.add(itemcodetf);
		itemcodetf.setColumns(10);
		
		itemquantitytf = new JTextField();
		itemquantitytf.setBounds(109, 62, 86, 20);
		panel.add(itemquantitytf);
		itemquantitytf.setColumns(10);
		
		// TABLE FOR READY PURCHASED
		
		JScrollPane scrollPaneAdd = new JScrollPane();
		scrollPaneAdd.setBounds(10, 165, 383, 264);
		panel.add(scrollPaneAdd);
		
		tablepurchaseS = new JTable();
		scrollPaneAdd.setViewportView(tablepurchaseS);
		
		
		
		modelS = new DefaultTableModel(new String[]{"Qty.","Item Name","Item Code","Price","Total Amt."}, 0);
		
		tablepurchaseS.setModel(modelS);
		
		
		btnAddS = new JButton(""); ////////// ADD--------ADD-------ADD--------ADD-------ADD--------ADD-------ADD--------ADD
		btnAddS.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\addbutton.jpg"));
		btnAddS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				int quantity = Integer.parseInt(itemquantitytf.getText());
				try
				{		
					String query = "SELECT Item_Name, Item_Code, Item_Price FROM items WHERE Item_Code =?";
					pst = conn.prepareStatement(query);
					pst.setString(1, itemcodetf.getText());
					rs = pst.executeQuery();
					
					
				while(rs.next())
				{
				    
				    itemName = rs.getString("Item_Name");
				    itemPrice = rs.getInt("Item_Price");
				    itemCode = rs.getInt("Item_Code");
				    
				    
				    int totprice;
				    				    
				    totprice = quantity * itemPrice;
				     modelS.addRow(new Object[]{quantity,itemName,itemCode,itemPrice,totprice});
				   
				}
				
				
				}
				catch(Exception e)	{JOptionPane.showMessageDialog(null,e.getMessage());}
				
				
				tablepurchaseS.setModel(modelS);
				
				
				
				
			}
		});
		btnAddS.setBounds(32, 118, 89, 23);
		panel.add(btnAddS);
		
		
		
		JButton btnRemove = new JButton("");
		btnRemove.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\removebutton.jpg"));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int numRows = tablepurchaseS.getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

				    modelS.removeRow(tablepurchaseS.getSelectedRow());
				}
				
			}
		});
		btnRemove.setBounds(144, 118, 89, 23);
		panel.add(btnRemove);
		
		JButton btnPurchased = new JButton("");
		btnPurchased.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\purchasebutton.jpg"));
		btnPurchased.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase all this items?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				if(tablepurchaseS.getRowCount()>=1 ) // FOR TABLE IS EMPTY
				{	
					
				
				try{																			//INSERTING DATA TO TABLE SUPPLIERORDERS
					int loop = 	tablepurchaseS.getRowCount();
					String sql = "INSERT INTO supplierorders (Item_Code, Quantity, Price) VALUES(?,?,?)";                        
					PreparedStatement pst = conn.prepareStatement(sql);
										
					for (int i = 0; i < loop; i++)
					{
				        
						String qu = String.valueOf(tablepurchaseS.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseS.getValueAt(i, 2));
						String pr = String.valueOf(tablepurchaseS.getValueAt(i, 4));
						int quantity = Integer.parseInt(qu);
						int itemname = Integer.parseInt(it);
						int price = Integer.parseInt(pr);
						pst.setInt(1, itemname);
						pst.setInt(2, quantity);
						pst.setInt(3, price);
						pst.executeUpdate();
				    }
					
				}catch(Exception e1){ JOptionPane.showMessageDialog(null,e1.getMessage());}
				
				
				try{																			//UPDATING DATA TO TABLE ITEMS
					int loop = 	tablepurchaseS.getRowCount();
					String sql = "UPDATE items SET Item_Quantity=Item_Quantity+? WHERE Item_Code=?";     
					PreparedStatement pst = conn.prepareStatement(sql);
					for (int i = 0; i < loop; i++)
					{
						String qu = String.valueOf(tablepurchaseS.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseS.getValueAt(i, 2));
						int quantity = Integer.parseInt(qu);
						int itemname = Integer.parseInt(it);
						
						pst.setInt(1, quantity);
					 	pst.setInt(2,itemname);
						
					 	pst.executeUpdate();
					}
					
				}catch(Exception e2){JOptionPane.showMessageDialog(null,e2.getMessage());}
				
				try{																			//INSERTING DATA ADDED IN INVENTORY
					int loop = 	tablepurchaseS.getRowCount();
					String sql = "UPDATE inventory SET Added=Added+? WHERE Item_Code=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					for (int i = 0; i < loop; i++)
					{
						String qu = String.valueOf(tablepurchaseS.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseS.getValueAt(i, 2));
						int quantity = Integer.parseInt(qu);
						int itemcode = Integer.parseInt(it);
						pst.setInt(1, quantity);
						pst.setInt(2, itemcode);
						
						pst.executeUpdate();
					}
										
					
				}catch(Exception e2){JOptionPane.showMessageDialog(null,"Error in Inventory"+e2.getMessage());}
				
				
				
				JOptionPane.showMessageDialog(null,"Successfully Purchased!");
				modelS.setRowCount(0);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"table is empty!");
				}
				
				
				
				}
			}
		});
		btnPurchased.setBounds(719, 325, 130, 140);
		panel.add(btnPurchased);
		
		btnViewOverallPrice = new JButton(">>>>> VIEW OVERALL PRICE <<<<<");
		btnViewOverallPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int totalamt = 0;

			    for (int i = 0; i < tablepurchaseS.getRowCount(); i++){
			        int amount = Integer.parseInt( tablepurchaseS.getValueAt(i, 4).toString());
			        totalamt =totalamt+ amount;
			    }
			    
			    int totalitem = 0;

			    for (int i = 0; i < tablepurchaseS.getRowCount(); i++){
			        int amount = Integer.parseInt( tablepurchaseS.getValueAt(i, 0).toString());
			        totalitem =totalitem + amount;
			    }
			    
			    String totitem = String.valueOf(totalitem);
			    String totprice = String.valueOf(totalamt);
			    
			    overallitemtf.setText(totitem);
			    overallpricetf.setText(totprice);
				
			}
		});
		btnViewOverallPrice.setBounds(10, 442, 383, 23);
		panel.add(btnViewOverallPrice);
	    
		JLabel lblOverallPrice = new JLabel("Overall Price:");
		lblOverallPrice.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblOverallPrice.setBounds(452, 416, 114, 14);
		panel.add(lblOverallPrice);
		
		JLabel lblOverallQuantityOf = new JLabel("Overall Quantity of Items: ");
		lblOverallQuantityOf.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblOverallQuantityOf.setBounds(413, 354, 189, 14);
		panel.add(lblOverallQuantityOf);
		
		overallitemtf = new JTextField();
		overallitemtf.setText("0");
		overallitemtf.setHorizontalAlignment(SwingConstants.CENTER);
		overallitemtf.setFont(new Font("Times New Roman", Font.BOLD, 35));
		overallitemtf.setBounds(588, 325, 114, 69);
		panel.add(overallitemtf);
		overallitemtf.setColumns(10);
		
		overallpricetf = new JTextField();
		overallpricetf.setText("0");
		overallpricetf.setHorizontalAlignment(SwingConstants.CENTER);
		overallpricetf.setFont(new Font("Times New Roman", Font.BOLD, 35));
		overallpricetf.setColumns(10);
		overallpricetf.setBounds(588, 396, 114, 69);
		panel.add(overallpricetf);
		
		
		// TABLE FOR ITEM LIST
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemList.setBounds(403, 11, 91, 14);
		panel.add(lblItemList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 40, 446, 274);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		model = new DefaultTableModel(new String[]{"Item Code", "Item Name","Category","Supplier","Price","Quantity","Unit","StockLvl"}, 0);
		
		
		try
		{
			
			String query = "SELECT items.Item_Code, items.Item_Name, items.Item_Category, suppliers.Supplier_Name, items.Item_Price, items.Item_Quantity, items.Item_Unit, items.Item_Warning FROM items INNER JOIN suppliers ON items.Supplier_ID = suppliers.Supplier_ID";
			
			 st = conn.createStatement();
			 rs = st.executeQuery(query);
			
		
			
	          
		while(rs.next())
		{	
		    itemCode = rs.getInt("Item_Code");
		    itemName = rs.getString("Item_Name");
		    itemCategory = rs.getString("Item_Category");
		    itemSupplier = rs.getString("Supplier_Name");
		    itemPrice = rs.getInt("Item_Price");
		    itemQuantity = rs.getString("Item_Quantity");
		    itemUnit = rs.getString("Item_Unit");		    
		    lowstockWarning = rs.getString("Item_Warning");
		    
			
			
		    
		    
		    
		    model.addRow(new Object[]{itemCode, itemName, itemCategory,itemSupplier,itemPrice,itemQuantity, itemUnit, lowstockWarning});
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		
		table.setModel(model);
		
		
		
		
		
		
		// 2ND TAB
		
		
		panelbranch = new JPanel();
		tabbedPane.addTab("Orders of Branch                                        ]", null, panelbranch, null);
		panelbranch.setLayout(null);
		
		JButton btnMenu = new JButton("");
		btnMenu.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\menubutton.jpg"));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				BrixtonMenu window = new BrixtonMenu();
				window.frame.setVisible(true);
				
			}
		});
		btnMenu.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnMenu);
		
		JLabel bgLogo = new JLabel("");
		bgLogo.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\orderback.jpg"));
		bgLogo.setBounds(0, 0, 884, 562);
		frame.getContentPane().add(bgLogo);
		
		
		lblItemCode = new JLabel("Item Code:");
		lblItemCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemCode.setBounds(22, 40, 77, 14);
		panelbranch.add(lblItemCode);
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQuantity.setBounds(22, 65, 61, 14);
		panelbranch.add(lblQuantity);
		
		itemcodetfB = new JTextField();
		itemcodetfB.setBounds(109, 37, 86, 20);
		panelbranch.add(itemcodetfB);
		itemcodetfB.setColumns(10);
		
		quantitytfB = new JTextField();
		quantitytfB.setBounds(109, 62, 86, 20);
		panelbranch.add(quantitytfB);
		quantitytfB.setColumns(10);
		
		// TABLE FOR READY PURCHASED
		
		scrollPaneAdd = new JScrollPane();
		scrollPaneAdd.setBounds(10, 165, 383, 264);
		panelbranch.add(scrollPaneAdd);
		
		tablepurchaseB = new JTable();
		scrollPaneAdd.setViewportView(tablepurchaseB);
		
		
		modelB = new DefaultTableModel(new String[]{"Qty.", "Item Name","Item Code","Price","Total Amt.","BranchID"}, 0);
		
		
		
		tablepurchaseB.setModel(modelB);
		
		
		btnAddB = new JButton(""); ////////// ADD--------ADD-------ADD--------ADD-------ADD--------ADD-------ADD--------ADD  FOR BRANCHES
		btnAddB.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\addbutton.jpg"));
		btnAddB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				int quantity = Integer.parseInt(quantitytfB.getText());
				try
				{		
					String query = "SELECT Item_Name, Item_Code, Item_Price FROM items WHERE Item_Code =?";
					pst = conn.prepareStatement(query);
					pst.setString(1, itemcodetfB.getText());
					String branch = branchcb.getSelectedItem().toString();
		           
					rs = pst.executeQuery();
					
					
				while(rs.next())
				{
				    
				    itemName = rs.getString("Item_Name");
				    itemPrice = rs.getInt("Item_Price");
				    itemCode = rs.getInt("Item_Code");
				    
				    
				    int totprice;
				    				    
				    totprice = quantity * itemPrice;
				    
				     modelB.addRow(new Object[]{quantity,itemName,itemCode,itemPrice,totprice,branch});
				   
				}
				
				
				}
				catch(Exception e)	{JOptionPane.showMessageDialog(null,e.getMessage());}
				
				
				tablepurchaseB.setModel(modelB);
				
				
				
				
				
				
			}
		});
		btnAddB.setBounds(32, 118, 89, 23);
		panelbranch.add(btnAddB);
		
		
		
		
		
		// TABLE FOR ITEM LIST
		
		lblItemList = new JLabel("Item List:");
		lblItemList.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemList.setBounds(403, 11, 91, 14);
		panelbranch.add(lblItemList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 40, 268, 274);
		panelbranch.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		model = new DefaultTableModel(new String[]{"Item Code", "Item Name","Price","Quantity",}, 0);
		
		try
		{
			
			String query = "SELECT items.Item_Code, items.Item_Name, items.Item_Price, items.Item_Quantity FROM items INNER JOIN suppliers ON items.Supplier_ID = suppliers.Supplier_ID";
			
			 st = conn.createStatement();
			 rs = st.executeQuery(query);
			
		
		while(rs.next())
		{	
		    itemCode = rs.getInt("Item_Code");
		    itemName = rs.getString("Item_Name");
		   	itemPrice = rs.getInt("Item_Price");
		    itemQuantity = rs.getString("Item_Quantity");
		    
		    
		    model.addRow(new Object[]{itemCode, itemName, itemPrice,itemQuantity,});
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
				
		table.setModel(model);
		
		
		
		branchtablescrollpane = new JScrollPane();								// branch table
		branchtablescrollpane.setBounds(675, 40, 174, 274);
		panelbranch.add(branchtablescrollpane);
		
		branchtable = new JTable();
		branchtablescrollpane.setViewportView(branchtable);
		
		branchmodel = new DefaultTableModel(new String[]{"Branch ID", "Branch Name"}, 0);
		try{
			
			String query = "SELECT * from branches";
			
			 st = conn.createStatement();
			 rs = st.executeQuery(query);
			
		
		while(rs.next())
		{	
		    String branchid = rs.getString("Branch_ID");
		    String branchname = rs.getString("Branch_Name");
		   	
		    
		    branchmodel.addRow(new Object[]{branchid,branchname});
		}
			
		}catch(Exception e){ JOptionPane.showMessageDialog(frame,e.getMessage());}
		
		branchtable.setModel(branchmodel);
		
		
		
		
		JButton btnNewButton =  new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\removebutton.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int numRows = tablepurchaseB.getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

				    modelB.removeRow(tablepurchaseB.getSelectedRow());
				}
				
			}
		});
		btnNewButton.setBounds(144, 118, 89, 23);
		panelbranch.add(btnNewButton);
		
		purchasedbtnB = new JButton("");
		purchasedbtnB.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\deliverbutton.jpg"));
		purchasedbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// BRANCH UPDATE INSERT
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to send all this items to the corresponding branch?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				if(tablepurchaseB.getRowCount()>=1 )// FOR TABLE IS EMPTY
				{
									
					
					
				try{																			//INSERTING DATA TO TABLE BRANCHORDERS
					
					
					int loop = 	tablepurchaseB.getRowCount();
					String sql = "INSERT INTO branchorders (Branch_ID, Item_Code, Quantity, Price) VALUES(?,?,?,?)";                        
					PreparedStatement pst = conn.prepareStatement(sql);
										
					for (int i = 0; i < loop; i++)
					{
				        
						String qu = String.valueOf(tablepurchaseB.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseB.getValueAt(i, 2));
						String pr = String.valueOf(tablepurchaseB.getValueAt(i, 4));
						String br = String.valueOf(tablepurchaseB.getValueAt(i, 5));
						int quantity = Integer.parseInt(qu);
						int itemname = Integer.parseInt(it);
						int price = Integer.parseInt(pr);
						int branch = Integer.parseInt(br);
						
						
			            pst.setInt(1, branch);
						pst.setInt(2, itemname);
						pst.setInt(3, quantity);
						pst.setInt(4, price);
						pst.executeUpdate();
						
				    }
					
				}catch(Exception e1){ JOptionPane.showMessageDialog(null,e1.getMessage());}
				
				
				try{																			//UPDATING DATA TO TABLE ITEMS
					int loop = 	tablepurchaseB.getRowCount();
					String sql = "UPDATE items SET Item_Quantity=Item_Quantity-? WHERE Item_Code=?";     
					PreparedStatement pst = conn.prepareStatement(sql);
					for (int i = 0; i < loop; i++)
					{
						String qu = String.valueOf(tablepurchaseB.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseB.getValueAt(i, 2));
						int quantity = Integer.parseInt(qu);
						int itemname = Integer.parseInt(it);
						
						pst.setInt(1, quantity);
					 	pst.setInt(2,itemname);
						
					 	pst.executeUpdate();
					}
					
				}catch(Exception e2){JOptionPane.showMessageDialog(null,e2.getMessage());}
				
				try{																			//INSERTING DATA CONSUMED IN INVENTORY
					int loop = 	tablepurchaseB.getRowCount();
					String sql = "UPDATE inventory SET Consumed=Consumed-? WHERE Item_Code=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					for (int i = 0; i < loop; i++)
					{
						String qu = String.valueOf(tablepurchaseB.getValueAt(i, 0));
						String it = String.valueOf(tablepurchaseB.getValueAt(i, 2));
						int quantity = Integer.parseInt(qu);
						int itemcode = Integer.parseInt(it);
						pst.setInt(1, quantity);
						pst.setInt(2, itemcode);
						
						pst.executeUpdate();
					}
										
					
				}catch(Exception e2){JOptionPane.showMessageDialog(null,"Error in Inventory"+e2.getMessage());}
				
				
				JOptionPane.showMessageDialog(null,"Successfully Delivered!");
				modelB.setRowCount(0);
				
				}
				else
				{
					JOptionPane.showMessageDialog(null,"table is empty!");
				}
				
				}
				
				
				
			}
		});
		purchasedbtnB.setBounds(719, 325, 130, 140);
		panelbranch.add(purchasedbtnB);
		
		JLabel label = new JLabel("Overall Quantity of Items: ");
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(413, 354, 189, 14);
		panelbranch.add(label);
		
		overallitemtfB = new JTextField();
		overallitemtfB.setText("0");
		overallitemtfB.setHorizontalAlignment(SwingConstants.CENTER);
		overallitemtfB.setFont(new Font("Times New Roman", Font.BOLD, 35));
		overallitemtfB.setColumns(10);
		overallitemtfB.setBounds(588, 325, 114, 69);
		panelbranch.add(overallitemtfB);
		
		JLabel label_1 = new JLabel("Overall Price:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_1.setBounds(452, 416, 114, 14);
		panelbranch.add(label_1);
		
		overallpricetfB = new JTextField();
		overallpricetfB.setText("0");
		overallpricetfB.setHorizontalAlignment(SwingConstants.CENTER);
		overallpricetfB.setFont(new Font("Times New Roman", Font.BOLD, 35));
		overallpricetfB.setColumns(10);
		overallpricetfB.setBounds(588, 396, 114, 69);
		panelbranch.add(overallpricetfB);
		
		JButton overallbtnB = new JButton(">>>>> VIEW OVERALL PRICE <<<<<");
		overallbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int totalamt = 0;

			    for (int i = 0; i < tablepurchaseB.getRowCount(); i++){
			        int amount = Integer.parseInt( tablepurchaseB.getValueAt(i, 4).toString());
			        totalamt =totalamt+ amount;
			    }
			    
			    int totalitem = 0;

			    for (int i = 0; i < tablepurchaseB.getRowCount(); i++){
			        int amount = Integer.parseInt( tablepurchaseB.getValueAt(i, 0).toString());
			        totalitem =totalitem + amount;
			    }
			    
			    String totitem = String.valueOf(totalitem);
			    String totprice = String.valueOf(totalamt);
			    
			    overallitemtfB.setText(totitem);
			    overallpricetfB.setText(totprice);
				
				
				
				
				
			}
		});
		overallbtnB.setBounds(10, 442, 383, 23);
		panelbranch.add(overallbtnB);
		
		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBranchId.setBounds(22, 90, 77, 14);
		panelbranch.add(lblBranchId);
		
		branchcb = new JComboBox();
		branchcb.setBounds(109, 87, 86, 20);
		panelbranch.add(branchcb);
		
		
		try{
			
			Statement st = conn.createStatement();

			 ResultSet r=st.executeQuery("select Branch_ID from branches");

			 while (r.next()) {  

			     branchcb.addItem(r.getString("Branch_ID"));
			
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,ex.getMessage());
				
			}
		
	
		
		
		
	}
	public void showTable() {

		
			
	
}
}
