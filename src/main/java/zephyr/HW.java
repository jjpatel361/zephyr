package zephyr;
import static spark.Spark.*;


public class HW {
	
    public static void main(String[] args) {
    	
        
        ConfigParameters.intialize();
        MYSQLDBConnector.initialize();
        get("/api", (req, res) -> "Hello World");
        
    }
    
   
}
