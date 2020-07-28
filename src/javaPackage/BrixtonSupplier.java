package javaPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class BrixtonSupplier {

	JFrame frame;
	private JTextField suppidtf;
	private JTextField suppnametf;
	private JTextField suppcontacttf;
	private JTextArea suppaddresstf;
	private static JTable table;
	static DefaultTableModel model;
	
	private static JButton btnAdd;
	private static JButton btnDelete;
	private static JButton btnEdit;
	private static JButton btnSave;
	private static JButton btnCancel;
	
	
	
	private static String suppID;
	private static String suppName;
	private static String suppCont;
	private static String suppAdd;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonSupplier window = new BrixtonSupplier();
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
	public BrixtonSupplier() {
		initialize();
	}
	
	static Connection conn;
	static Statement st;
	static ResultSet rs;
	static PreparedStatement pst;
	private JButton btnRefresh;
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		conn = MySQL.dbConnector();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 800, 550);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setBounds(305, 116, 469, 358);
		frame.getContentPane().add(spTable);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int i = table.getSelectedRow();

		        table.getModel();
		        
		        
		        suppidtf.setText(model.getValueAt(i,0).toString());

		        suppnametf.setText(model.getValueAt(i,1).toString());

		        suppcontacttf.setText(model.getValueAt(i,2).toString());

		        suppaddresstf.setText(model.getValueAt(i,3).toString());
				
				
			}
		});
		spTable.setViewportView(table);
		
		showTable();
		
		
		/*
		model = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Address / Location"}, 0);
		
		try
		{
			
			String sql = "SELECT * FROM suppliers ORDER BY Supplier_ID";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
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
		
		*/
		
		
		
		
		
		
		
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\menubutton.jpg"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				BrixtonMenu w = new BrixtonMenu();
				w.frame.setVisible(true);
				
				
			}
		});
		button.setBounds(10, 24, 89, 23);
		frame.getContentPane().add(button);
		
		JLabel lblSuppliersID = new JLabel("Supplier's ID:");
		lblSuppliersID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSuppliersID.setForeground(Color.BLACK);
		lblSuppliersID.setBounds(25, 144, 112, 14);
		frame.getContentPane().add(lblSuppliersID);
		
		suppidtf = new JTextField();
		suppidtf.setBounds(183, 138, 112, 20);
		frame.getContentPane().add(suppidtf);
		suppidtf.setColumns(10);
		suppidtf.setEnabled(false);
		
		JLabel lblSuppliersName = new JLabel("Supplier's Name:");
		lblSuppliersName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSuppliersName.setForeground(Color.BLACK);
		lblSuppliersName.setBounds(25, 174, 135, 14);
		frame.getContentPane().add(lblSuppliersName);
		
		suppnametf = new JTextField();
		suppnametf.setBounds(183, 172, 112, 20);
		frame.getContentPane().add(suppnametf);
		suppnametf.setColumns(10);
		
		JLabel lblSuppliersContact = new JLabel("Supplier's Contact:");
		lblSuppliersContact.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSuppliersContact.setForeground(Color.BLACK);
		lblSuppliersContact.setBounds(25, 205, 135, 14);
		frame.getContentPane().add(lblSuppliersContact);
		
		suppcontacttf = new JTextField();
		suppcontacttf.setBounds(183, 203, 112, 20);
		frame.getContentPane().add(suppcontacttf);
		suppcontacttf.setColumns(10);
		
		btnSave = new JButton("");// SAVEUPDATE----SAVEUPDATE----SAVEUPDATE----SAVEUPDATE----SAVEUPDATE----SAVEUPDATE----SAVEUPDATE----SAVEUPDATE
		btnSave.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\savebutton.jpg"));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure about this updates?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try{
					
					
				String sql = "UPDATE suppliers SET Supplier_Name=?, Supplier_Contact=?, Supplier_Address=? WHERE Supplier_ID=?";     
				PreparedStatement pst = conn.prepareStatement(sql);
				 pst.setString(4, suppidtf.getText());
				 pst.setString(1, suppnametf.getText());
				 pst.setString(2, suppcontacttf.getText());
				 pst.setString(3, suppaddresstf.getText());
				
				 pst.executeUpdate();
	           
				 	suppidtf.setText("");
				 	suppnametf.setText("");
		            suppcontacttf.setText("");
		            suppaddresstf.setText("");
				 				 
				
				
				JOptionPane.showMessageDialog(null, "Update Successfully!");
				showTable();
				
				
				
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Update Mali!");
				}
				
				}
				
				
				
			}
		});
		btnSave.setBounds(48, 424, 89, 23);
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
				suppidtf.setEnabled(false);
			}
		});
		btnCancel.setBounds(166, 424, 89, 23);
		frame.getContentPane().add(btnCancel);
		btnCancel.setEnabled(false);
		
		btnAdd = new JButton("");//ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD----ADD  
		btnAdd.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\addbutton.jpg"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try
				{
			            
					
					String sql = "INSERT INTO suppliers (Supplier_Name, Supplier_Contact, Supplier_Address) VALUES(?,?,?)";
					PreparedStatement pst = conn.prepareStatement(sql);
						
					
					//pst.setString(1,suppidtf.getText());
					pst.setString(1,suppnametf.getText());
					pst.setString(2,suppcontacttf.getText());
					pst.setString(3,suppaddresstf.getText());
					pst.executeUpdate();
							
							
						
			            
			            
			           
			            suppnametf.setText("");
			            suppcontacttf.setText("");
			            suppaddresstf.setText("");
			            
			           
			            JOptionPane.showMessageDialog(null,"Added Successfully!" );
			            showTable();
			            
			            
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Some of the Inputs are Wrong!","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				}
				
				
				
			}
		});
		btnAdd.setBounds(48, 356, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnDelete = new JButton("");//DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE----DELETE
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\\\deletebutton.jpg"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this selected item?", "Warning!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				try{
					
				
					String sql = "DELETE FROM `suppliers` WHERE `suppliers`.`Supplier_ID` = ?";
					
					table.getModel();
					
					pst = conn.prepareStatement(sql);
		            pst.setString(1, suppidtf.getText());
		            
					pst.executeUpdate();
					
						suppidtf.setText("");
					 	suppnametf.setText("");
			            suppcontacttf.setText("");
			            suppaddresstf.setText("");
			            
					
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					showTable();
				
				}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, "Brotcha!");
				}
				
				}
				
				//"DELETE FROM `suppliers` WHERE `suppliers`.`Supplier_ID` = 3"
				
				
			}
		});
		btnDelete.setBounds(166, 356, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnEdit = new JButton("");// UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE----UPDATE
		btnEdit.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\editbutton.jpg"));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAdd.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				suppidtf.setEnabled(true);
				
				
			}
		});
		btnEdit.setBounds(107, 390, 89, 23);
		frame.getContentPane().add(btnEdit);
		
		
		JLabel lblSuppliersAddress = new JLabel("Supplier's Address:");
		lblSuppliersAddress.setForeground(Color.BLACK);
		lblSuppliersAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSuppliersAddress.setBounds(25, 236, 135, 14);
		frame.getContentPane().add(lblSuppliersAddress);
		
		suppaddresstf = new JTextArea();
		suppaddresstf.setBounds(183, 234, 112, 67);
		suppaddresstf.setLineWrap(true);
		frame.getContentPane().add(suppaddresstf);
		
		JButton btnSearch = new JButton("");// SEARCH-----SEARCH-----SEARCH-----SEARCH-----SEARCH-----SEARCH-----SEARCH-----SEARCH
		btnSearch.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\searchbutton.jpg"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				model = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Address / Location"}, 0);
				
				
				String search = JOptionPane.showInputDialog("Search by Name:");
				
				try{
				String sql = "SELECT * FROM suppliers WHERE Supplier_Name=?";
				
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%"+search+"%");
				
				 rs = pst.executeQuery();
				
				 while(rs.next())
					{
					   suppID = rs.getString("Supplier_ID");
					   suppName = rs.getString("Supplier_Name");
					   suppCont = rs.getString("Supplier_Contact");
					   suppAdd = rs.getString("Supplier_Address");
					        
					    
					    
					    model.addRow(new Object[]{suppID,suppName,suppCont,suppAdd});
					  
					}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage());
					}
					
					table.setModel(model);
				
			}
		});
		btnSearch.setBounds(685, 82, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\refreshbutton.jpg"));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showTable();
				
				
				
			}
		});
		btnRefresh.setBounds(305, 82, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		
		
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\supplierback.jpg"));
		label.setBounds(0, 0, 794, 522);
		frame.getContentPane().add(label);
		
		
		

        
        
        
		
		
		
		
	}
	
	public static void showTable()
	{
		
		model = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Address / Location"}, 0);
		
		try
		{
			
			String sql = "SELECT * FROM suppliers ORDER BY Supplier_ID";
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			
		while(rs.next())
		{
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
