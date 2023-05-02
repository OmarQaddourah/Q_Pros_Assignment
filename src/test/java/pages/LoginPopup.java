package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.helpers.ElementsHelper.elementToBeVisible;

public class LoginPopup {

    public LoginPopup(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    public void login(String username, String password) {
        elementToBeVisible(this.usernameField).sendKeys(username);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }
}
