package boxTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class boxTesting {
	private XSSFWorkbook workbook = new XSSFWorkbook();
	public static WebDriver driver;

	@BeforeMethod
	public void add() {
		driver = new ChromeDriver();
		driver.get("https://eop.edu.vn");
		driver.manage().window().maximize();

	}

	@Test
	public void testKQThiPass() {
		WebElement el1 = driver.findElement(By.linkText("Kết quả thi"));
		el1.click();
		WebElement el2 = driver.findElement(By.xpath("//*[@id=\"ketquathi\"]/div/div[1]/div[1]/input"));
		el2.sendKeys("2020603560");
		WebElement el3 = driver.findElement(By.xpath("//*[@id=\"ketquathi\"]/div/div[1]/div[2]/button"));
		el3.click();
	}

	@Test
	public void testKQThiFail() {
		WebElement el1 = driver.findElement(By.linkText("Kết quả thi"));
		el1.click();
		WebElement el2 = driver.findElement(By.xpath("//*[@id=\"ketquathi\"]/div/div[1]/div[1]/input"));
		el2.sendKeys("20206089090");
		WebElement el3 = driver.findElement(By.xpath("//*[@id=\"ketquathi\"]/div/div[1]/div[2]/button"));
		el3.click();
	}

	@Test
	public void testKHHTapPass() {
		Set<String> tableData = new HashSet<>();
		try {
			FileInputStream file = new FileInputStream("Search.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("MSV");
			List<String> msv = new ArrayList<>();

			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);

				if (cell1 != null) {
					cell1.setCellType(CellType.STRING);
					String username = cell1.getStringCellValue();
					msv.add(username);
				}
			}

			for (int i = 0; i < msv.size(); i++) {
				WebElement el2 = driver.findElement(By.xpath("//*[@id=\"kehoachhoctap\"]/div/div[1]/div[1]/input"));
				el2.sendKeys(msv.get(i));
				Thread.sleep(3000);
				WebElement el3 = driver.findElement(By.xpath("//*[@id=\"kehoachhoctap\"]/div/div[1]/div[2]/button"));
				el3.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				el2.clear();
				WebElement table = driver.findElement(By.xpath("//*[@id=\"kehoachhoctap\"]/div/div[3]/table"));
				String tableText = table.getText();
				tableData.add(tableText);
			}
			// Đóng file Excel
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		String fileName = "KHHTPass.txt";

		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			for (String data : tableData) {
				outputStream.write(data.getBytes());
				outputStream.write(System.getProperty("line.separator").getBytes());
				System.out.println("Successfully wrote to the file.");
			}

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// In ra dữ liệu trong bảng
		System.out.println(tableData);
	}

	@Test
	public void testKHHTapFail() {
		WebElement el2 = driver.findElement(By.xpath("//*[@id=\"kehoachhoctap\"]/div/div[1]/div[1]/input"));
		el2.sendKeys("");
		WebElement el3 = driver.findElement(By.xpath("//*[@id=\"kehoachhoctap\"]/div/div[1]/div[2]/button"));
		el3.click();
	}

	@Test
	public void testKHThiPass() {
		Set<String> tableData = new HashSet<>();
		try {
			FileInputStream file = new FileInputStream("Search.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("MSV-2");
			List<String> msv = new ArrayList<>();

			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);

				if (cell1 != null) {
					cell1.setCellType(CellType.STRING);
					String username = cell1.getStringCellValue();
					msv.add(username);
				}
			}

			for (int i = 0; i < msv.size(); i++) {
				WebElement el1 = driver
						.findElement(By.xpath("/html/body/section[1]/div/div/div/div[1]/div/ul/li[1]/a"));
				el1.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebElement el2 = driver.findElement(By.xpath("//*[@id=\"kehoachthi\"]/div/div[1]/div[1]/input"));
				el2.sendKeys(msv.get(i));
				Thread.sleep(1000);
				WebElement el3 = driver.findElement(By.xpath("//*[@id=\"kehoachthi\"]/div/div[1]/div[2]/button"));
				el3.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				el2.clear();
				WebElement table = driver.findElement(By.xpath("//*[@id=\"kehoachthi\"]/div/div[3]/table"));
				String tableText = table.getText();
				tableData.add(tableText);
			}
			// Đóng file Excel
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		String fileName = "KHThiPass.txt";

		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			for (String data : tableData) {
				outputStream.write(data.getBytes());
				outputStream.write(System.getProperty("line.separator").getBytes());
				System.out.println("Successfully wrote to the file.");
			}

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// In ra dữ liệu trong bảng
		System.out.println(tableData);

	}

	@Test
	public void testKHThiFail() {
		WebElement el1 = driver.findElement(By.linkText("Kế hoạch thi"));
		el1.click();
		WebElement el2 = driver.findElement(By.xpath("//*[@id=\"kehoachthi\"]/div/div[1]/div[1]/input"));
		el2.sendKeys("");
		WebElement el3 = driver.findElement(By.xpath("//*[@id=\"kehoachthi\"]/div/div[1]/div[2]/button"));
		el3.click();
	}

	@Test
	public void KQHTPass() {
		WebElement loginF = driver.findElement(By.xpath("//*[@id=\"page-top\"]/div/div[1]/a"));
		loginF.click();

		WebElement user = driver.findElement(By.id("input-username"));
		WebElement pass = driver.findElement(By.id("input-password"));
		WebElement btn = driver.findElement(By.id("login-btn"));
		user.sendKeys("2020603560");
		pass.sendKeys("202060356011379");
		btn.click();
		WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver2 -> driver2.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div/div/a"));
		tACNTT.click();
		WebElement foo2 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver2 -> driver2.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT2 = driver.findElement(By.xpath("//*[@id=\"courses\"]/div[1]/div[2]/div/div/a"));
		tACNTT2.click();
		WebElement foo3 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver2 -> driver2.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement tACNTT3 = driver.findElement(By.xpath("//*[@id=\"groups\"]/div/div/div/div/a"));
		tACNTT3.click();
		WebElement foo4 = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(driver2 -> driver2.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div/h2")));
		WebElement capcha = driver.findElement(By.xpath("//*[@id=\"txtcaptcha\"]"));
		capcha.sendKeys("A");
		WebElement capchaBTN = driver
				.findElement(By.xpath("//*[@id=\"wrapper\"]/div[2]/div/div[2]/div/div/div[1]/div/button"));
		capchaBTN.click();
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}
