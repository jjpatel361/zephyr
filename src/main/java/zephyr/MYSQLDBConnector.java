package main.java.zephyr;

import java.sql.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	public ResultSet getAllRows(String tableName) throws Exception
	{
		String query = "SELECT * FROM " + tableName;
		
		return executeQuery(query);
	}
	
	public ResultSet getRows(String tableName, int numRows) throws Exception
	{
		String query = "SELECT * FROM " + tableName + " LIMIT " + numRows;
		
		return executeQuery(query);
	}
	
	public ResultSet getRowsByColumnNames(String tableName, ArrayList<String> columns) throws Exception
	{
		String columnNames = "";
		for (String column : columns) {
			columnNames += column + " ";
		}
		
		String query = "SELECT " + columnNames + "FROM " + tableName;
		
		return executeQuery(query);
	}
	
	public JsonObject getJsonFromTable(ResultSet rs) throws Exception
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int numColumns = rsmd.getColumnCount();
		
		String json = "{\"results\":";
		
		while (rs.next()) {
			json += "[{";
			
			for (int i = 1; i <= numColumns; i++) {
				String colName = rsmd.getColumnName(i);
				json += "\"" + colName + "\":";
				
				switch (rsmd.getColumnType(i)) {
					case Types.INTEGER:
						json += rs.getInt(i);
						break;
					
					case Types.VARCHAR:
						json += "\"" + rs.getString(i) + "\"";
						break;
				}
				if (i < numColumns) {
					json += ",";
				}		
			}
			
			json += "}]";
			
			if (!rs.last()) {
				json += ",";
			}
		}
		
		json += "}";
		JsonParser parser = new JsonParser();
		
		return parser.parse(json).getAsJsonObject();
	}
}
