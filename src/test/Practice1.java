package test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import fles.Payload;

public class Practice1 {
	String placeid ="";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com/#/index";
		
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.extract().asString();
		System.out.println(response);
		JsonPath js=ReUsableMethods.rawTojson(response);
		String placeid=js.get("place_id");
		System.out.print("placeid:");
		
		String newAddress="Summer Walk, Africas";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.UpdateAddress(placeid, newAddress)).
		when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));		
					
			  String getresp = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeid).header("Content-Type", "application/json").
					  when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
			  
			  JsonPath js1=ReUsableMethods.rawTojson(getresp);
			  String actualaddress=js1.get("address"); 
			  System.out.println("Actual Address:" +actualaddress);
			  System.out.println("New Address:" +newAddress);
			 Assert.assertEquals(actualaddress, newAddress);
			 
			 System.out.println("Script worked correctly");
	}
}
