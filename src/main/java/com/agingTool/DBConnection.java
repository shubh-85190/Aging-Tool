package com.agingTool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.microsoft.sqlserver.jdbc.SQLServerException;;
public class DBConnection 
{
	Connection conn;
	public DBConnection(String ServerName,String authMode,String user,String password) throws SQLException, ClassNotFoundException
	{
//		String ServerName = "BPLQADB01";
		String DBPortNo = "1433";
		
		String url = "jdbc:sqlserver://"+ServerName+"\\SQLEXPRESS;databaseName=master;integratedSecurity=true;portNumber="+DBPortNo+";trustServerCertificate=true";
		
		System.out.println("ServerName : " + ServerName);
		System.out.println("AuthMode : " + authMode);
		if(authMode.equalsIgnoreCase("SA"))
		{		
			System.out.println("User : " + user);
			System.out.println("Password : " + password);
			url = "jdbc:sqlserver://"+ServerName+"\\SQLEXPRESS;databaseName=master;user="+user+";password="+password+";portNumber="+DBPortNo+";trustServerCertificate=true";
		}
		
		System.out.println("DBPortNo : " + DBPortNo);
		
//		System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-17\\lib\\security\\cacert");
//		System.setProperty("javax.net.ssl.trustStorePassword", "Welcome@8519015673");  // If your TrustStore is password-protected

		
//		String url = "jdbc:sqlserver://"+ServerName+"\\SQLEXPRESS;databaseName=master;integratedSecurity=true;portNumber="+DBPortNo+";trustServerCertificate=true";
		
		System.out.println("URL :"+url);  
		//here sonoo is database name, root is username and password  
		
		 try {
			 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(url);
	            if(conn!=null)
	                System.out.println("Database Successfully connected");	            
		    } catch (SQLException e) {
		    	System.out.println("Inside Exception..........");
	            e.printStackTrace();
	        }
	}


	public ArrayList<String> getDBNames() throws Exception {
		if(conn==null)
		{
			System.out.println("Null Returned........");
			return null;
		}
		Statement stmt = conn.createStatement();		
        ResultSet rs= stmt.executeQuery("SELECT name FROM sys.databases with (nolock)");
        System.out.println(rs.next());
        rs.getStatement();
        //int i=1;
        ArrayList<String> myArrayList = new ArrayList<String>();
        while(rs.next()) {
//        	System.out.println(rs.getString(1));
        	 myArrayList.add(rs.getString(1));
        }
        myArrayList.sort(null);
        return myArrayList;
	}
	
	public String getCurrentDate(String dbname) throws Exception {
		try {
		Statement stmt = conn.createStatement();		
        ResultSet rs= stmt.executeQuery("SELECT top 1 tnpdate as TNPDATE FROM "+dbname+"..commontnp with(nolock) where atid=60 order by tnpdate");
        rs.getStatement();
        //int i=1;
        String currentDate;
        while(rs.next()) {
        	 currentDate=rs.getString("tnpdate");
        }
        
        return currentDate;
		}
		catch(SQLServerException sql)
		{
			return null;
		}
	}


}
