package utils;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonDataReader {
	public String expectedTitle,expectedErrorMessage,productname,checkoutstatus,firstname,lastname,Zibcode;

	public List<String> usernames = new ArrayList<>();
	public List<String> passwords = new ArrayList<>();
	public List<String> usertypes = new ArrayList<>();


	public void dataReader(String userType) throws ParseException, IOException {


		String JSON_FILE_PATH =  System.getProperty("user.dir") + "/src/test/resources/testdata.json";
		File jsonFile = new File(JSON_FILE_PATH);

		FileReader reader = new FileReader(jsonFile);
		JSONParser jsonparse = new JSONParser();
		Object obj= jsonparse.parse(reader);
		JSONObject jsonData= (JSONObject) obj;
		JSONObject messages = (JSONObject) jsonData.get("messages");
		
		///////get messages////////
		expectedTitle =  (String) messages.get("expectedTitle");
		expectedErrorMessage = (String) messages.get("expectedErrorMessage");
		productname = (String) messages.get("productName");
		productname = (String) messages.get("productName");
		checkoutstatus = (String) messages.get("checkoutstatus");
        
		
		/////// get check out information //////////
		JSONObject orderInfo = (JSONObject) jsonData.get("checkoutinformation");
		firstname =  (String) orderInfo.get("fname");
		lastname = (String) orderInfo.get("lname");
		Zibcode = (String) orderInfo.get("code");
		
		
		////// get users ////////////
		JSONArray array= (JSONArray)jsonData.get("users");
		for (int i = 0; i < array.size(); i++) {

			JSONObject users= (JSONObject) array.get(i);
			String userTypeFromJson = (String) users.get("usertype");
	        if (userTypeFromJson.equals(userType)) {
	            usernames.add((String) users.get("username"));
	            passwords.add((String) users.get("password"));
	            return; // Exit the method after finding the matching user type
	            
	        }


		}

	}

}

