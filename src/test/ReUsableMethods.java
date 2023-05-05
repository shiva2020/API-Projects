package test;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath rawTojson(String response)
	{
		JsonPath jp1=new JsonPath(response);
		return jp1;
	}

}
