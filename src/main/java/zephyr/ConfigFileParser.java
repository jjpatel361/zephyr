package zephyr;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigFileParser {
	static String configFileName = "config.json";
	
	public static ConfigFile parseFile()
	{
		String currDir = System.getProperty("user.dir");
		File[] files = new File(currDir).listFiles();
		ConfigFile cf = new ConfigFile();
		try
		{
			for (File f: files)
			{
				if(f.getName().equals(configFileName))
				{
					//got config.json file
					Gson gson = new Gson();
					BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
					cf = gson.fromJson(br,  ConfigFile.class);
					return cf;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			
		}
		return cf; //shouldn't reach
	}

}
