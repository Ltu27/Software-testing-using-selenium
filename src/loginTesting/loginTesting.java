package loginTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import org.openqa.selenium.devtools.v110.memory.Memory.GetDOMCountersResponse;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTesting {
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private static WebDriver driver;

	@BeforeMethod
	public void add() {
		driver = new ChromeDriver();
		driver.get("https://eop.edu.vn/login");
	}

	@Test
	public void loginPass() {
		workbook.createSheet("LoginPass");

		try {
			FileInputStream file = new FileInputStream("Login.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("UseLogin");
			List<String> usernames = new ArrayList<>();
			List<String> passwords = new ArrayList<>();

			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				if (cell1 != null && cell2 != null) {
					cell1.setCellType(CellType.STRING);
					cell2.setCellType(CellType.STRING);
					String username = cell1.getStringCellValue();
					String password = cell2.getStringCellValue();
					usernames.add(username);
					passwords.add(password);
				}
			}
			for (int i = 0; i < usernames.size(); i++) {
				WebElement user = driver.findElement(By.id("input-username"));
				WebElement pass = driver.findElement(By.id("input-password"));
				WebElement btn = driver.findElement(By.id("login-btn"));
				user.sendKeys(usernames.get(i));
//				Thread.sleep(1000);
				pass.sendKeys(passwords.get(i));
//				Thread.sleep(1000);
				btn.click();
				WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
						.until(driver -> driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
				driver.manage().deleteCookieNamed("duser");
				driver.manage().deleteCookieNamed("dgtk");
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			}
			// Đóng file Excel
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		// Ghi các giá trị cookie vào tệp Excel

	}

	@Test
	public void loginNull() {
		WebElement user = driver.findElement(By.id("input-username"));
		WebElement pass = driver.findElement(By.id("input-password"));
		WebElement btn = driver.findElement(By.id("login-btn"));
		btn.click();

	}

	@Test
	public void loginUsernameNull() {
		try {
			FileInputStream file = new FileInputStream("Login.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("LoginUserNameNull");
			List<String> usernames = new ArrayList<>();
			List<String> passwords = new ArrayList<>();
			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				if (cell1 != null && cell2 != null) {
					cell1.setCellType(CellType.STRING);
					cell2.setCellType(CellType.STRING);
					String username = cell1.getStringCellValue();
					String password = cell2.getStringCellValue();
					usernames.add(username);
					passwords.add(password);
				}
			}
			for (int i = 0; i < usernames.size(); i++) {
				WebElement user = driver.findElement(By.id("input-username"));
				WebElement pass = driver.findElement(By.id("input-password"));
				WebElement btn = driver.findElement(By.id("login-btn"));
				user.sendKeys("");
				Thread.sleep(1000);
				pass.sendKeys(passwords.get(i));
				Thread.sleep(1000);
				btn.click();
				user.clear();
			}
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void loginFail() {
		WebElement user = driver.findElement(By.id("input-username"));
		WebElement pass = driver.findElement(By.id("input-password"));
		WebElement btn = driver.findElement(By.id("login-btn"));

		try {
			FileInputStream file = new FileInputStream("Login.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Fails");
			List<String> usernames = new ArrayList<>();
			List<String> passwords = new ArrayList<>();
			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				if (cell1 != null && cell2 != null) {
					cell1.setCellType(CellType.STRING);
					cell2.setCellType(CellType.STRING);
					String username = cell1.getStringCellValue();
					String password = cell2.getStringCellValue();
					usernames.add(username);
					passwords.add(password);
				}
			}
			for (int i = 0; i < usernames.size(); i++) {
				user.sendKeys(usernames.get(i));
				pass.sendKeys(passwords.get(i));
				btn.click();
//				driver.navigate().refresh();
			}

			// Đóng file Excel
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@AfterMethod
	public void close() {
		driver.close();
	}
}
