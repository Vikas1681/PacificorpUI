package org.Pacificorp.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomationUIPOC {

	private WebDriver driver;
	private Sheet writerSheet;
	private Workbook writerBook;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddHH-mm-ss");
	private LocalDateTime now = LocalDateTime.now();
	private int noOfRowsInTestOutputFile;
	private int noOfColumnsInTestOutputFile;
	private FileOutputStream fos;
	private List<WebElement> lnkListWithText;
	private List<WebElement> btnList;
	private int lnkListWithTextCount;
	private int btnCount;
	private List<WebElement> listOfElements;
	private Logger log;

	// -------------------Test Layer----------------
	@BeforeClass
	public void beforeClassSetup() {
		this.initialseLogger();
		this.createExcelSheetToWiteOutput();
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		this.getLogger().info("Chrome Browser Stared");
		driver.manage().window().maximize();
		this.getLogger().info("Chrome Browser Maximized");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://csappstest.pacificpower.net");
		this.getLogger().info("Launching WebXP Application");
	}

	@Test
	public void captureButtonTextAndCount() {
		this.loadExcelSheetToWriteOutPut("button");
		this.captureFacebookPageButtons();
		this.loadExcelSheetToWriteOutPut("links");
		this.captureFacebookPageLinks();
	}

	@AfterClass
	public void closeBrowser() {
		this.getDriver().close();
		this.getLogger().info("closing Chrome Browser");
	}

	// ---------Base Layer----------------
	public WebDriver getDriver() {
		return driver;
	}

	public void initialseLogger() {
		log = Logger.getLogger(AutomationUIPOC.class);
	}

	public Logger getLogger() {
		return log;
	}

	// ----------Logic To Capture UI Characteristics
	public List<WebElement> captureButtons() {
		this.getLogger().info("capturing buttons and storing them in list");
		return btnList = this.getDriver().findElements(By.xpath("//button"));

	}

	public int getButtonCount() {
		return btnCount = btnList.size();
	}

	public List<WebElement> captureLinks() {
		this.getLogger().info("capturing links and storing them in list");
		List<WebElement> lnkList = this.getDriver().findElements(By.xpath("//a"));
		lnkListWithText = new ArrayList();
		for (WebElement webElement : lnkList) {
			if (webElement.getText().length() > 0) {
				lnkListWithText.add(webElement);
			}

		}
		return lnkListWithText;
	}

	public int getLinksCount() {
		return lnkListWithTextCount = lnkListWithText.size();
	}

	public void captureFacebookPageButtons() {
		listOfElements = this.captureButtons();
		writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

	public void captureFacebookPageLinks() {
		listOfElements = captureLinks();
		writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

	// -------Logic Layer-----------------
	public void createExcelSheetToWiteOutput() {
		writerBook = new XSSFWorkbook();
		writerSheet = writerBook.createSheet("Sheet1");
		this.getLogger().info("creating sheet to write the test findings");
	}

	public void loadExcelSheetToWriteOutPut(String uiPropertyName) {
		getNoOfRowsInTestOutputFile();
		this.getLogger().info("Creating rows and columns to write the test findings");
		writerSheet.createRow(noOfRowsInTestOutputFile).createCell(0, CellType.STRING).setCellValue("Property_Name");
		writerSheet.createRow(noOfRowsInTestOutputFile + 1).createCell(0, CellType.STRING).setCellValue(uiPropertyName);
		writerSheet.createRow(noOfRowsInTestOutputFile + 2).createCell(0, CellType.STRING)
				.setCellValue(uiPropertyName + "_" + "co-ordinates");

	}

	public void getNoOfRowsInTestOutputFile() {
		noOfRowsInTestOutputFile = writerSheet.getPhysicalNumberOfRows();
		this.getLogger().info("No of rows present in the sheet:-" + noOfRowsInTestOutputFile);
	}

	public void writeUIPropertyNameAndItsDataInExcel(List<WebElement> list) {
		getNoOfRowsInTestOutputFile();
		int cellNo = 1;
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				writerSheet.getRow(noOfRowsInTestOutputFile - 3).createCell(cellNo, CellType.STRING)
						.setCellValue("count");
				writerSheet.getRow(noOfRowsInTestOutputFile - 2).createCell(cellNo, CellType.STRING)
						.setCellValue(list.size());
				cellNo++;
				this.getLogger().info("writing following Test Findings:-" + "count" + list.size());
			}
			if (i > 0) {
				writerSheet.getRow(noOfRowsInTestOutputFile - 3).createCell(cellNo, CellType.STRING)
						.setCellValue("Text" + i);
				writerSheet.getRow(noOfRowsInTestOutputFile - 2).createCell(cellNo, CellType.STRING)
						.setCellValue(list.get(i - 1).getText());
				writerSheet.getRow(noOfRowsInTestOutputFile - 1).createCell(cellNo, CellType.STRING).setCellValue(
						list.get(i - 1).getLocation().getX() + "," + list.get(i - 1).getLocation().getY());
				cellNo++;
				this.getLogger()
						.info("writing following Test Findings:-" + "Text" + i + " " + "Value:-"
								+ list.get(i - 1).getText() + " " + "Location:-" + list.get(i - 1).getLocation().getX()
								+ "," + list.get(i - 1).getLocation().getY());
			}

		}
		try {
			fos = new FileOutputStream(System.getProperty("user.dir") + "\\" + "test-output" + "\\" + "TestUIOutput"
					+ dtf.format(now) + ".xlsx");
			this.getLogger().info("Test Output is saved at " + System.getProperty("user.dir") + "\\" + "test-output"
					+ "\\" + "TestUIOutput" + dtf.format(now) + ".xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			writerBook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
