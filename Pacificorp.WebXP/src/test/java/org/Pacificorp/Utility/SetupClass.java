package org.Pacificorp.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupClass {

	WebDriver driver;
	ExcelHelper objExcelHelper;

	public void initialiseBrowser() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		options.merge(capabilities);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		objExcelHelper = new ExcelHelper(this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void openURL() {
		driver.get("https://www.facebook.com/");
	}

	public static void main(String[] args) {
		new SetupClass().initialiseBrowser();
	}

	public ExcelHelper getObjExcelHelper() {
		return objExcelHelper;
	}
}
