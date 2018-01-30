package com.haibao.AutoDemo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LanuchOnBaidu {
	private WebDriver chromWb = null;

	@BeforeClass
	public void startChrome() {
		Browsers browser = new Browsers(BrowsersType.chrome);
		chromWb = browser.driver;
	}

	@Test
	public void loginOnBaidu() {
		chromWb.get("http://www.baidu.com");
		chromWb.manage().window().maximize();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void release() {
		chromWb.quit();
	}
}
