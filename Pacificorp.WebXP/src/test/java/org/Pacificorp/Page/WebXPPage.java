package org.Pacificorp.Page;

import java.util.ArrayList;
import java.util.List;

import org.Pacificorp.Utility.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebXPPage {

	private SetupClass objSetupClass;
	private List<WebElement> lnkListWithText;
	private List<WebElement> btnList;
	private int lnkListWithTextCount;
	private int btnCount;

	public WebXPPage(SetupClass objSetupClass) {
		this.objSetupClass = objSetupClass;
	}

	public List<WebElement> captureButtons() {
		return btnList = objSetupClass.getDriver().findElements(By.xpath("//button"));

	}

	public int getButtonCount() {
		return btnCount = btnList.size();
	}

	public List<WebElement> captureLinks() {
		List<WebElement> lnkList = objSetupClass.getDriver().findElements(By.xpath("//a"));
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

}
