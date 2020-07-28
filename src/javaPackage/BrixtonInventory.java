package javaPackage;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrixtonInventory {

	JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonInventory window = new BrixtonInventory();
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
	public BrixtonInventory() {
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
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				BrixtonMenu w = new BrixtonMenu();
				w.frame.setVisible(true);
				
			}
			
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menubutton.jpg"));
		btnNewButton.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 764, 572);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		model = new DefaultTableModel(new String[]{"InventID", "ItemCode","ItemName","Total Quantity","StockLvl.","Add","Consume"}, 0);	//INNER JOIN suppliers ON items.Supplier_ID = suppliers.Supplier_ID ORDER BY Item_Code
		
		try
		{
			
			String query = "SELECT inventory.Inventory_ID, inventory.Item_Code, items.Item_Name, inventory.Added, inventory.Consumed, items.Item_Quantity, items.Item_Warning FROM inventory INNER JOIN items ON inventory.Item_Code = items.Item_Code";                                                
			 st = conn.createStatement();
			 rs = st.executeQuery(query);
			
		
		while(rs.next())
		{	
			String id = rs.getString("Inventory_ID");
			String item = rs.getString("Item_Code");
			String itemname = rs.getString("Item_Name");
			String added = rs.getString("Added");
			String consumed = rs.getString("Consumed");
			String quantity = rs.getString("Item_Quantity");
			String low = rs.getString("Item_Warning");
		    
		    model.addRow(new Object[]{id,item, itemname,quantity,low,added,consumed,});
		    
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
				
		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\inventoryback.jpg"));
		lblNewLabel.setBounds(0, 0, 784, 662);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
