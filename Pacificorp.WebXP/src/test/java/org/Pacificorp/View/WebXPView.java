package org.Pacificorp.View;

import java.util.List;

import org.Pacificorp.Page.WebXPPage;
import org.Pacificorp.Utility.ExcelHelper;
import org.Pacificorp.Utility.SetupClass;
import org.openqa.selenium.WebElement;

public class WebXPView {

	SetupClass objSetupClass;
	WebXPPage objWebXPPage;
	ExcelHelper objExcelHelper;
	List<WebElement> listOfElements;

	public WebXPView(SetupClass objSetupClass) {
		this.objSetupClass = objSetupClass;
		this.objWebXPPage = new WebXPPage(objSetupClass);
		this.objExcelHelper = new ExcelHelper(objSetupClass);
	}

	public void captureFacebookPageButtons() {
		System.out.println("Facebook View" + this);
		listOfElements = objWebXPPage.captureButtons();
		objSetupClass.getObjExcelHelper().writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

	public void captureFacebookPageLinks() {
		listOfElements = objWebXPPage.captureLinks();
		objSetupClass.getObjExcelHelper().writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

}
