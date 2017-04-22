package zephyr;
import static spark.Spark.*;

public class HW {
	
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

    }
}
