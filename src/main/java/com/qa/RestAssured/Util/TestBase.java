package com.qa.RestAssured.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public void init() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("/Users/dineshkumarrajendran/Documents/Webservice_Automation/RestAssuredAPIAuto/src/main/java/com/qa/RestAssured/Config/config.Properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
