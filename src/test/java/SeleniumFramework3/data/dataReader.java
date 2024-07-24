package SeleniumFramework3.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

// In this class you can write n number of utilities to retrive data for your json.	
	
	
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	
	{
		// Read json and convert it to String. Copy path of that json file.
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework3//data//PurchaseOrder.json")
				, StandardCharsets.UTF_8);
		// Now, convert String to HashMap, for that need Jackson databind dependency from Mevan Repository.
		// Gapchup path karaycha, kay ata karnar.
		// It will convert string to HashMap and put it in a List.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		{});
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
