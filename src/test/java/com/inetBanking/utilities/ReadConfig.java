package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;


import java.util.Properties;

public class ReadConfig {
 
	
	Properties pro;
	
	public ReadConfig() 
	{
		File src = new File("./configuration/config.properties");
		
		
		try {
			FileInputStream fis = new FileInputStream(src);
			
			pro= new Properties();
			pro.load(fis);
		} catch (Exception e) {
		System.out.println("Exception is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getApplicationUR()
	{
		String url= pro.getProperty("baseURL");
		return url;
}
	public String getUsername()
	{
		String username = pro.getProperty("username");
		return username;
	}
	public String getPassword()
	{
		String password = pro.getProperty("password");
		return password;
	}
	public String getFirefoxPath()
	{
		String firefox = pro.getProperty("Firefoxpath");
		return firefox;
	}
	public String getChromePath()
	{
		String chrome = pro.getProperty("Chromepath");
		return chrome ;
	}
}