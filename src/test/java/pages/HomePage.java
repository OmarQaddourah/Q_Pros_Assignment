package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.helpers.ElementsHelper.elementToBeVisible;

public class HomePage {

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signupButton;

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement loginButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeUser;

    public void reachSignPopup(String registration) {
        switch (registration) {
            case "sign-up" -> elementToBeVisible(this.signupButton).click();
            case "log-in" -> elementToBeVisible(this.loginButton).click();
        }
    }

    public WebElement getWelcomeUser() {
        return elementToBeVisible(this.welcomeUser);
    }
}
