package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class AutomationJavaTest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "c:/browser-drivers/geckodriver.exe");

		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		driver = new FirefoxDriver(options);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void sauceLabsTest() throws InterruptedException {
		driver.get("http://saucelabs.com");

		driver.findElement(By.className("_16Ez")).click();

		Thread.sleep(1000);

		driver.findElement(By.linkText("Cross Browser Testing")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"react_zQvZpY1NHE6rBiBWxlpzg\"]/div/div[2]/div/p[2]/a")).click();

		Thread.sleep(1000);

		String message = driver.findElement(By.id("react_eB1hcwfaUk2CT7CirxdH9g")).getText();

		assertEquals(message, "Run Selenium tests securely in the cloud");
	}

	@Test
	public void githubTest() throws InterruptedException {
		driver.get("http://github.com");

		WebElement searchInput = driver.findElement(By.className("header-search-input"));
		searchInput.sendKeys("selenium");
		searchInput.sendKeys(Keys.RETURN);

		Thread.sleep(1000);

		driver.findElement(By.linkText("SeleniumHQ/selenium")).click();

		List<WebElement> rows = driver.findElements(By.cssSelector(".files .js-navigation-item"));

		WebElement oldestRow = rows.get(0);
		String lastTime = "";
		String currentTime = "";

		for (WebElement row : rows) {

			lastTime = oldestRow.findElement(By.cssSelector("time-ago")).getAttribute("datetime");
			currentTime = row.findElement(By.cssSelector("time-ago")).getAttribute("datetime");

			if (currentTime.compareTo(lastTime) < 0) {
				oldestRow = row;
			}
		}

		System.out.println("Oldest file or dir: " + oldestRow.findElement(By.className("content")).getText());
	}

	@Test
	public void extentreportsTest() throws InterruptedException {

		Actions action = new Actions(driver);

		driver.get("http://extentreports.com");

		action.moveToElement(driver.findElement(By.id("menu-item-1534"))).build().perform();

		Thread.sleep(1000);

		action.moveToElement(driver.findElement(By.id("menu-item-1535"))).build().perform();

		Thread.sleep(1000);

		action.moveToElement(driver.findElement(By.id("menu-item-1538"))).click().build().perform();

		Thread.sleep(1000);

		List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

		String feature = "";
		Boolean offlineSupportInPro = false;

		for (WebElement row : rows) {

			feature = row.findElement(By.cssSelector("td:first-child")).getText();

			if (feature.equals("Offline report")) {
				offlineSupportInPro = row.findElement(By.cssSelector("td:last-child i")).getAttribute("class")
						.contains("fa-check");
			}
		}

		System.out.println("Pro has offline support: " + offlineSupportInPro);
	}

}
