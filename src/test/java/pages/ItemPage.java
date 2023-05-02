package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.helpers.ElementsHelper.elementToBeVisible;

public class ItemPage {

    public ItemPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//h2")
    private WebElement itemTitle;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    public void addToCart() {
        elementToBeVisible(this.addToCartButton).click();
    }

    public String getItemTitle() {
        elementToBeVisible(this.itemTitle);
        return this.itemTitle.getText();
    }

    public void goToCart() {
        this.cartButton.click();
    }
}
