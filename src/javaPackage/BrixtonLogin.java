package javaPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BrixtonLogin {

	JFrame frame;
	private static JTextField loginTF;
	private static JPasswordField passTF;
	private static JLabel lblUsername;
	private static JLabel lblPassword;
	private static JLabel label_1;
		
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrixtonLogin window = new BrixtonLogin();
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
	public BrixtonLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		loginTF = new JTextField();
		loginTF.setBounds(409, 324, 160, 20);
		frame.getContentPane().add(loginTF);
		loginTF.setColumns(10);
		
		passTF = new JPasswordField();
		//passTF.setEchoChar('*');
		passTF.setBounds(409, 355, 160, 20);
		frame.getContentPane().add(passTF);
		passTF.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			////stock d2
				if(e.getSource()==btnNewButton)
				{
															
					String username = loginTF.getText();
					String password = passTF.getText();
					
					if(username.contentEquals("admin") && password.contentEquals("qwer") ) {
					    //next frame---------------------
						//JOptionPane.showMessageDialog(frame, "Going Up!","Success",JOptionPane.PLAIN_MESSAGE);
						JOptionPane.showMessageDialog(frame, "Login Successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
						
						BrixtonMenu window = new BrixtonMenu();
						window.frame.setVisible(true);
						
						
												
					}
					else{
						JOptionPane.showMessageDialog(frame, "Incorrect Username or Password! (Check CAPS LOCK)","Login Error",JOptionPane.ERROR_MESSAGE);
						
						passTF.setText(null);
					} 
				}
				
			}
		}); 
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\loginbutton.jpg"));
		btnNewButton.setBounds(429, 386, 106, 23);
		frame.getContentPane().add(btnNewButton);
		frame.getRootPane().setDefaultButton(btnNewButton);
		
		lblUsername = new JLabel("USERNAME:");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(301, 326, 117, 14);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(301, 357, 117, 14);
		frame.getContentPane().add(lblPassword);
		
		
	
		
		JLabel label = new JLabel("");
		label.setForeground(Color.GRAY);
		label.setBounds(0, 0, 694, 472);
		label.setIcon(new ImageIcon("C:\\Users\\Andrywin\\workspace\\SQL\\img\\loginback.jpg"));
		frame.getContentPane().add(label);
	}

}
