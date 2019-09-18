package org.Pacificorp.Test;

import org.Pacificorp.Utility.SetupClass;
import org.Pacificorp.View.FacebookView;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FacebookTest extends SetupClass {

	FacebookView objFacebookView;

	public void initialiseViewsAndPages() {
		this.objFacebookView = new FacebookView(this);
	}

	@BeforeClass
	public void BeforeClass() {
		initialiseViewsAndPages();
		this.initialiseBrowser();
		this.openURL();
		this.getObjExcelHelper().createExcelSheetToWiteOutput();
	}

	@Test
	public void captureButtonTextAndCount() {
		this.getObjExcelHelper().loadExcelSheetToWriteOutPut("button");
		objFacebookView.captureFacebookPageButtons();
		this.getObjExcelHelper().loadExcelSheetToWriteOutPut("links");
		objFacebookView.captureFacebookPageLinks();
	}

	@AfterClass
	public void closeBrowser() {
		this.getDriver().close();
	}

}
