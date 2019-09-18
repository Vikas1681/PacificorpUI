package org.Pacificorp.View;

import java.util.List;

import org.Pacificorp.Page.FacebookPage;
import org.Pacificorp.Utility.ExcelHelper;
import org.Pacificorp.Utility.SetupClass;
import org.openqa.selenium.WebElement;

public class FacebookView {

	SetupClass objSetupClass;
	FacebookPage objFacebookPage;
	ExcelHelper objExcelHelper;
	List<WebElement> listOfElements;

	public FacebookView(SetupClass objSetupClass) {
		this.objSetupClass = objSetupClass;
		this.objFacebookPage = new FacebookPage(objSetupClass);
		this.objExcelHelper = new ExcelHelper(objSetupClass);
	}

	public void captureFacebookPageButtons() {
		System.out.println("Facebook View" + this);
		listOfElements = objFacebookPage.captureButtons();
		objSetupClass.getObjExcelHelper().writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

	public void captureFacebookPageLinks() {
		listOfElements = objFacebookPage.captureLinks();
		objSetupClass.getObjExcelHelper().writeUIPropertyNameAndItsDataInExcel(listOfElements);
	}

}
