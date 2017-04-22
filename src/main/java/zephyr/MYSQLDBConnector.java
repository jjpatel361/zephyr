package main.java.zephyr;

import java.sql.*;

public class MYSQLDBConnector
{
	private static final String CLASSNAME = "com.mysql.jdbc.Driver"; 
	private static final String RDS_HOSTNAME = "jdbc:mysql://zepyhrdb.cb2tfamnh1z2.us-east-1.rds.amazonaws.com:3306/";
	private static final String RDS_PASSWORD = "hackru2017";
	private static final String RDS_USERNAME = "admin";
	
	public String dbName = "";
	public String dbUrl = "";
	protected boolean connected = false;
	
	protected Connection conn;
	
	public MYSQLDBConnector(String dbName)
	{
	    this.dbName = dbName;
	    this.dbUrl = RDS_HOSTNAME + dbName;
	}
	
	public boolean initialize()
	{
	    boolean retval = true;
	    
	    try {
	        Class.forName(CLASSNAME);
	
	        conn = DriverManager.getConnection(dbUrl,RDS_USERNAME,RDS_PASSWORD);
	        
	        connected = true;
	    } catch(Exception e) {
	    	retval = false;
	    } 
	    
	    return retval;
	}
	
	public ResultSet executeQuery(String query) throws Exception
    {
        if (!connected) {
            initialize();
        }
        
        ResultSet results = null;
        
        try {
            Statement stmt = conn.createStatement();
            results = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new Exception("executeQuery(): error");
        }
        return results;
    }
}
