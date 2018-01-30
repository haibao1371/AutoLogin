package com.haibao.AutoDemo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author 海宝20180120 用来将常用的浏览器都进行封装，以后想用哪个，就直接拿出来哪个浏览器， 非常的方便、快捷，完全符合JAVA的工程化思想
 */
public class Browsers {
	public WebDriver driver = null;
	private DesiredCapabilities caps = null;
	private FirefoxProfile firefoxProfile = null;
	// private String productPath = "D:\\workspaceone\\selenium";//设置工程路径，请记住是\\
	private String productPath = System.getProperty("user.dir");
	private BrowsersType browserType;

	public Browsers(BrowsersType browserType) {
		switch (browserType) {
		case firefox:
			/*	*//**
					 * 火狐不是默认安装在C盘的时候，必须设置其Bin
					 *//*
						 * System.setProperty("webdriver.firefox.bin", "D:\\");
						 */
			File fireBug = new File(productPath + "/tool/firebug.xpi");
			File firePath = new File(productPath + "/tool/firepath.xpi");
			firefoxProfile = new FirefoxProfile();
			try {
				firefoxProfile.addExtension(fireBug);
				firefoxProfile.addExtension(firePath);
				firefoxProfile.setPreference("webdriver.accept.untrusted.certs", "true");// 设置证书
				firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.12.1");// 设置firebug
				firefoxProfile.setPreference("network.proxy.type", 0);// 设置代理
				firefoxProfile.setPreference("network.proxy.http", "10.1.1.0");
				firefoxProfile.setPreference("network.proxy.http_port", 3328);
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new FirefoxDriver(firefoxProfile);
			break;
		case chrome:
			System.setProperty("webdriver.chrome.driver", productPath + "/tools/chromedriver.exe");
			// 初始化caps
			caps = DesiredCapabilities.chrome();
			caps.setCapability("chrome.switches", Arrays.asList("--start-maximized"));// 最大化browser
			caps.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://your-proxy-domain:4443"));// 设置代理
			driver = new ChromeDriver(caps);
			driver.manage().window().maximize();
			break;
		case ie:
			System.setProperty("webdriver.ie.driver", productPath + "/files/IEDriverServer64.exe");
			// 初始化IE浏览器
			caps = DesiredCapabilities.internetExplorer();
			// 启动浏览器并关闭保护模式
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// 在InPrivate模式下启动IE浏览器
			caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			driver.manage().window().maximize();
			break;
		}
	}
}
