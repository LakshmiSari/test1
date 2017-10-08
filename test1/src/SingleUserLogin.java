import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingleUserLogin {
	WebDriver driver;
	//variables to test Data
			String dBrowser, dURL,dFname,dLname,dEmail,dPassword,dconfirmationMsg;
			//Variables for Element Identification
			
			String 	eJoinUS,eGetPlanSingleUser,eFname,eLname,eEmail,ePassword,eproceed,eSmallTeam;
	@Before
	public void setup(){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\BrowserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\BrowserDrivers\\chromedriver.exe");
	    //driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
	  //Initialize the test data
	  	//	dBrowser= "chrome";
	  		dURL="http://dev.atomic77.in/ANATv1/#";
	  	dFname = "lakshmi";
	  	dLname= "vala";
	  	dEmail= "sirideep.boddu@gmail";
	  	dPassword= "valasapalli";
	  	dconfirmationMsg = "successfully registred";
	  	//initialize the Element variables
	  	eJoinUS= "Join US";
		eGetPlanSingleUser ="Get this plan";
		eFname="//input[@id='firstname']";
		eLname="//input[@id='lastname']";
		eEmail="//input[@id='email']";
		ePassword="//input[@id='password']";
		eproceed="//button[@id='singlebutton']";
		eSmallTeam = "//a[@href='/ANATv1/Users/register/smallteam']";
	  		
			
	}
	@Test
	public void mainTest(){
		//TestCase:AA_UR_001 single user sign up
	
		//Navigate to the Url
		driver.get(dURL);
		
		//Click on join us link
		driver.findElement(By.linkText(eJoinUS)).click();
		//Click on get this plan for single user
		driver.findElement(By.linkText(eGetPlanSingleUser)).click();
		//Enter the first name
		driver.findElement(By.xpath(eFname)).sendKeys(dFname);
		//Enter the last name
		driver.findElement(By.xpath(eLname)).sendKeys(dLname);
		//Enter the Email
		driver.findElement(By.xpath(eEmail)).sendKeys(dEmail);
		//Enter the Password
		driver.findElement(By.xpath(ePassword)).sendKeys(dPassword);
		//Click on Proceed
		driver.findElement(By.xpath(eproceed)).click();
		//Get Confirmation over it
		//
		
		//TestCase:AA_ur002 Small Team Sign Up
		
		//Navigate to the Url
				driver.get(dURL);
				
				//Click on join us link
				driver.findElement(By.linkText(eJoinUS)).click();
				//Click on get this plan for single user
				//driver.findElement(By.linkText(eGetPlanSingleUser)).click();
				driver.findElement(By.xpath(eSmallTeam)).click();;
				//Enter the first name
				driver.findElement(By.xpath(eFname)).sendKeys(dFname);
				//Enter the last name
				driver.findElement(By.xpath(eLname)).sendKeys(dLname);
				//Enter the Email
				driver.findElement(By.xpath(eEmail)).sendKeys(dEmail);
				//Enter the Password
				driver.findElement(By.xpath(ePassword)).sendKeys(dPassword);
				//Click on Proceed
				driver.findElement(By.xpath(eproceed)).click();
				//Get Confirmation over it
				

		
		


	
	
	}
	@After
	public void cleanup(){
		//	Close browser
		//driver.quit();
		
	}
	
	
	

}
