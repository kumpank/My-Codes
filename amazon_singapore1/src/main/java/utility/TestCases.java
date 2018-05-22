package utility;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;
import com.google.common.io.Files;


import utility.TestBase;
import com.amazon.utils.PropertiesFileReader;

public class TestCases extends TestBase {

	@Test(priority = 1)
	public void LoginAmazon() throws IOException, Throwable {
		
		
		test = extent.createTest("incorrect login");
		System.out.println("incorrect login .......");
		System.out.println("===============================================================");
		login("username"," "," ","LoginAmazon");
		test.log(Status.INFO, MarkupHelper.createLabel("Login id " + " Successfully verified login in Amazon WebPortal", ExtentColor.GREEN));


		}
	
	@Test(priority = 2)
	public void Validatetitle() throws IOException, Throwable {
		
		
		test = extent.createTest("Title Validate");
		
		System.out.println("===============================================================");
		login("username1","password1","Your Account","Validatetitle");
		test.log(Status.INFO, MarkupHelper.createLabel("Tite  " + " Successfully verified Title in Amazon WebPortal", ExtentColor.GREEN));


		}
	
	@Test(priority = 3)
	public void LoginAmazonsuccesfull() throws IOException, Throwable {
		
		
		
		test = extent.createTest("pick login");
		System.out.println("pick product .......");
		System.out.println("===============================================================");
		pickPrduct(" "," ","product"); 
		test.log(Status.INFO, MarkupHelper.createLabel("product " + " Successfully selected product in Amazon WebPortal", ExtentColor.GREEN));

		}
	




@Test(priority = 4)
public void checkcart() throws IOException, Throwable {
	
	
	
	test = extent.createTest("check cartitems");
	System.out.println("check product .......");
	System.out.println("===============================================================");
	login("username1","password1"," ","checkcart");
	test.log(Status.INFO, MarkupHelper.createLabel("check product " + " Successfully verified product in Amazon WebPortal", ExtentColor.GREEN));

	}


} 
	
