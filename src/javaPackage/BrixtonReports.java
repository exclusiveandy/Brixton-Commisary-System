package javaPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;

public class BrixtonReports {

	JFrame frame;
	private JTable table;
	private JTextField itemnametfS;
	private JTextField datetfS;
	private JTextField itemnametfB;
	private JTextField datetfB;
	private DefaultTableModel modelS;
	private DefaultTableModel modelB;
	private DefaultTableModel modelES;
	private DefaultTableModel modelEB;
	private JComboBox branchcb;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonReports window = new BrixtonReports();
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
	public BrixtonReports() {
		initialize();
	}

	static Connection conn;
	static Statement st;
	static ResultSet rs;
	static PreparedStatement pst;
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		conn = MySQL.dbConnector();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 900, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnMenu = new JButton("");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				BrixtonMenu w = new BrixtonMenu();
				w.frame.setVisible(true);
				
				
			}
		});
		btnMenu.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menubutton.jpg"));
		btnMenu.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 199, 650, 602);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSupplier = new JButton("");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				showSupplier();
								
			}
		});
		btnSupplier.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\showsupplierbutton.jpg"));
		btnSupplier.setBounds(10, 213, 204, 31);
		frame.getContentPane().add(btnSupplier);
		
		JButton btnBranch = new JButton("");
		btnBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showBranches();
				
			}
		});
		btnBranch.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\showbranchorder.jpg"));
		btnBranch.setBounds(10, 429, 204, 31);
		frame.getContentPane().add(btnBranch);
		
		JLabel lblItemName = new JLabel("ITEM NAME:");
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemName.setBounds(10, 271, 101, 14);
		frame.getContentPane().add(lblItemName);
		
		itemnametfS = new JTextField();
		itemnametfS.setBounds(118, 269, 101, 20);
		frame.getContentPane().add(itemnametfS);
		itemnametfS.setColumns(10);
		
		JLabel lblDate = new JLabel("DATE:");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(10, 302, 101, 14);
		frame.getContentPane().add(lblDate);
		
		datetfS = new JTextField();
		datetfS.setBounds(80, 300, 139, 20);
		frame.getContentPane().add(datetfS);
		datetfS.setColumns(10);
		
		JLabel label = new JLabel("ITEM NAME:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(10, 492, 101, 14);
		frame.getContentPane().add(label);
		
		itemnametfB = new JTextField();
		itemnametfB.setColumns(10);
		itemnametfB.setBounds(118, 490, 101, 20);
		frame.getContentPane().add(itemnametfB);
		
		JLabel label_1 = new JLabel("DATE:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_1.setBounds(10, 523, 101, 14);
		frame.getContentPane().add(label_1);
		
		datetfB = new JTextField();
		datetfB.setColumns(10);
		datetfB.setBounds(80, 521, 139, 20);
		frame.getContentPane().add(datetfB);
		
		JButton btnApply = new JButton("");
		btnApply.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\executebutton.jpg"));
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				executeS();
				
			}
		});
		btnApply.setBounds(125, 331, 89, 23);
		frame.getContentPane().add(btnApply);
		
		JButton btnApply_1 = new JButton("");
		btnApply_1.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\executebutton.jpg"));
		btnApply_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				executeB();
				
			}
		});
		btnApply_1.setBounds(125, 583, 89, 23);
		frame.getContentPane().add(btnApply_1);
		
		JLabel lblBranch = new JLabel("BRANCH:");
		lblBranch.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBranch.setBounds(10, 552, 76, 14);
		frame.getContentPane().add(lblBranch);
		
		branchcb = new JComboBox();
		branchcb.setBounds(90, 552, 129, 20);
		frame.getContentPane().add(branchcb);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\burgerofficial.gif"));
		lblNewLabel_1.setBounds(31, 630, 157, 160);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\reportsbackfinal.jpg"));
		lblNewLabel.setBounds(0, 0, 884, 812);
		frame.getContentPane().add(lblNewLabel);
		
		try{
			
			Statement st = conn.createStatement();

			 ResultSet r=st.executeQuery("select Branch_Name from branches");

			 while (r.next()) {  

			     branchcb.addItem(r.getString("Branch_Name"));
			
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,ex.getMessage());
				
			}
		
		
		
		
		
	}
	
	private void showSupplier(){
		
		modelS = new DefaultTableModel(new String[]{"Item Name", "Total Qty. Purchase", "Total Amt. Purchase","Order Date"}, 0);
		
		try
		{
			
			String sql = "SELECT items.Item_Name, supplierorders.Quantity, supplierorders.Price, supplierorders.Order_Date FROM supplierorders INNER JOIN items ON items.Item_Code = supplierorders.Item_Code ORDER BY Order_Date";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
			
		   
		   String itemname = rs.getString("Item_Name");
		   String quantity = rs.getString("Quantity");
		   String price = rs.getString("Price");
		   String date = rs.getString("Order_Date");
		        
		    
		    
		    modelS.addRow(new Object[]{itemname,quantity,price,date});
		  
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		table.setModel(modelS);
		
	}
	private void showBranches(){
		
		
		modelB = new DefaultTableModel(new String[]{"Delivered to Branch","Item Name", "Total Qty. Purchase", "Total Amt. Purchase","Order Date"}, 0);
		
		try
		{
			
			String sql = "SELECT branches.Branch_Name, items.Item_Name, branchorders.Quantity, branchorders.Price, branchorders.Order_Date FROM branchorders INNER JOIN items ON items.Item_Code = branchorders.Item_Code"
					+ " INNER JOIN branches ON branches.Branch_ID = branchorders.Branch_ID ORDER BY Order_Date";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
			
			
			String branchname = rs.getString("Branch_Name");
			String itemname = rs.getString("Item_Name");
			String quantity = rs.getString("Quantity");
			String price = rs.getString("Price");
			String date = rs.getString("Order_Date");
			        			    
			    
			    modelB.addRow(new Object[]{branchname,itemname,quantity,price,date});
		  
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		table.setModel(modelB);
		
		
	}
	
	private void executeS(){
		
		modelES = new DefaultTableModel(new String[]{"Item Name", "Total Qty. Purchase", "Total Amt. Purchase","Order Date"}, 0);
		
		// PURCHASE ORDER SEARCH
		
		try{
		String sql = "SELECT items.Item_Name, supplierorders.Quantity, supplierorders.Price, supplierorders.Order_Date FROM supplierorders INNER JOIN items ON items.Item_Code = supplierorders.Item_Code WHERE items.Item_Name LIKE ? and supplierorders.Order_Date LIKE ?";

		
		pst = conn.prepareStatement(sql);
		pst.setString(1, "%" + itemnametfS.getText() + "%");
		pst.setString(2, "%" + datetfS.getText() + "%");
		 rs = pst.executeQuery();
		
		 while(rs.next())
			{
			
			   String itemname = rs.getString("Item_Name");
			   String quantity = rs.getString("Quantity");
			   String price = rs.getString("Price");
			   String date = rs.getString("Order_Date");
			        
			    
			    
			    modelES.addRow(new Object[]{itemname,quantity,price,date});
			  
			}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
			table.setModel(modelES);
		
	}
	
private void executeB(){
		
		modelEB = new DefaultTableModel(new String[]{"Delivered to Branch","Item Name", "Total Qty. Purchase", "Total Amt. Purchase","Order Date"}, 0);
		
		// BRANCH ORDER SEARCH
		
		
		try{
		String sql = "SELECT branches.Branch_Name, items.Item_Name, branchorders.Quantity, branchorders.Price, branchorders.Order_Date FROM branchorders INNER JOIN items ON items.Item_Code = branchorders.Item_Code"
				+ " INNER JOIN branches ON branches.Branch_ID = branchorders.Branch_ID WHERE items.Item_Name LIKE ? and branchorders.Order_Date LIKE ? and branches.Branch_Name LIKE ?";
		
		pst = conn.prepareStatement(sql);
		pst.setString(1, "%" + itemnametfB.getText() + "%");
		pst.setString(2, "%" + datetfB.getText() + "%");
		pst.setString(3, "%" + branchcb.getSelectedItem().toString()+ "%");
		
		 rs = pst.executeQuery();
		
		 while(rs.next())
			{
			 
				 String branchname = rs.getString("Branch_Name");
				 String itemname = rs.getString("Item_Name");
				 String quantity = rs.getString("Quantity");
				 String price = rs.getString("Price");
				 String date = rs.getString("Order_Date");
			        			    
			    
			    modelEB.addRow(new Object[]{branchname,itemname,quantity,price,date});
			}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
			table.setModel(modelEB);
		
	}
}
