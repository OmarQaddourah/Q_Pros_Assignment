package core.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.base.Base.webDriver;

public class ElementsHelper {

    public static final int TIME_OUT_IN_SECONDS = 60;
    private static WebDriverWait wait;

    public static WebElement elementToBeVisible(WebElement webElement) {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement elementToBeClickable(WebElement webElement) {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void switchToAlert() {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert();
    }

    public static void acceptAlert() {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert().accept();
        webDriver.switchTo().defaultContent();
    }

    public static String getAlertText() {
        return webDriver.switchTo().alert().getText();
    }

    public static void staticWait() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
