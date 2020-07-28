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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Notification extends JFrame{

	JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notification window = new Notification();
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
	public Notification() {
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
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btnMenu = new JButton("");
		btnMenu.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menubutton.jpg"));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				BrixtonMenu window = new BrixtonMenu();
				window.frame.setVisible(true);
				
			}
		});
		btnMenu.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 194, 623, 281);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblHi = new JLabel("");
		
		
		lblHi.setBounds(476, 68, 89, 115);
		frame.getContentPane().add(lblHi);
		
		JLabel lblAwd = new JLabel("");
		lblAwd.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\notificationback.jpg"));
		lblAwd.setBounds(0, 2, 684, 510);
		frame.getContentPane().add(lblAwd);
		frame.setBounds(100, 100, 700, 550);
		
		
		showNotificationTable();
		
		if(table.getRowCount()==0){
			
			JOptionPane.showMessageDialog(null,"No Low Stock Warnings!");
			lblHi.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\nowarning.gif"));
			
		}
		if(table.getRowCount()>=1){
			
			int a = table.getRowCount();
			JOptionPane.showMessageDialog(null,"There is(are) "+a+" Items that needed to Replenish");
			lblHi.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\arrowdown.gif"));
			
			
		}
		
		
		frame.setLocationRelativeTo(null);
	}
	
	private void showNotificationTable()
	{
		model = new DefaultTableModel(new String[]{"Item Code", "Item Name","Category","Price","Unit","Quantity","StockLevel"}, 0);
		
		try
		{
			
			String sql = "SELECT * FROM items";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
			String itemCode = rs.getString("Item_Code");
		    String itemName = rs.getString("Item_Name");
		    String itemCategory = rs.getString("Item_Category");
		   
		    int itemPrice = rs.getInt("Item_Price");
		    int itemQuantity = rs.getInt("Item_Quantity");
		    String itemUnit = rs.getString("Item_Unit");		    
		    int lowstockWarning = rs.getInt("Item_Warning");
		        
		    
		    
		    if(itemQuantity<=lowstockWarning)
		    {
		    
		    model.addRow(new Object[]{itemCode,itemName,itemCategory,itemPrice,itemUnit,itemQuantity,lowstockWarning});
		    }
		    else{}
		    
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Wlaa?"+e.getMessage());
		}
		
		table.setModel(model);
	}
}
