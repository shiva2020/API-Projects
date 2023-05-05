package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fles.Payload;
import io.restassured.path.json.JsonPath;

public class SumOfCourses {
	
	@Test()
	public void sumofcourses()
	{
		int sum=0;
		JsonPath js=new JsonPath(Payload.CoursePrice());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			System.out.println(amount);
			sum=sum+amount;
			
		}
		System.out.println("Sum of all: " +sum);
		int purchaseamt=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount: " +purchaseamt);
		SoftAssert sassert = new SoftAssert();
		//sassert.assertEquals(sum, purchaseamt, "Price is not similar");
	Assert.assertEquals(sum, purchaseamt, "Price is not similar");
	}

}
