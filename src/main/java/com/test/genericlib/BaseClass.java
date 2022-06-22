package com.test.genericlib;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(com.test.genericlib.TestListener.class)
public class BaseClass {
	public static WebDriver driver;
	public static Capabilities cap;
	public static Logger log = Logger.getLogger(BaseClass.class);
	public FileUtils fLib = new FileUtils();
	public static Properties prop;
	public DBConnection dbCon=new DBConnection();
	public static String envName;
	public String envUrl;
	public Connection schema1;
	public Connection schema2;
	
	
	@BeforeSuite
	public void beforeSuite() throws Throwable {
		prop=fLib.getPropertyFileObject();
		envName=prop.getProperty("Env");
		envUrl=prop.getProperty(envName+".url");
		
		log.info(GetData.PROP_READ.data()+envName);
		log.info(GetData.PROP_READ.data()+envUrl);
		System.setProperty("envName", envName);
		System.setProperty("envUrl", envUrl);
		log.info(GetData.PROP_LOAD_SYS.data()+envName);
		log.info(GetData.PROP_LOAD_SYS.data()+envUrl);	
		
		//opening the JDBC driver connections for different schema if have any
		/*
		schema1=dbCon.createConnection(prop.getProperty(envName+".schema1"));
		schema2=dbCon.createConnection(prop.getProperty(envName+".schema2"));
		*/
		log.info(GetData.JDBC_CONN_OPEN.data());
	}
	

	@BeforeTest
	@Parameters({"browser"})  
	public void beforeTest(String browser) throws Throwable {
		if(browser.length()<=0) {
			browser=prop.getProperty("default.browser");
		}
		browser=browser.toLowerCase();
		if(GetData.CHROME.data().equals(browser)) {
			log.info(GetData.CHROME.data()+" : "+GetData.PROP_LOAD_SYS.data());
			System.setProperty("webdriver.chrome.driver", "src\\main\\resource\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver(options);
		}
		else if(GetData.FIREFOX.data().equals(browser)) {
			log.info(GetData.FIREFOX.data()+" : "+GetData.PROP_LOAD_SYS.data());
			System.setProperty("webdriver.gecko.driver","src\\main\\resource\\geckodriver.exe" );
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver=new FirefoxDriver(options);
		}
		else if(GetData.INTERNETEXPLORER.data().equals(browser)) {
			log.info(GetData.INTERNETEXPLORER.data()+" : "+GetData.PROP_LOAD_SYS.data());
			System.setProperty("webdriver.ie.driver", "src\\main\\resource\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(GetData.EDGE.data().equals(browser)) {
			log.info(GetData.EDGE.data()+" : "+GetData.PROP_LOAD_SYS.data());
			System.setProperty("webdriver.edge.driver","src\\main\\resource\\msedgedriver.exe" ); 
			driver=new EdgeDriver();
		}
		else {
			log.info(GetData.INVALID_BROWSER.data());
			throw new Exception(String.format(GetData.INVALID_BROWSER.data()+", user input %s",browser));
		}
		
		 cap=((RemoteWebDriver) driver).getCapabilities();
		 log.info(GetData.BROWSER_INITIALIZATION_MESSAGE.data()+"Browser : "+cap.getBrowserName()+" || version : "+cap.getVersion()+" || OS : "+cap.getPlatform().toString());
		
		 	ComMethods.maximize();
			log.info("Browser Window got maximize");
			ComMethods.implicitWait();
			log.info("Implicit wait applied on driver");
		 
	}
	
	@AfterTest
	public void afterTest()  throws Exception {
		//driver.quit();
		log.info(GetData.BROWSER_SESSION_QUIT.data());
		
	}
	
	
	@AfterSuite
	public void afterSuite() throws SQLException {
		//dbCon.closeConnection(schema1);
		//dbCon.closeConnection(schema2);
		log.info(GetData.JDBC_CONN_CLOSE.data());
		//System.exit(0);
	}
		

}
