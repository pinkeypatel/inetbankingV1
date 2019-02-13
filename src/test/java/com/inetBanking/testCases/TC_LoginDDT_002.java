package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.BaseClass;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginDDT_002 extends BaseClass{
	@Test(dataProvider="LoginData")
	public void loginDDt(String user, String pwd) throws Exception
	{
	LoginPage lp=new LoginPage(driver);
	
	lp.setUserName(user);
	logger.info("username getting");
	lp.setPassword(pwd);
	logger.info("password getting");
	lp.clickSubmit();
	Thread.sleep(5000);
	if(isAlertPresent()==true) {
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.warn("login failed");
	}
	else {
		Assert.assertTrue(true);
		lp.clickLogout();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();//close logout alert
		driver.switchTo().defaultContent();
		logger.warn("login passed successfully");
	}
	}
	//alert is present or not--->userdefined method
public boolean isAlertPresent() {
	try {
	driver.switchTo().alert();
	return true;
}catch(NoAlertPresentException e) {
	return false;
}
	
}
	@DataProvider(name="LoginData")
	 String[][] getData() throws IOException
	{
		String path =System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
	
	int rownum = XLUtils.getRowCount(path, "sheet1");
	int colcount = XLUtils.getcellcount(path, "sheet1", 1);
	
	String logindata[][]=new String[rownum][colcount];
	for(int i = 1;i<=rownum;i++) {
		for(int j=0;j<colcount;j++) {
			
			logindata[i-1][j]=XLUtils.getcellData(path, "sheet1", i, j);//1 0
		}
	}
	return logindata;
	}
}