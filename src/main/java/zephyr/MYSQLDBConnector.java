package zephyr;

import java.sql.*;

public class MYSQLDBConnector {
	
	private static final String CLASSNAME = "com.mysql.jdbc.Driver"; 
	private static final String RDS_HOSTNAME = "jdbc:mysql://zepyhrdb.cb2tfamnh1z2.us-east-1.rds.amazonaws.com:3306/user";
	private static final String RDS_PASSWORD = "hackru2017";
	private static final String RDS_DATABASE = "user";
	private static final String RDS_USERNAME = "admin";
	private static final String query = "SELECT * from employees";
	
	public static void initialize() {
		try{  
				
				Class.forName(CLASSNAME);  
				Connection con = DriverManager.getConnection(RDS_HOSTNAME,
														RDS_USERNAME,
														RDS_PASSWORD);
				
				java.sql.Statement stmt=con.createStatement();  
				ResultSet rs = stmt.executeQuery(query);  
				while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
				con.close();  
			}catch(Exception e){ 
				System.out.println(e);
			}  
	}  

}
