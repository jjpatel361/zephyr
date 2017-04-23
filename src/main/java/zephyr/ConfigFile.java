package zephyr;
import java.io.*;

public class ConfigFile {

	private String userName;
	private String password;
	private String URL;
	private String databaseName;
	private String[] tables;
	
	public ConfigFile(String uName, String pass, String url, String dbName, String[] tables)
	{
		this.userName = uName;
		this.password = pass;
		this.URL = url;
		this.databaseName = dbName;
		this.tables = tables;
	}
	
	public ConfigFile()
	{
		super();
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getDatabaseName()
	{
		return this.databaseName;
	}
	
	public String getURL()
	{
		return this.URL;
	}
	
	public String [] getTables()
	{
		return this.tables;
	}
}
