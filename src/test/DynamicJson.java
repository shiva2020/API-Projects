package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	String Id="";
	@Test(dataProvider="Booksdata")
	public void AddBook(String isbnval, String aisleval)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").
		body(Payload.Addbook(isbnval,aisleval)).
		when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		JsonPath js=ReUsableMethods.rawTojson(response);
		Id=js.get("ID");
		System.out.println(Id);
		DeleteBook(Id);
		}
	
	
	public void DeleteBook(String Id)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").body(Payload.DeleteBook(Id)).when().post("Library/DeleteBook.php").then().log().all()
		.assertThat().statusCode(200).extract().response().toString();
		JsonPath js=ReUsableMethods.rawTojson(response);
		String resp=js.get("Msg");
		System.out.print("----------");
		System.out.println(resp);
	}
	
	
	@DataProvider(name="Booksdata")
	public Object[][] getdata()
	{
		return new Object[][] {{"sbg14341","1011344"},{"ghj1342","22421"},{"de33443","33551"},{"ghj1344","22421"},{"de33445","33551"}};
	}
	
}
