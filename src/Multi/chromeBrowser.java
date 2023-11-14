package Multi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class chromeBrowser {
	public static WebDriver driver;
	
	@BeforeMethod
	public void add() {
		driver = new ChromeDriver();
		driver.get("https://eop.edu.vn/login");
		driver.manage().window().maximize();
	}
	
	@Test
	public void chromeBrowser() {
		WebElement user = driver.findElement(By.id("input-username"));
		WebElement pass = driver.findElement(By.id("input-password"));
		WebElement btn = driver.findElement(By.id("login-btn"));
		user.sendKeys("2020607579");
		pass.sendKeys("Anh02@");
		btn.click();
		
		WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div/div/a"));
		tACNTT.click();
		
		WebElement foo2 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT2 = driver.findElement(By.xpath("//*[@id=\"courses\"]/div[3]/div[2]/div/div/a"));
		tACNTT2.click();
		
		WebElement foo3 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT3 = driver.findElement(By.xpath("//*[@id=\"groups\"]/div/div/div/div/a"));
		tACNTT3.click();
		
		WebElement foo4 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement unit2 = driver.findElement(By.xpath("//*[@id=\"units\"]/div[1]/div[2]/div/div/a"));
		unit2.click();
		
		WebElement foo5 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement grammar = driver.findElement(By.xpath("//*[@id=\"dtypes\"]/div[2]"));
		grammar.click();
		
		WebElement foo6 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement pr2 = driver.findElement(By.xpath("//*[@id=\"tpgrammar\"]/a[2]"));
		pr2.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement done = driver.findElement(By.xpath("//*[@id=\"submit4a6e47d3\"]"));
		done.click();
		
		try { Thread.sleep(10000); } 
		catch (InterruptedException e) {e.printStackTrace(); }
		
		WebElement home = driver.findElement(By.xpath("//*[@id=\"dcoursetitle\"]"));
		home.click();
		
		WebElement logoLogout = driver.findElement(By.xpath("//*[@id=\"duinfo\"]/a"));
		logoLogout.click();
		
		WebElement logout = driver.findElement(By.xpath("//*[@id=\"duinfo\"]/ul/li[6]/a"));
		logout.click();
		
		try {
		    Thread.sleep(3000); // Dừng thực thi trong 5 giây
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		driver.close();
	}	
}
