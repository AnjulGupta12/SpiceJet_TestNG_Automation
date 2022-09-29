package SpicejetAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_ClassSpiceJet{
	
//	login->flight finder->select flight->book flight->flight confirmation		
	
	
	public static WebDriver driver;
//	Actions actions = new Actions(driver);		//don't declare obj outside of class
  //  JavascriptExecutor js = (JavascriptExecutor) driver;			//use for scroll of the element but mouse hover(move to element) do work here

	//METHOD 1 : for wait
	public void wait(int wt) {	
		try {
			Thread.sleep(wt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//METHOD2
	public void captureScreenshot(String msg) {							// FOR SCREEENSHOT

		System.out.println("Screenshot for " + msg);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss"); //yyyy-MM-dd
		String dateTime = sdf.format(date);
		String fileName = "screenshot_" + dateTime;
		
		
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(".\\src\\test\\resources\\Screenshots\\" + fileName + ".png");		// EXPLAIN PATH!   . FOR CURRENT PROJECT FOLDER -> src->test-> resources -> screenshot
		
		
			try {
				FileUtils.copyFile(srcFile, destFile);										// TO COPY SCREENSHOT FILE IN DESTINATION FILE 
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	}
	
	
	@BeforeClass
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver",".\\UtilitesRequire\\ChromeDriver\\chromedriver105.exe");
		
//		if (prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		} else if (prop.getProperty("Browser").equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//		}
// YOU NEED TO ADD JAR OF WEBDRIVER MANAGER
//		// to launch chrome browser
		WebDriverManager.chromedriver().setup();
//		
//		// to launch firefox browser
//		WebDriverManager.firefoxdriver().setup();
		driver = new ChromeDriver(); // FirefoxDriver();
		driver.get("https://www.spicejet.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		System.out.println("Test Step:  LOGIN -> FLIGHT FINDER -> SELECT FLIGHT -> BOOK FLIGHT -> FLIGHT CONFIRMATION -> LOGOUT");
		
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}
	
	
	
  @Test(priority=1)
  public void LogIn() {
	  By LoginBtn = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[2]/div[1]/div/div[3]/div[1]/div/div");
	  By EmailRBtn = By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[3]/div/div/div[2]/div[2]/div/div[2]");
	  By EmailTxtBox = By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[4]/div[2]/input");
	  By PassTxtBox = By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[5]/div[1]/div[2]/input");
	  By LoginBtn2 = By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[5]/div[3]");
	  By VisibilityTest = By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[3]/div/div/div[2]/div[2]/div");
	  driver.findElement(LoginBtn).click();
	  wait(2020);
//	  WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(VisibilityTest));
//	  Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"container\"]/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[1]/div[1]/div[1]/div[1]/input")));
//	 WebElement visiTest = driver.findElement(VisibilityTest);
//	 if(visiTest.isDisplayed()==true)
	  driver.findElement(EmailRBtn).click();
	  wait(900); 
	  driver.findElement(EmailTxtBox).sendKeys("anjulgupta1205@gmail.com");
	  driver.findElement(PassTxtBox).sendKeys("password");
	  wait(500);
	  driver.findElement(LoginBtn2).click();
	  wait(6000);
	  
  }
  
  
  
  
  
  @Test(priority=2)
  public void FlightFinder() {
	  
	  By FlightOption = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[1]/div[1]/div[2]");
	  By OneWayRB = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[2]/div/div[2]/div[2]/div/div[1]");
	  By FromTB = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[1]/div");
	  By ToTB = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[3]/div/div[2]/input");
//	  By DeptDate = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div/div[1]");
	  By ReturnDate = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div/div[2]/div[2]/div[1]");
	  By Passenger = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div");
	  By Currency = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[2]/div/div[2]");
	  By searchFlightBtn = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[7]/div[2]/div");

	 driver.findElement(FlightOption).click();
	 wait(1000);
	 driver.findElement(OneWayRB).click();
	 wait(3000);
	 driver.findElement(FromTB).sendKeys("Delhi");
	 wait(1000);
	 driver.findElement(ToTB).sendKeys("Mumbai (BOM)");
	 wait(1000);
	 			
//	 driver.findElement(DeptDate).click();															//no need of clicking this it opens automatically
//	 wait(900); 
	 WebElement dateChoice = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div[2]/div[3]/div[2]/div/div[2]/div/div[3]/div[2]/div[4]/div"));
		Actions actions = new Actions(driver);														//for mouse hover to select date
		actions.moveToElement(dateChoice).click().build().perform();								//used "actions" since 
		
	 
	 
	 wait(800);
	if( driver.findElement(ReturnDate).isEnabled()==false)
	{
		System.out.print("It is one way Ticket");
	}
	
//	Actions actions = new Actions(driver);
	WebElement Passengers = driver.findElement(Passenger);
	actions.moveToElement(Passengers).click().build().perform();									// for passenger stuff
	
	
	WebElement adultmore = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[3]"));
	WebElement adultless = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]"));
	WebElement childmore = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[3]"));
	WebElement childless = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]"));
	WebElement infantmore = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[3]"));
	WebElement infantless = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]"));
	WebElement doneBTN = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[2]/div/div[2]/div"));
	wait(900);
	for(int i =0; i<2; i++) {
	actions.moveToElement(adultmore).click();
	wait(500);
	}
	actions.build().perform();
	wait(900);
	for(int i =0 ; i<2; i++) {
		actions.moveToElement(childmore).click();
		wait(500);
	}
	actions.build().perform();
	wait(900);
	
	actions.moveToElement(doneBTN).click().perform();
	wait(500);

	driver.findElement(Currency).click();
	wait(1000);
    
     WebElement INR = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[5]/div[2]/div[2]/div[2]/div/div[1]"));
     actions.moveToElement(INR).click().build().perform();
     wait(2000);
     driver.findElement(searchFlightBtn).click();
     wait(8000);
     
     WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(40));
 	waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-container\"]/div/div[3]/div/div[1]/div[1]/div/div[1]")));
 	 
  }
  
  
  
  
  
  
  
  
  @Test(priority=3)
	 public void SelectFlight() {
		WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(40));
		Actions actions = new Actions(driver);	
		wait(1000);
		//radio button of "All flight"
		driver.findElement(By.xpath("//*[@id=\"list-results-section-0\"]/div[1]/div[2]/div[2]/div/div[2]/div")).click();
		wait(2000);
		//first flight, first radio button 
		WebElement FirstFlight = driver.findElement(By.xpath("//*[@id=\"list-results-section-0\"]/div[5]/div[1]/div/div[2]/div[1]/div/div/div"));
		//if fight is available
		if(FirstFlight.isDisplayed()==true)
		{
			actions.moveToElement(FirstFlight).click().build().perform();
			wait(6000);
			waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("replacedbutton")));
			wait(1000);
		}
		else
		{
			System.out.print("There is no flight Available");
			driver.quit();
		}
		wait(3000);
//		WebElement ContinueBTN = driver.findElement(By.xpath("//*[@id=\"replacedbutton\"]/div[1]"));
//		actions.moveToElement(ContinueBTN).click().build().perform();
		driver.findElement(By.id("replacedbutton")).click();
		//replacedbutton
		wait(1000);
	 	waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("upgrade_spicemax")));
	 	wait(1000);
	 	driver.findElement(By.id("continue2")).click();
	 	wait(1000);
//	 	WebElement SkipBTN = driver.findElement(By.id("continue2"));
//	 	actions.click(SkipBTN).build().perform();
	 	waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"primary-contact-details\"]/div[1]")));
	 	wait(3000);
	 }
  
  
  @Test(priority=4)
  public void BookFlight() {
	  By Title = By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[1]/div[2]/div");
	  By FirstName = By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[2]/div/div/div[2]/input");
	  By LastName = By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[3]/div/div/div[2]/input");
	  By ContactNo = By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[4]/div/div[2]/input");
	  By Email = By.xpath("//*[@id=\"primary-contact-details\"]/div[4]/div[1]/div/div/div[2]/input");
	  By Country = By.xpath("//*[@id=\"primary-contact-details\"]/div[4]/div[2]/div[2]/div");
	  By Town = By.xpath("//*[@id=\"primary-contact-details\"]/div[4]/div[3]/div/div/div[2]/input");
	  By PrimaryPassengerRB = By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[1]/div/div/div/div[1]/svg/g/rect");
	  By PassangerTitle = By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]");
	  By PassengerFirstName = By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/input");
	  By PassengerLastName = By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[2]/div[3]/div/div/div[2]/input");
	  By PassengerMobileNo = By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[2]/div[4]/div/div[1]/div/div[2]/input");
	  By ContinueBTN = By.xpath("//*[@id=\"travellers-view\"]/div[2]/div/div/div[2]");
	  
	  
	  WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(20));
	  Actions actions = new Actions(driver);	
		wait(2000);
		actions.click(driver.findElement(By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[1]/div[2]/div")));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"primary-contact-details\"]/div[3]/div[1]/div[2]/div[2]/div/div/div[3]/div"))).click().build().perform();
		wait(800);
		
		driver.findElement(FirstName).clear();
		wait(200);
		driver.findElement(FirstName).sendKeys("ANJUL");
		wait(800);
		
		driver.findElement(LastName).clear();
		wait(200);
		driver.findElement(LastName).sendKeys("GUPTA");
		wait(800);

		driver.findElement(ContactNo).click();
		wait(800);
		driver.findElement(ContactNo).sendKeys("8448996791");
		wait(800);
		
		driver.findElement(Email).clear();
		wait(800);
		driver.findElement(Email).sendKeys("anjulgupta1205@gmail.com");
		wait(800);
		
		actions.click(driver.findElement(Country));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"primary-contact-details\"]/div[4]/div[2]/div[2]/div[2]/div[2]/div[101]/div"))).click().build().perform();
		wait(800);

		driver.findElement(Town).sendKeys("HARIDWAR");
		wait(800);
		
		//PASSENGER PART
		
		driver.findElement(PrimaryPassengerRB).click();
		wait(800);
		
		actions.click(driver.findElement(PassangerTitle));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/div[3]/div"))).click().build().perform();
		wait(800);
		
		driver.findElement(PassengerFirstName).sendKeys("ANJUL");
		wait(800);
		
		driver.findElement(PassengerLastName).sendKeys("GUPTA");
		wait(800);
		
		driver.findElement(PassengerMobileNo).sendKeys("8449995426");
		wait(800);
		
		driver.findElement(By.xpath("//*[@id=\"pax-item-MCFBRFQ-\"]/div[2]/div/div/div[4]/div/div")).click();
		wait(800);
		
		//PASSANGER PART2
	
		actions.click(driver.findElement(By.xpath("//*[@id=\\\"pax-item-MSFBRFQ-\\\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\\\"pax-item-MSFBRFQ-\\\"]/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/div[3]"))).click().build().perform();
		wait(800);
		
		driver.findElement(By.xpath("//*[@id=\\\"pax-item-MSFBRFQ-\\\"]/div[2]/div/div/div[1]/div[2]/div/div/div[2]/input")).sendKeys("PRIYANSHU");
		wait(800);
		
		driver.findElement(By.xpath("//*[@id=\\\"pax-item-MSFBRFQ-\\\"]/div[2]/div/div/div[1]/div[3]/div/div/div[2]/input")).sendKeys("GUPTA");
		wait(800);
		
		driver.findElement(By.xpath("//*[@id=\\\"pax-item-MSFBRFQ-\\\"]/div[2]/div/div/div[1]/div[4]/div/div[1]/div/div[2]/input")).sendKeys("9456963258");
		wait(800);
		
		driver.findElement(By.xpath("//*[@id=\"pax-item-MSFBRFQ-\"]/div[2]/div/div/div[3]/div/div/div")).click();
		wait(800);
		
		//PASSANGER PART3
		
		actions.click(driver.findElement(By.xpath("//*[@id=\"pax-item-MiFBRFQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"pax-item-MiFBRFQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/div[3]/div"))).click().build().perform();
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MiFBRFQ-\"]/div[2]/div/div/div[1]/div[2]/div/div/div[2]/input")).sendKeys("REENA");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MiFBRFQ-\"]/div[2]/div/div/div[1]/div[3]/div/div/div[2]/input")).sendKeys("GUPTA");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MiFBRFQ-\"]/div[2]/div/div/div[1]/div[4]/div/div[1]/div/div[2]/input")).sendKeys("9456964214");
		wait(800);
			
		driver.findElement(By.xpath("")).click();
		wait(800);
			
		//PASSANGER PART4
			
		actions.click(driver.findElement(By.xpath("//*[@id=\"pax-item-MyFDSEQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"pax-item-MyFDSEQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/div[1]"))).click().build().perform();
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MyFDSEQ-\"]/div[2]/div/div/div[1]/div[2]/div/div/div[2]/input")).sendKeys("SHIVI");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MyFDSEQ-\"]/div[2]/div/div/div[1]/div[3]/div/div/div[2]/input")).sendKeys("GUPTA");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-MyFDSEQ-\"]/div[2]/div/div/div[3]/div/div/div")).click();
		wait(800);
			
		//PASSANGER PART5
			
		actions.click(driver.findElement(By.xpath("//*[@id=\"pax-item-NCFDSEQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")));
		wait(800);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"pax-item-NCFDSEQ-\"]/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/div[1]"))).click().build().perform();
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-NCFDSEQ-\"]/div[2]/div/div/div[1]/div[2]/div/div/div[2]/input")).sendKeys("POOJA");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"pax-item-NCFDSEQ-\"]/div[2]/div/div/div[1]/div[3]/div/div/div[2]/input")).sendKeys("GUPTA");
		wait(800);
			
		driver.findElement(By.xpath("//*[@id=\"travellers-view\"]/div[2]/div/div/div[2]")).click();
		wait(800);
			
		//next page element
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-container\"]/div/div[5]/div/div/div[2]/div/div/div[1]/div[1]/div[1]")));
		wait(800);
			
  }
  
  
  @Test(priority=5)
  public void ConfirmBooking() {
	  
	  WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(20));
	  Actions actions = new Actions(driver);	
	
	  driver.findElement(By.xpath("//*[@id=\"addons-container\"]/div[2]/div[2]/div/div/div[2]/div/div[1]/div[1]/div[2]/div")).click();
	  //seat selection visibility
	  waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[13]/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]")));
		//selecting 7A seat
	  actions.moveToElement(driver.findElement(By.xpath("/html/body/div[13]/div/div/div[2]/div[2]/div[2]/div[7]/div/div[2]/div[2]/div[4]/div[37]/div/div/div/div/svg/g/g/path[2]"))).click();
	  //visiblity of heading of new frame
	  waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[206]/div/div/div/div/div[1]/div")));
		
	  actions.click(driver.findElement(By.xpath("/html/body/div[206]/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/svg/g/circle[1]")));
	  actions.click(driver.findElement(By.xpath("/html/body/div[206]/div/div/div/div/div[4]/div[1]/div")));
	  actions.click(driver.findElement(By.xpath("/html/body/div[206]/div/div/div/div/div[5]/div[3]/div/div")));
	  
	  
	  actions.click(driver.findElement(By.xpath("/html/body/div[13]/div/div/div[2]/div[2]/div[1]/div[3]/div[2]/div[2]/div"))).build().perform();
		
	  //driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[5]/div/div/div[2]/div/div/div[4]"));
	System.out.println("The last Continue Button for payment is not clicked deliberatly");
	
  }
  
  
  @Test(priority=6, enabled=true)
  public void LogOut() {
	  
	  Actions actions = new Actions(driver);	
	
	  
	  By LogoutDD = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div/div[3]/div[1]");
	  By LogoutBTN = By.xpath("//*[@id=\"main-container\"]/div/div[1]/div/div[3]/div[2]/div[3]");
	  
	  actions.click(driver.findElement(LogoutDD));
	  wait(1000);
	  actions.moveToElement(driver.findElement(LogoutBTN)).click().build().perform();
	  
  }
  
}
