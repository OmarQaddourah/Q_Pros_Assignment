package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.helpers.DataHelper.*;
import static core.helpers.ElementsHelper.elementToBeVisible;
import static core.helpers.XmlDataWriter.writeNode;

public class SignupPopup {

    public SignupPopup(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "sign-username")
    private WebElement usernameField;

    @FindBy(id = "sign-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signupButton;

    public void signup() {
        String index = String.valueOf(getRandNumber(9999));
        setGlobalValue("Latest Signup", index);
        String username = getTestData("username") + getRandString(5);
        String password = getRandString(7) + String.valueOf(getRandNumber(3));
        writeNode("EmailsAndPasswords", "username" + index, username);
        writeNode("EmailsAndPasswords", "password" + index, password);

        elementToBeVisible(this.usernameField).sendKeys(username);
        this.passwordField.sendKeys(password);
        this.signupButton.click();
    }
}
