package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.base.Base.webDriver;
import static core.helpers.ElementsHelper.elementToBeVisible;
import static core.helpers.ElementsHelper.staticWait;

public class CartPage {

    public CartPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//td/img")
    private WebElement itemImage;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(css = ".sweet-alert h2")
    private WebElement successMessage;

    public WebElement getItemTitle(String itemName) {
        elementToBeVisible(this.itemImage);
        staticWait();
        return webDriver.findElement(By.xpath("//td[text()='" + itemName + "']"));
    }

    public void clickOnDeleteButton(String itemName) {
        elementToBeVisible(this.itemImage);
        webDriver.findElement(By.xpath("//td[text()='" + itemName + "']//parent::td//following-sibling::td[2]/a")).click();
    }

    public void clickOnPlaceButton() {
        elementToBeVisible(this.itemImage);
        this.placeButton.click();
    }

    public void fillCheckoutForm() {
        elementToBeVisible(this.nameField).sendKeys("Automation user");
        this.countryField.sendKeys("Jordan");
        this.cityField.sendKeys("Amman");
        this.cardField.sendKeys("4444333300001111");
        this.monthField.sendKeys("12");
        this.yearField.sendKeys("2028");
    }

    public void clickOnPurchaseButton() {
        this.purchaseButton.click();
    }

    public WebElement getSuccessMessage() {
        return elementToBeVisible(this.successMessage);
    }
}
