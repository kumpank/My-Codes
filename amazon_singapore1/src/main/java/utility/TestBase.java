package utility;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.amazon.utils.PropertiesFileReader;


public class TestBase extends PropertiesFileReader
{
	static PropertiesFileReader prop=new PropertiesFileReader();
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;

	public static String amazonUrl = prop.getProperty("url");



	public static SimpleDateFormat htmlfolderFormat = new SimpleDateFormat("dd-MMM-yyyy") ;
	public static SimpleDateFormat htmlfileFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss") ;
	public static String htmlfolderName ;
	public static String htmlfileName ;
	public static long htmlfileNumber ;
	static String cart_product ;
	
	static String price ;
	
	
	@BeforeSuite
	public void setUp() {
		System.out.println(amazonUrl);
		htmlfolderName=	htmlfolderFormat.format(new Date());
		htmlfileName=htmlfileFormat.format(new Date());
		htmlfileNumber=System.currentTimeMillis();
		
		String htmlfpath = System.getProperty("user.dir")+"/HtmlReports/AmazonAutomationReport"+htmlfileName+htmlfileNumber + ".html";

		htmlReporter = new ExtentHtmlReporter(htmlfpath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows 8 Server R2");
		extent.setSystemInfo("Host Name", "Pankaj Kumar");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Pankaj Kumar");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Amazon Automation POC Report");
		htmlReporter.config().setReportName("Amazon Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

		driver = launchChrome();
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	public static WebDriver launchChrome() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("test-type");
		Options.addArguments("disable-infobars");
		Options.addArguments("start-maximized");
		Options.addArguments("--js-flags=--expose-gc");
		Options.addArguments("--enable-precise-memory-info");
		Options.addArguments("--disable-popup-blocking");
		Options.addArguments("--disable-default-apps");
		return driver = new ChromeDriver(Options);
		
	}

	public static void launchTestNgReport() throws Exception {
		maximize();
		String tesngreportfile =  "file://C:/Users/pankaj/workspace/amazon_singapore1/test-output/emailable-report.html";
		driver.get(tesngreportfile);
		screenshot();
		Thread.sleep(5000);
	}

	public static void launchExtentReport() throws Exception {
		String extentreprotfile = "file:///"+System.getProperty("user.dir")+"/HtmlReports/AmazonAutomationReport"+htmlfileName+htmlfileNumber+".html";
		driver.get(extentreprotfile);
		screenshot();
	}
	
	
	public static void screenshot() throws Exception {
		
		
		try {
			SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss") ;
			SimpleDateFormat folderName = new SimpleDateFormat("yyyy-MMM-dd") ;
			
			long fileno = System.currentTimeMillis();
			
			String fpath = System.getProperty("user.dir")+"/Screenshot/"+folderName.format(new Date())+"/Pic "+fileName.format(new Date())+" "+fileno + ".png";
			
			File dfile = new File(fpath);
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, dfile);
			test.addScreenCaptureFromPath(fpath);
			} 
			catch (Exception e) {
			//e.printStackTrace(); 
				System.out.println("Exception occured during screenshot");
			}

			}

	public static void minimize() {
		driver.manage().window().setSize(new Dimension(10,10));
	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void signin(String s0,String p0) throws Exception {
		
		System.out.println("\n Amazon webportal Verification Started.....");
		
		//minimize();
		
		driver.get(amazonUrl);
		screenshot();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(s0.equalsIgnoreCase("username")||s0.equalsIgnoreCase("username1")){
		 WebElement Mouse_hover_on_Sign_=driver.findElement(By.xpath("//*[contains(text(),'Hello. Sign in')]"));
			Actions action=new Actions(driver);
				
			action.moveToElement(Mouse_hover_on_Sign_).build().perform();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
			driver.findElement(By.xpath("//*[contains(text(),'Sign in')]")).click();
	
		
	
		
	driver.findElement(By.id("ap_email")).sendKeys(prop.getProperty(s0));
		
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("continue")).click();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		if(p0.equalsIgnoreCase("password1")){
		driver.findElement(By.id("ap_password")).sendKeys(prop.getProperty(p0));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("signInSubmit")).click();
		}	
	}
	public static void login(String s1,String p1,String t1,String tc) throws Exception {
		switch (tc) {
     case ("LoginAmazon"): 
		
		
			signin("username","");
		if (driver.findElement(By.xpath("//*[contains(text(),' We cannot find an account with that email address')]")).isDisplayed()) 
		{
			screenshot();
			System.out.println( " Wrong Credential");
		}
		
break;
     case ("Validatetitle"): 
     
    	 signin("username1","password1");
    	 if(!t1.contains(" "))
    	 {
			 
			if (driver.getTitle().contains("Title of Page")&& t1.contains("Your Account") ) 
			{	
			System.out.println( " correct credentials");
		
			}
			
			
			
     }
    	 break;	
     case ("checkcart"): 
     
    	 if(!p1.contains(" ")){
    		 signin("username1","password1");
    	 }
    	 driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement prod=driver.findElement(By.xpath("//div[@id='a-page']"));
			WebElement cost=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-price sc-price sc-white-space-nowrap  sc-price-sign']"));
			if (prod.getText().contains(cart_product)){
			System.out.println( "passed");
			screenshot();
		
			}
    	 if(p1.contains(" ")){
    		  
    	 
			if (prod.getText().contains(cart_product) && cost.getText().contains(price)){
				System.out.println( "passed");
				screenshot();
			}
		 
	}
     
    	 break;
    
			}
		}
		
		public static void pickPrduct(String s2,String p2,String pr) throws Exception {
		
			//System.out.println(prop.getProperty(p2));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			screenshot();
			
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(prop.getProperty(pr));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement Capture_product_name=driver.findElement(By.xpath("//*[@class='a-size-medium s-inline  s-access-title  a-text-normal']"));
			System.out.println  (Capture_product_name.getText());
			cart_product=Capture_product_name.getText();
			
			WebElement Validate_price=driver.findElement(By.xpath("//*[@class='sx-price-whole']"));
			price=Validate_price.getText();
			System.out.println( price);
			screenshot();
			Capture_product_name.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,1400)"); 
		 
			 driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 js.executeScript("window.scrollBy(0,300)");
			
			 login(" ", " ", " ","checkcart");
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	 
			 WebElement Mouse_hover_on_Sign_link=driver.findElement(By.xpath("//*[contains(text(),'Hello, kumar')]"));
			Actions action=new Actions(driver);
				
			action.moveToElement(Mouse_hover_on_Sign_link).build().perform();
			
			driver.findElement(By.xpath("//*[contains(text(),'Sign Out')]")).click();
	
			
		
	
		
	} 

	

	
	@AfterSuite
	public void tearDown() throws Exception {
		extent.flush();
		launchTestNgReport();
		launchExtentReport();
	}
}