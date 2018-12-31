package com.qa.RestAssured.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.RestAssured.Util.TestBase;
import com.qa.RestAssured.Util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherInfoTests extends TestBase {
	
	@BeforeMethod
	public void setUp() {
		init();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] resultdata = TestUtil.getDataFromSheet(TestUtil.Weathersheetname);
		return resultdata;
	}
	
	@Test (dataProvider="getData")
	public void getWeatherDetailsWithCorrectCityNameTest(String City, String Temperature, String Humidity,
			String WeatherDescription, String WindSpeed, String WindDirectionDegree) {
		
		RestAssured.baseURI = prop.getProperty("serviceurl");
		RequestSpecification httprequest =  RestAssured.given();
		Response response = httprequest.request(Method.GET, "/"+City);
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody: "+responseBody);
		
		JsonPath jsonpath = response.jsonPath();
		
		String response_Temperature = jsonpath.get("Temperature");
		Assert.assertEquals(response_Temperature, Temperature);
		
		String response_Humidity = jsonpath.get("Humidity");
		Assert.assertEquals(response_Humidity, Humidity);
		
		String response_WeatherDescription = jsonpath.get("WeatherDescription");
		Assert.assertEquals(response_WeatherDescription, WeatherDescription);
		
		String response_WindSpeed = jsonpath.get("WindSpeed");
		Assert.assertEquals(response_WindSpeed, WindSpeed);
		
		String response_WindDirectionDegree = jsonpath.get("WindDirectionDegree");
		Assert.assertEquals(response_WindDirectionDegree, WindDirectionDegree);
	
		
		
//		capture the entire header contents
		Headers header = response.headers();
		System.out.println("\nHeader contents:\n"+header);
		
//		capture the specific header content
		String contenttype = response.getHeader("Content-Type");
		System.out.println("\nContent type from Headers: "+contenttype);
		

//		capture the response code
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, TestUtil.RESPONSE_CODE_200, "Status code mismatch");
		
//		capture the entire response line
		String statusline = response.getStatusLine();
		Assert.assertEquals(statusline.contains("200 OK"),true);		
	}
	

}
