import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class DriverScript {
	WebDriver driver;
	 static String xlPath;
	 static String[][] xTC,xTS;
	 static int xTC_r;
	static int xTC_c;
	static int xTS_r;
	static int xTS_c;
	public static void main(String[]args) throws Exception{
		//Read the Test Cases,Teststeps from Excel sheet
		xlPath = "C:\\Users\\Administrator\\Desktop\\KDF_AAUT\\TestPlan.xlsx";
	 xTC= readXL(xlPath,"Testcases");
	 xTS= readXL(xlPath,"Teststeps");
	xTC_r = xTC.length;
	xTC_c=xTC[0].length;	
	xTS_r = xTS.length;
	xTS_c = xTS[0].length;
	
	
	System.out.println("TC rows : "+ xTC_r);
	System.out.println("TC cols : "+ xTC_c);
	System.out.println("TS rows : "+ xTS_r);
	System.out.println("TS cols : "+ xTC_c);
	
	
	//Go to each row in TC sheet and see if executable]
	//If yes go to each row in the TS and and verify if it belongs to that TC
	//Call the corresponding Resuable function
	//Update the Excel with that result
	}
	//Reusable keyword functions
	
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

	



// Method to read XL
public static String[][] readXL(String fPath, String fSheet) throws Exception{
	// Inputs : XL Path and XL Sheet name
	// Output : 2D Array
	int xRows,xCols;
	String[][] xData;  

	File myxl = new File(fPath);                                
	FileInputStream myStream = new FileInputStream(myxl);                                
	HSSFWorkbook myWB = new HSSFWorkbook(myStream);                                
	HSSFSheet mySheet = myWB.getSheet(fSheet);                                 
	xRows = mySheet.getLastRowNum()+1;                                
	xCols = mySheet.getRow(0).getLastCellNum();   
	xData = new String[xRows][xCols];    
	
	for (int i = 0; i < xRows; i++) {                           
		HSSFRow row = mySheet.getRow(i);
		for (int j = 0; j < xCols; j++) {                               
			HSSFCell cell = row.getCell(j);
			String value = "-";
			if (cell!=null){
				value = cellToString(cell);
			}
			xData[i][j] = value; 
			System.out.print(" >> ");
			System.out.print(value);
		}        
		System.out.println("");
	}    
	myxl = null; // Memory gets released
	return xData;
}

//Change cell type
public static String cellToString(HSSFCell cell) { 
	// This function will convert an object of type excel cell to a string value
	int type = cell.getCellType();                        
	Object result;                        
	switch (type) {                            
	case HSSFCell.CELL_TYPE_NUMERIC: //0                                
		result = cell.getNumericCellValue();                                
		break;                            
	case HSSFCell.CELL_TYPE_STRING: //1                                
		result = cell.getStringCellValue();                                
		break;                            
	case HSSFCell.CELL_TYPE_FORMULA: //2                                
		throw new RuntimeException("We can't evaluate formulas in Java");  
	case HSSFCell.CELL_TYPE_BLANK: //3                                
		result = "%";                                
		break;                            
	case HSSFCell.CELL_TYPE_BOOLEAN: //4     
		result = cell.getBooleanCellValue();       
		break;                            
	case HSSFCell.CELL_TYPE_ERROR: //5       
		throw new RuntimeException ("This cell has an error");    
	default:                  
		throw new RuntimeException("We don't support this cell type: " + type); 
	}                        
	return result.toString();      
}

// Method to write into an XL
public static void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{

	File outFile = new File(fPath);
	HSSFWorkbook wb = new HSSFWorkbook();
	HSSFSheet osheet = wb.createSheet(fSheet);
	int xR_TS = xData.length;
	int xC_TS = xData[0].length;
	for (int myrow = 0; myrow < xR_TS; myrow++) {
		HSSFRow row = osheet.createRow(myrow);
		for (int mycol = 0; mycol < xC_TS; mycol++) {
			HSSFCell cell = row.createCell(mycol);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(xData[myrow][mycol]);
		}
		FileOutputStream fOut = new FileOutputStream(outFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}
}
}