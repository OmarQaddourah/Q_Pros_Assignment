package core.base;

import com.utils.PropReader;
import drivers_initializer.drivers.SelInstance;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

import static drivers_initializer.drivers.SelInstance.getWebDriver;

public class Base {

    public static WebDriver webDriver;
    public static HashMap<String, Object> globalMap = new HashMap<>();

    @BeforeMethod()
    public void setUp() {
        webDriver = getWebDriver();
        webDriver.manage().window().maximize();
        webDriver.get(PropReader.readConfig("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            webDriver.quit();
        } finally {
            SelInstance.webDriver.set(null);
        }
    }
}
