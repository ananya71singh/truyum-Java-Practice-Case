package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {

	public static Connection getConnection()
	{
		
		Connection con=null;
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("src/connection.properties");
			props.load(in);	
			in.close();
		
			String driver=props.getProperty("driver");
			Class.forName(driver.trim());
			
			String url=props.getProperty("connection-url");
			
			String username=props.getProperty("user");
			String password=props.getProperty("password");
			 con=DriverManager.getConnection(  
					url,username,password);
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		 catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
}
	


