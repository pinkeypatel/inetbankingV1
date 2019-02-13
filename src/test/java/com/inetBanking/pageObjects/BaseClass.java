package com.inetBanking.pageObjects;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import org.apache.log4j.PropertyConfigurator;
public class BaseClass {
	ReadConfig readconfig = new ReadConfig();

	public String baseURL =readconfig.getApplicationUR();

	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
@BeforeClass
public void setup(String br) {
		logger=Logger.getLogger("ebanking");
		 PropertyConfigurator.configure("log4j.properties");
		 
		 if(br.equals("Firefox")) {
	System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
	driver=new FirefoxDriver();	
		 }
		 else if(br.equals("Chrome")) {
			 System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
				 driver = new ChromeDriver();
		 }
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(baseURL);
}

@AfterClass
public void tearDown() {
 driver.quit();
}

public void capturesceen(WebDriver driver, String tname) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir")+"\\Screenshots\\" + tname + ".png");
	FileUtils.copyFile(source, target);
	System.out.println("screenshot taken");
}
}
//System.getProperty("user.dir")+"//drivers//geckodriver.exe
//System.getProperty("user.dir")+"//drivers//chromedriver.exe"