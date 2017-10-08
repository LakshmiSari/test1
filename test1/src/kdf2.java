import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class kdf2 {
	WebDriver driver;
	//variables to test Data
			String dBrowser, dURL,dFname,dLname,dEmail,dPassword,dconfirmationMsg;
			//Variables for Element Identification
			
			String 	eJoinUS,eGetPlanSingleUser,eFname,eLname,eEmail,ePassword,eproceed,eSmallTeam;
	@Before
	public void setup(){
		
	    
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
	OpenBrowser("firefox")  ;
	maximiseBrowser();
	timeoutBrowser(10);
			
	}
	@Test
	public void mainTest(){
		//TestCase:AA_UR_001 single user sign up
	
		
		driver.get(dURL);
		clickLink(eJoinUS);
		//Click on get this plan for single user
		clickLink(eGetPlanSingleUser);
				//Enter the first name
		typeText(eFname,dFname);
		//Enter the last name
		typeText(eLname,dLname);
		//driver.findElement(By.xpath(eLname)).sendKeys(dLname);
		//Enter the Email
		typeText(eEmail,dEmail);
		//driver.findElement(By.xpath(eEmail)).sendKeys(dEmail);
		//Enter the Password
		typeText(ePassword,dPassword);
		//driver.findElement(By.xpath(ePassword)).sendKeys(dPassword);
		//Click on Proceed
		clickElement(eproceed);
		
		//driver.findElement(By.xpath(eproceed)).click();
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
	public  void cleanUp(){
	closeBrowser();
	}
	
	
	public void NavigateBrowser(String fdata){
		driver.navigate().to(fdata);
	}
	public void OpenBrowser (String fdata){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\BrowserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\BrowserDrivers\\chromedriver.exe");
	    //driver = new ChromeDriver();
	    
		driver.get(dURL);
	}
	
public void closeBrowser(){
	driver.quit();
}
public void maximiseBrowser(){
	driver.manage().window().maximize();
	
}
public void timeoutBrowser(int fdata){ 
	driver.manage().timeouts().implicitlyWait(fdata,TimeUnit.SECONDS);
}
public void clickLink(String fEID){
	driver.findElement(By.linkText(fEID)).click();
	
}
public void clickElement(String fEID){
	driver.findElement(By.xpath(fEID)).click();
}
public void typeText(String fEID,String fData){
	driver.findElement(By.xpath(fEID)).clear();
	driver.findElement(By.xpath(fEID)).sendKeys(fData);
}
}
