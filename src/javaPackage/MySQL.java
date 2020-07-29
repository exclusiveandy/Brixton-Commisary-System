package javaPackage;

import java.sql.*;




public class MySQL
{
	Connection conn = null;
	

	public static Connection dbConnector()
	{
		try{
			   String driver = "com.mysql.jdbc.Driver";
			   Class.forName(driver);			  
			   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase?useSSL=false", "root", "");
			   
			   Statement st = conn.createStatement();
			 			   
			   return conn;
			   
			  } catch(Exception e)
			{
				return null;
			}
		 
		
		
		
	}
	


	 
}
