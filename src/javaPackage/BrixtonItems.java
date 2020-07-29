package javaPackage;

import java.awt.EventQueue;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class BrixtonItems {

	JFrame frame;
	private JTable table;
	static DefaultTableModel model;
	private JTextField itemcodetf;
	private JTextField itemnametf;
	private JTextField itempricetf;
	private JTextField itemquantitytf;
	private JTextField itemwarningtf;
	

	static JComboBox suppliercb;
	
	private static String itemCode;
	private static String itemName;
	private static String itemCategory;
	private static String itemSupplier;
	private static String itemPrice;
	private static String itemUnit;
	private static String itemQuantity;
	private static String lowstockWarning;
	
	private static JButton btnAdd;
	private static JButton btnDelete;
	private static JButton btnEdit;
	private static JButton btnSave;
	private static JButton btnCancel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonItems window = new BrixtonItems();
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
	public BrixtonItems() {
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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 890, 550);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 103, 580, 382);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int i = table.getSelectedRow();

		        table.getModel();
		        
		        itemcodetf.setText(model.getValueAt(i,0).toString());
		        itemnametf.setText(model.getValueAt(i,1).toString());
		        itempricetf.setText(model.getValueAt(i,4).toString());
		        itemquantitytf.setText(model.getValueAt(i,5).toString());	
		        
		        itemwarningtf.setText(model.getValueAt(i,7).toString());
		    	
		    	
			}
		});
		
		
		
		
		
		
		
		scrollPane.setViewportView(table);
		
			
		showTable();
		
		
		
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				BrixtonMenu window = new BrixtonMenu();
				window.frame.setVisible(true);
				
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menubutton.jpg"));
		button.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(button);
		
				
		JLabel lblItemCode = new JLabel("Item Code:");
		lblItemCode.setForeground(Color.BLACK);
		lblItemCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemCode.setBounds(10, 109, 93, 14);
		frame.getContentPane().add(lblItemCode);
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemName.setForeground(Color.BLACK);
		lblItemName.setBounds(10, 140, 93, 14);
		frame.getContentPane().add(lblItemName);
		
		JLabel lblItem = new JLabel("Item Supplier ID:");
		lblItem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblItem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItem.setForeground(Color.BLACK);
		lblItem.setBounds(10, 205, 120, 14);
		frame.getContentPane().add(lblItem);
		
		JLabel lblItemCategory = new JLabel("Item Category:");
		lblItemCategory.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemCategory.setForeground(Color.BLACK);
		lblItemCategory.setBounds(10, 171, 105, 14);
		frame.getContentPane().add(lblItemCategory);
		
		JLabel lblItemPrice_1 = new JLabel("Item Price:");
		lblItemPrice_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemPrice_1.setForeground(Color.BLACK);
		lblItemPrice_1.setBounds(10, 236, 120, 14);
		frame.getContentPane().add(lblItemPrice_1);
		
		JLabel lblItemUnit = new JLabel("Item Unit:");
		lblItemUnit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemUnit.setForeground(Color.BLACK);
		lblItemUnit.setBounds(10, 266, 93, 14);
		frame.getContentPane().add(lblItemUnit);
		
		JLabel lblItemQuantity = new JLabel("Item Quantity:");
		lblItemQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemQuantity.setForeground(Color.BLACK);
		lblItemQuantity.setBounds(10, 298, 105, 20);
		frame.getContentPane().add(lblItemQuantity);
		
		JLabel lblLowstockWarning = new JLabel("Low-Stock Warning:");
		lblLowstockWarning.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLowstockWarning.setForeground(Color.BLACK);
		lblLowstockWarning.setBounds(10, 329, 146, 18);
		frame.getContentPane().add(lblLowstockWarning);
		
		itemcodetf = new JTextField();
		itemcodetf.setBounds(123, 107, 151, 20);
		frame.getContentPane().add(itemcodetf);
		itemcodetf.setColumns(10);
		itemcodetf.setEnabled(false);
		
		itemnametf = new JTextField();
		itemnametf.setBounds(123, 138, 151, 20);
		frame.getContentPane().add(itemnametf);
		itemnametf.setColumns(10);
		
		itempricetf = new JTextField();
		itempricetf.setBounds(123, 234, 151, 20);
		frame.getContentPane().add(itempricetf);
		itempricetf.setColumns(10);
		
		itemquantitytf = new JTextField();
		itemquantitytf.setBounds(123, 296, 151, 20);
		frame.getContentPane().add(itemquantitytf);
		itemquantitytf.setColumns(10);
		
		itemwarningtf = new JTextField();
		itemwarningtf.setBounds(166, 327, 108, 20);
		frame.getContentPane().add(itemwarningtf);
		itemwarningtf.setColumns(10);
		
		JComboBox categorycb = new JComboBox();
		categorycb.setModel(new DefaultComboBoxModel(new String[] {"Burger", "Fries", "Shake", "Condiments", "Others"}));
		categorycb.setBounds(123, 169, 151, 20);
		frame.getContentPane().add(categorycb);
		
		JComboBox unitcb = new JComboBox();
		unitcb.setModel(new DefaultComboBoxModel(new String[] {"Pack", "Kilo", "Can","Rim"}));
		unitcb.setBounds(123, 264, 151, 20);
		frame.getContentPane().add(unitcb);
		
		suppliercb = new JComboBox();
		suppliercb.setBounds(154, 203, 120, 20);
		frame.getContentPane().add(suppliercb);
		
		try{
			
		Statement st = conn.createStatement();

		 ResultSet r=st.executeQuery("select Supplier_ID from suppliers");

		 while (r.next()) {  

		     suppliercb.addItem(r.getString("Supplier_ID"));
		
		}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
			
		}
		
		
		
		
		
		
		
		
		btnAdd = new JButton(""); //ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD-----ADD
		btnAdd.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\addbutton.jpg"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
								
				try
				{  
			            String sql = "INSERT INTO items(Item_Name ,Item_Category, Supplier_ID, Item_Price ,Item_Unit ,Item_Quantity, Item_Warning) VALUES(?,?,?,?,?,?,?)";                        
			           
			            PreparedStatement pst = conn.prepareStatement(sql);
			            
			            pst.setString(1, itemnametf.getText());
			            String category = categorycb.getSelectedItem().toString();
			            pst.setString(2, category);
			            String supplier = suppliercb.getSelectedItem().toString();
			            pst.setString(3, supplier);
			            pst.setString(4, itempricetf.getText());
			            String unit = unitcb.getSelectedItem().toString();
			            pst.setString(5, unit);
			            pst.setString(6, itemquantitytf.getText());
			            pst.setString(7, itemwarningtf.getText());
			            pst.executeUpdate();
			            itemcodetf.setText("");
			            itemnametf.setText("");
			            itempricetf.setText("");
			            itemquantitytf.setText("");
			            itemwarningtf.setText("");
			            JOptionPane.showMessageDialog(null,"Added Successfully!" );
			            showTable();
			            			            
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
				try{				//INSERTING DATA IN INVENTORY TABLE
					

					String sql = "INSERT INTO inventory (Item_Code) VALUES (LAST_INSERT_ID())";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					pst.executeUpdate();
					
					
				}catch(Exception e1){JOptionPane.showMessageDialog(frame,e1.getMessage());}
				
				
				
				}//if
				
				
				
				
				
			}
		});
		btnAdd.setBounds(21, 390, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnDelete = new JButton("");// DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\deletebutton.jpg"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this selected item?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
						
				
				
				try{
					
					
					String sql = "DELETE FROM `items` WHERE `items`.`Item_Code` = ?";
					
					table.getModel();
					
					pst = conn.prepareStatement(sql);
		            pst.setString(1, itemcodetf.getText());
		            
					pst.executeUpdate();
					
						itemcodetf.setText("");
						itemnametf.setText("");
						itempricetf.setText("");
					 	
			            itemquantitytf.setText("");
			            itemwarningtf.setText("");
			            
					
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					showTable();
				
				}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
				}
				
			}
		});
		btnDelete.setBounds(166, 390, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\editbutton.jpg"));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnAdd.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				itemcodetf.setEnabled(true);
			}
		});
		btnEdit.setBounds(95, 424, 89, 23);
		frame.getContentPane().add(btnEdit);
		
		btnSave = new JButton("");// UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE
		btnSave.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\savebutton.jpg"));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure about this updates?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
						
				
				try{
					
					
				String sql = "UPDATE items SET Item_Name=?, Item_Category=?, Supplier_ID=?, Item_Price=?, Item_Unit=?, Item_Quantity=?, Item_Warning=? WHERE Item_Code=?";     
				PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(8, itemcodetf.getText());
				 	pst.setString(1, itemnametf.getText());
		            String category = categorycb.getSelectedItem().toString();
		            pst.setString(2, category);
		            String supplier = suppliercb.getSelectedItem().toString();
		            pst.setString(3, supplier);
		            pst.setString(4, itempricetf.getText());
		            String unit = unitcb.getSelectedItem().toString();
		            pst.setString(5, unit);
		            pst.setString(6, itemquantitytf.getText());
		            pst.setString(7, itemwarningtf.getText());
				
				 
				 
				 
				 pst.executeUpdate();
	           
				 	itemcodetf.setText("");
					itemnametf.setText("");
					itempricetf.setText("");
				 	
		            itemquantitytf.setText("");
		            itemwarningtf.setText("");
				 				 
				
				
				JOptionPane.showMessageDialog(null, "Update Successfully!");
				showTable();
				
				
				
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				}
				
				
			}
		});
		btnSave.setBounds(21, 458, 89, 23);
		frame.getContentPane().add(btnSave);
		btnSave.setEnabled(false);
		
		btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\cancelbutton.jpg"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAdd.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				itemcodetf.setEnabled(false);
				
				itemcodetf.setText("");
				itemnametf.setText("");
				itempricetf.setText("");
			 	
	            itemquantitytf.setText("");
	            itemwarningtf.setText("");
				
			}
		});
		btnCancel.setBounds(166, 458, 89, 23);
		frame.getContentPane().add(btnCancel);
		btnCancel.setEnabled(false);
		
		JButton btnSearch = new JButton(""); //SEARCH----SEARCH----SEARCH----SEARCH----SEARCH----SEARCH----SEARCH----SEARCH----SEARCH
		btnSearch.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\searchbutton.jpg"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				model = new DefaultTableModel(new String[]{"Item Code", "Item Name","Category","Supplier","Price","Quantity","Unit","StockLevel"}, 0);
				
				
				String search = JOptionPane.showInputDialog("Search by Name:");
				
				try{
				String sql = "SELECT items.Item_Code, items.Item_Name, items.Item_Category"
						+ ", suppliers.Supplier_Name, items.Item_Price, items.Item_Quantity"
						+ ", items.Item_Unit, items.Item_Warning FROM items "
						+ "INNER JOIN suppliers ON items.Supplier_ID = suppliers.Supplier_ID WHERE Item_Name LIKE ?";

				
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + search + "%");
				
				 rs = pst.executeQuery();
				
				 while(rs.next())
					{
					 	itemCode = rs.getString("Item_Code");
					    itemName = rs.getString("Item_Name");
					    itemCategory = rs.getString("Item_Category");
					    itemSupplier = rs.getString("Supplier_Name");
					    itemPrice = rs.getString("Item_Price");
					    itemQuantity = rs.getString("Item_Quantity");
					    itemUnit = rs.getString("Item_Unit");		    
					    lowstockWarning = rs.getString("Item_Warning");
					   
					    model.addRow(new Object[]{itemCode, itemName, itemCategory,itemSupplier,itemPrice,itemQuantity, itemUnit, lowstockWarning});
					  
					}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					
					table.setModel(model);
				
				
				
			}
		});
		btnSearch.setBounds(775, 69, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\refreshbutton.jpg"));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showTable();
			}
		});
		btnRefresh.setBounds(284, 69, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnSupplierTable = new JButton("");
		btnSupplierTable.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\showsupplerbutton.jpg"));
		btnSupplierTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showSupplier();
				
				
			}
		});
		btnSupplierTable.setBounds(493, 69, 139, 23);
		frame.getContentPane().add(btnSupplierTable);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\itemback1.jpg"));
		label.setBounds(0, 0, 884, 522);
		frame.getContentPane().add(label);
		
	}

	private void showTable() {

		
			model = new DefaultTableModel(new String[]{"Item Code", "Item Name","Category","Supplier","Price","Quantity","Unit","StockLevel"}, 0);
			
		
				try
				{
					
					String query = "SELECT * "
							+ "from items";
					
					 st = conn.createStatement();
					 rs = st.executeQuery(query);
					
				
					
			          
				while(rs.next())
				{	
				    itemCode = rs.getString("Item_Code");
				    itemName = rs.getString("Item_Name");
				    itemCategory = rs.getString("Item_Category");
				   // itemSupplier = rs.getString("Supplier_Name");
				    itemPrice = rs.getString("Item_Price");
				    itemQuantity = rs.getString("Item_Quantity");
				    itemUnit = rs.getString("Item_Unit");		    
				    //lowstockWarning = rs.getString("Item_Warning");
				    
					
					
				    
				    
				    
				    model.addRow(new Object[]{itemCode, itemName, itemCategory,itemPrice,itemQuantity, itemUnit, lowstockWarning});
				}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
				
				
				table.setModel(model);
		
	}
	
	private void showSupplier()
	{
model = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Address / Location"}, 0);
		
		try
		{
			
			String sql = "SELECT * FROM suppliers ORDER BY Supplier_ID";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
			String suppID,suppName,suppCont,suppAdd;
			
			
		   suppID = rs.getString("Supplier_ID");
		   suppName = rs.getString("Supplier_Name");
		   suppCont = rs.getString("Supplier_Contact");
		   suppAdd = rs.getString("Supplier_Address");
		        
		    
		    
		    model.addRow(new Object[]{suppID,suppName,suppCont,suppAdd});
		  
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Connection Failed");
		}
		
		table.setModel(model);
	}
	
}
