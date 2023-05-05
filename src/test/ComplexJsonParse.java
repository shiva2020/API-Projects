package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import fles.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) throws IOException
	{
	
	int allprice=0;
	//JsonPath js=new JsonPath(Payload.CoursePrice());
	JsonPath js =new JsonPath(new String(Files.readAllBytes(Paths.get("C:\\Users\\shiva\\OneDrive\\Desktop\\TestAPI.json"))));
	int coursecount=js.getInt("courses.size()");
	System.out.println("Number of courses: " +coursecount);
	int purchaseamount=js.getInt("dashboard.purchaseAmount");
	String website=js.getString("dashboard.website");
	System.out.println("Purchase amount: " +purchaseamount);
	System.out.println(website);
//	int coursecount=js.getInt("courses.size()");
	for (int i=0;i<coursecount;i++)
	{
	//String title=js.getString("courses[i].title");
	System.out.println(js.getString("courses["+i+"].title"));
	System.out.println(js.getString("courses["+i+"].price"));
	System.out.println(js.getString("courses["+i+"].copies"));
	System.out.println("");
	
	int price=js.getInt("courses["+i+"].price");
	int copies=js.getInt("courses["+i+"].copies");
	allprice = allprice + (price*copies);
	}
	
	for (int i=0;i<coursecount;i++)
	{
	//String title=js.getString("courses[i].title");
	String CourseTitle=js.getString("courses["+i+"].title");
	if(CourseTitle.equalsIgnoreCase("RPA"))
	{
		System.out.println(js.getString("courses["+i+"].copies").toString());
		break;
	}
	}	
	System.out.println("Total of all courses price: " +allprice);
	Assert.assertEquals(allprice, purchaseamount);
	if(purchaseamount==allprice)
	{
		System.out.println("Both price are same");
	}
	else
	{
		System.out.println("Both the prices are not same");
	}
}
}