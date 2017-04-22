package zephyr;
import static spark.Spark.*;

public class HW {
	
    public static void main(String[] args) {
    	ConfigFile cf = ConfigFileParser.parseFile();
    	System.out.println("username " + cf.getUserName());
    	System.out.println("password " + cf.getPassword());
    	System.out.println("db name " + cf.getDatabaseName());
    	System.out.println("URL " + cf.getURL());
    	String [] tNames = cf.getTables();
    	for(String t: tNames)
    	{
    		System.out.println("table name is " + t);
    	}
        //get("/hello", (req, res) -> "Hello World");

    }
}
