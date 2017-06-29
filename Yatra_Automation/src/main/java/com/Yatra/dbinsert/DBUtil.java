package com.Yatra.dbinsert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {
	
	//public static final Logger LOG = LogUtil.createLogger(DBUtil.class, "SQLDBLogs", true);

	
	//static Config config;
    public static Connection connection;
  /*  static{
    	try {
			config	=	Config.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	*/
    
    
	 public static Connection  sqlConnectionwithDBNameEx(String DBname) throws Exception 
	 {
          String DBusername="";
          String DBPassword="";
          String forwardPort="3306";
          String dbIP="";
		 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//System.out.println(DBusername+ " "+ config.getConfig("DBPassword"));
			connection = DriverManager.getConnection("jdbc:mysql://"+dbIP +":"+ forwardPort + "/" + DBname,DBusername,DBPassword);
		
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
		
	 }
	 
	 public static Connection  sqlConnectionForDBInserter(String DBname) throws Exception 
	 {
		 String DBusername="automationUSR";
         String DBPassword="aut!mat2onU00R";
         String forwardPort="3306";
         String dbIP="automationDB";
		 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//System.out.println(DBusername+ " "+ DBPassword);
			connection = DriverManager.getConnection("jdbc:mysql://"+dbIP +":"+ forwardPort + "/" + DBname,DBusername,DBPassword);
		
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
		
	 }

	public static void closeConnection() throws SQLException{
		connection.close();
		System.out.println("SQL DB connection closed");
	}
	
	
	
  }