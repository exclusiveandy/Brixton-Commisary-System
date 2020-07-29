package javaPackage;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class BrixtonMenu {

	JFrame frame;
	JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonMenu window = new BrixtonMenu();
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
	public BrixtonMenu() {
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
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 800,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setBounds(10, 581, 764, 80);
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				BrixtonItems window = new BrixtonItems();
				window.frame.setVisible(true);
				
				
				
				
				
				
			}
		});
		frame.setEnabled(true);
		
		
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Show Item List, able to add, edit and delete items.");
			}
		});
		btnNewButton.setToolTipText("ITEM LIST");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newburger.png"));
		btnNewButton.setBounds(74, 316, 120,120);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				BrixtonOrders window = new BrixtonOrders();
				window.frame.setVisible(true);
				
			}
		});
		button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Show Order Form, Getting Stock in Supplier and Delivering Stock at Branches");
			}
		});
		button.setToolTipText("ORDERS");
		button.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\neworder.png"));
		button.setBounds(74, 461, 120, 120);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				BrixtonSupplier window = new BrixtonSupplier();
				window.frame.setVisible(true);
				
				
			}
		});
		button_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Display Available Supplier. Able to add, delete and update suppliers");
			}
		});
		button_1.setToolTipText("SUPPLIERS");
		button_1.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newsupp.png"));
		button_1.setBounds(429, 461, 120, 120);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				frame.dispose();
				BrixtonInventory w = new BrixtonInventory();
				w.frame.setVisible(true);
				
			}
		});
		button_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Display Inventory, Show the Added and Consumed Items.");
			}
		});
		button_2.setToolTipText("INVENTORY");
		button_2.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newinvent.png"));
		button_2.setBounds(259, 316, 120, 120);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				BrixtonReports win = new BrixtonReports();
				win.frame.setVisible(true);
				
				
			}
		});
		button_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblNewLabel.setText("Show Reports.");
			}
		});
		button_3.setToolTipText("REPORTS");
		button_3.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newreport.png"));
		button_3.setBounds(586, 461, 120, 120);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				BrixtonBranches window = new BrixtonBranches();
				window.frame.setVisible(true);
				
			}
		});
		button_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Show Existing Branches. Able to add, delete and update Branches");
				
			}
		});
		button_4.setToolTipText("BRANCHES");
		button_4.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newbranch.png"));
		button_4.setBounds(259, 461, 120, 120);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Notification window = new Notification();
				window.frame.setVisible(true);
				
			}
			
		});
		button_5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				lblNewLabel.setText("Notifications");
				
			}
		});
		button_5.setToolTipText("STOCKS");
		button_5.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\newstock.png"));
		button_5.setBounds(429, 316, 120, 120);
		frame.getContentPane().add(button_5);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		

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
		    	JLabel lblNewLabel_2 = new JLabel("");
				lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Andrywin\\Desktop\\img\\arrowcheck.gif"));
				lblNewLabel_2.setBounds(586, 316, 120, 120);
				frame.getContentPane().add(lblNewLabel_2);
		   
		    }
		    else{}
		    
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 31));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menuback2.jpg"));
		lblNewLabel_1.setBounds(0, 0, 794, 672);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
	}
}
