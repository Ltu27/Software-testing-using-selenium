package cookieTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class cookieTesting {

	private XSSFWorkbook workbook = new XSSFWorkbook();
	private static WebDriver driver;
	
	@BeforeMethod
	public void add() {
		driver = new ChromeDriver();
		driver.get("https://eop.edu.vn/login");
		WebElement user = driver.findElement(By.id("input-username"));
		WebElement pass = driver.findElement(By.id("input-password"));
		WebElement btn = driver.findElement(By.id("login-btn"));
		user.sendKeys("2020600385");
		pass.sendKeys("140902Bi@");
		btn.click();
	}

	@Test
	public void addCookie() {
		driver.navigate().refresh();
		Cookie user = new Cookie.Builder("user", "user_value").isSecure(true).isHttpOnly(true).build();
		driver.manage().addCookie(user);
		driver.navigate().refresh();
		Set<Cookie> cookie = driver.manage().getCookies();
		for (Cookie get : cookie) {
			System.out.println(get);
		}
////		XSSFWorkbook workbook = new XSSFWorkbook();
//        // Tạo một trang tính trong tệp Excel
		workbook.createSheet("Cookies");
//        // Ghi các giá trị cookie vào tệp Excel
		try {
			FileOutputStream outputStream = new FileOutputStream("Output2.xlsx");
			Row row;
			Cell cellCookieName;
			Cell cellCookieValue;
			int rowNum = 0;
			for (Cookie get : cookie) {
				row = workbook.getSheet("Cookies").createRow(rowNum++);
				cellCookieName = row.createCell(0);
				cellCookieValue = row.createCell(1);
				cellCookieName.setCellValue(get.getName());
				cellCookieValue.setCellValue(get.getValue());
			}
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Cookie values have been written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(user.isSecure());

	}

	@Test
	public void secureCheck() {
		driver.navigate().refresh();
		Cookie user = new Cookie.Builder("user", "user_value").isSecure(true).isHttpOnly(true).build();
		driver.manage().addCookie(user);
		driver.navigate().refresh();
		Set<Cookie> cookie = driver.manage().getCookies();
		for (Cookie get : cookie) {
			if (get.isSecure()) {
				System.out.println("Secure: " + get.getName());
			} else {
				System.out.println("Not Secure: " + get.getName());
			}
//			Assert.assertTrue(get.isSecure());
		}

		workbook.createSheet("Secure");

		// Ghi các giá trị cookie vào tệp Excel
		try {
			FileOutputStream outputStream = new FileOutputStream("Output.xlsx");
			Row row;
			Cell cellCookieName;
			Cell cellCookieValue;
			int rowNum = 0;
			for (Cookie get : cookie) {
				row = workbook.getSheet("Secure").createRow(rowNum++);
				cellCookieName = row.createCell(0);
				cellCookieValue = row.createCell(1);
				cellCookieName.setCellValue(get.getName());
//              cellCookieValue.setCellValue(get.getValue());
				if (get.isSecure()) {
					cellCookieValue.setCellValue("Secure");
				} else {
					cellCookieValue.setCellValue("Not Secure");
				}
			}
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Cookie values have been written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteCookie() {
		driver.navigate().refresh();
		driver.manage().deleteCookieNamed("duser");
		driver.navigate().refresh();
		Set<Cookie> cookie = driver.manage().getCookies();
		workbook.createSheet("Delete");
//      // Ghi các giá trị cookie vào tệp Excel
		try {
			FileOutputStream outputStream = new FileOutputStream("Output.xlsx");
			Row row;
			Cell cellCookieName;
			Cell cellCookieValue;
			int rowNum = 0;
			for (Cookie get : cookie) {
				row = workbook.getSheet("Delete").createRow(rowNum++);
				cellCookieName = row.createCell(0);
				cellCookieValue = row.createCell(1);
				cellCookieName.setCellValue(get.getName());
				cellCookieValue.setCellValue(get.getValue());
			}
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Cookie values have been written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			}

	}

	@Test
	public void cookieLifeTime() {
		driver.navigate().refresh();
		Set<Cookie> cookie = driver.manage().getCookies();
		workbook.createSheet("CookieLifeTime");
		try {
			FileOutputStream outputStream = new FileOutputStream("Output.xlsx");
			Row row;
			Cell cellCookieName;
			Cell cellCookieTime;
			int rowNum = 0;
			for (Cookie cookie2 : cookie) {
				row = workbook.getSheet("CookieLifeTime").createRow(rowNum++);
				cellCookieName = row.createCell(0);
				cellCookieTime = row.createCell(1);
				cellCookieName.setCellValue(cookie2.getName());
				if (cookie2 != null && cookie2.getExpiry() != null) {
					if (cookie2.getExpiry().before(new Date())) {
						cellCookieTime.setCellValue(cookie2.getExpiry() + " Cookie has expired");

					} else {
						cellCookieTime.setCellValue(cookie2.getExpiry() + " Cookie is valid");
					}
				} else {
					System.out.println("Cookie does not exist or has no expiry date");
				}
			}
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Cookie values have been written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void useCookie() {
//		driver.navigate().refresh();
		Set<Cookie> cookie = driver.manage().getCookies();
		for (Cookie get : cookie) {
			System.out.println(get);
		}
////		XSSFWorkbook workbook = new XSSFWorkbook();
//        // Tạo một trang tính trong tệp Excel
		workbook.createSheet("Use");
//        // Ghi các giá trị cookie vào tệp Excel
		try {
			FileOutputStream outputStream = new FileOutputStream("Output.xlsx");
			Row row;
			Cell cellCookieName;
			Cell cellCookieValue;
			int rowNum = 0;
			for (Cookie get : cookie) {
				row = workbook.getSheet("Use").createRow(rowNum++);
				cellCookieName = row.createCell(0);
				cellCookieValue = row.createCell(1);
				cellCookieName.setCellValue(get.getName());
				cellCookieValue.setCellValue(get.getValue());
			}
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Cookie values have been written to Excel file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.manage().deleteCookieNamed("duser");
		driver.manage().deleteCookieNamed("dgtk");
		driver.navigate().refresh();

		try {
			FileInputStream file = new FileInputStream("Output.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Use");
			List<String> names = new ArrayList<>();
			List<String> values = new ArrayList<>();
			for (Row row : sheet) {
				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				if (cell1 != null && cell2 != null) {
					String value1 = cell1.getStringCellValue();
					String value2 = cell2.getStringCellValue();
					names.add(value1);
					values.add(value2);
				}
			}
			for (int i = 0; i < names.size(); i++) {
				if (names.get(i).equalsIgnoreCase("duser") || names.get(i).equalsIgnoreCase("dgtk")) {
					Cookie cookie4 = new Cookie(names.get(i), values.get(i));
					driver.manage().addCookie(cookie4);
				}
			}
			driver.navigate().refresh();

			// Đóng file Excel
			workbook.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
