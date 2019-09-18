package org.Pacificorp.Utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelHelper {

	private SetupClass objSetupClass;
	Sheet writerSheet;
	Workbook writerBook;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddHH-mm-ss");
	LocalDateTime now = LocalDateTime.now();
	int noOfRowsInTestOutputFile;
	int noOfColumnsInTestOutputFile;
	FileOutputStream fos;

	public ExcelHelper(SetupClass objSetupClass) {
		this.objSetupClass = objSetupClass;
	}

	public void createExcelSheetToWiteOutput() {
		writerBook = new XSSFWorkbook();
		writerSheet = writerBook.createSheet("Sheet1");
	}

	public void loadExcelSheetToWriteOutPut(String uiPropertyName) {
		getNoOfRowsInTestOutputFile();
		System.out.println(noOfRowsInTestOutputFile);
		writerSheet.createRow(noOfRowsInTestOutputFile).createCell(0, CellType.STRING).setCellValue("Property_Name");
		writerSheet.createRow(noOfRowsInTestOutputFile + 1).createCell(0, CellType.STRING).setCellValue(uiPropertyName);

	}

	public void getNoOfRowsInTestOutputFile() {
		noOfRowsInTestOutputFile = writerSheet.getPhysicalNumberOfRows();
	}

	public void writeUIPropertyNameAndItsDataInExcel(List<WebElement> list) {
		getNoOfRowsInTestOutputFile();
		System.out.println(noOfRowsInTestOutputFile);
		int cellNo = 1;
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Size of the list" + list.size());
			if (i == 0) {
				writerSheet.getRow(noOfRowsInTestOutputFile - 2).createCell(cellNo, CellType.STRING)
						.setCellValue("count");
				writerSheet.getRow(noOfRowsInTestOutputFile - 1).createCell(cellNo, CellType.STRING)
						.setCellValue(list.size() + 1);
				cellNo++;
			}
			if (i > 0) {
				writerSheet.getRow(noOfRowsInTestOutputFile - 2).createCell(cellNo, CellType.STRING)
						.setCellValue("Text" + i);
				writerSheet.getRow(noOfRowsInTestOutputFile - 1).createCell(cellNo, CellType.STRING)
						.setCellValue(list.get(i - 1).getText());
				cellNo++;
			}

		}
		try {
			fos = new FileOutputStream(
					System.getProperty("user.dir") + "\\" + "TestUIOutput" + dtf.format(now) + ".xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			writerBook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeTestDataOutputInExcel(String uiPropertyName) {

	}

}
