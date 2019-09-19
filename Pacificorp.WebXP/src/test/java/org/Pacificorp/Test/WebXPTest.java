package org.Pacificorp.Test;

import org.Pacificorp.Utility.SetupClass;
import org.Pacificorp.View.WebXPView;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebXPTest extends SetupClass {

	WebXPView objWebXPView;

	public void initialiseViewsAndPages() {
		this.objWebXPView = new WebXPView(this);
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
		objWebXPView.captureFacebookPageButtons();
		this.getObjExcelHelper().loadExcelSheetToWriteOutPut("links");
		objWebXPView.captureFacebookPageLinks();
	}

	@AfterClass
	public void closeBrowser() {
		this.getDriver().close();
	}

}
