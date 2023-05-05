package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import fles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GoogleAPIProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//POST
		
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String response = given().queryParam("key","qaclick123").header("Content-Type",
		 "application/json").body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		 .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		 
		 System.out.println(response);
		 JsonPath js =  new JsonPath(response);
		 String placeid=js.getString("place_id");
		 
		 //PUT
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String updateResp = given().queryParam("key","qaclick123").header("Content-Type","application/json").
		 body(Payload.UpdateAddress(placeid, "30, side test layout, cohen 09")).when().put("/maps/api/place/update/json")
		 .then().assertThat().statusCode(200).extract().response().asString();
		 System.out.println(updateResp);
		 
		 //GET	 			
		
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
			  given().queryParam("key","qaclick123").queryParam("place_id",placeid)
			  .header("Content-Type","application/json").when().get(
			  "/maps/api/place/get/json").then().log().all() .assertThat().statusCode(200);
		
		//DELETE
		
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String deletePlace =  given().queryParam("key","qaclick123").queryParam("place_id",placeid)
			  .header("Content-Type","application/json").when().delete("/maps/api/place/delete/json").then().assertThat()
			  .statusCode(200).extract().response().asString();
		// JsonPath js1 = new JsonPath(deletePlace);
			//	 String message = js1.getString("status");
			  System.out.println("Place deleted :" +deletePlace);
			  
	}
}
