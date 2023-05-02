package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static core.base.Base.webDriver;
import static core.helpers.DataHelper.getRandNumber;
import static core.helpers.ElementsHelper.*;

public class CategoriesPage {

    public CategoriesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = ".list-group-item:not(:first-child)")
    private List<WebElement> allCategories;

    @FindBy(xpath = "//div//h4/a")
    private List<WebElement> allItems;

    public void clickOnCategories(int index) { //0-1-or 2
        webDriver.navigate().refresh();
        elementToBeClickable(this.allCategories.get(index)).click();
    }

    public int getListOfItems() {
        staticWait();
        return this.allItems.size();
    }

    public void clickOnAnyItem() {
        webDriver.navigate().refresh();
        staticWait();
        elementToBeClickable(this.allItems.get(getRandNumber(this.allItems.size()))).click();
    }
}
