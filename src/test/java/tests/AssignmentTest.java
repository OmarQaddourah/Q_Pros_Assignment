package tests;

import core.base.Base;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static core.helpers.DataHelper.*;
import static core.helpers.ElementsHelper.*;

@Listeners(com.utils.ExtentReport.TestListener.class)
public class AssignmentTest extends Base {

    private HomePage homePage;
    private SignupPopup signupPopup;
    private LoginPopup loginPopup;
    private CategoriesPage categoriesPage;
    private ItemPage itemPage;
    private CartPage cartPage;
    private SoftAssert softAssert;

    @Test(description = "Verify signup successfully", priority = 1)
    public void signup() {
        homePage = new HomePage(webDriver);
        signupPopup = new SignupPopup(webDriver);

        homePage.reachSignPopup("sign-up");
        signupPopup.signup();
        switchToAlert();

        Assert.assertTrue(getAlertText().contains(getTestData("signupMessage")));
    }

    @Test(description = "Verify login successfully", priority = 2)
    public void login() {
        homePage = new HomePage(webDriver);
        loginPopup = new LoginPopup(webDriver);

        homePage.reachSignPopup("log-in");
        setGlobalValue("Latest username", getTestData("username" + getGlobalValue("Latest Signup")));
        setGlobalValue("Latest password", getTestData("password" + getGlobalValue("Latest Signup")));
        loginPopup.login(getGlobalValue("Latest username").toString(), getGlobalValue("Latest password").toString());

        Assert.assertTrue(homePage.getWelcomeUser().isDisplayed());
    }

    @Test(description = "Verify the listed Categories has Items", priority = 3)
    public void checkCategoriesItems() {
        homePage = new HomePage(webDriver);
        loginPopup = new LoginPopup(webDriver);
        categoriesPage = new CategoriesPage(webDriver);
        softAssert = new SoftAssert();

        homePage.reachSignPopup("log-in");
        loginPopup.login(getGlobalValue("Latest username").toString(), getGlobalValue("Latest password").toString());
        categoriesPage.clickOnCategories(0);
        softAssert.assertTrue(categoriesPage.getListOfItems() > 0);

        categoriesPage.clickOnCategories(1);
        softAssert.assertTrue(categoriesPage.getListOfItems() > 0);

        categoriesPage.clickOnCategories(2);
        softAssert.assertTrue(categoriesPage.getListOfItems() > 0);
        softAssert.assertAll();
    }

    @Test(description = "Verify add a random item to the cart", priority = 4)
    public void addItemToCart() {
        homePage = new HomePage(webDriver);
        loginPopup = new LoginPopup(webDriver);
        categoriesPage = new CategoriesPage(webDriver);
        itemPage = new ItemPage(webDriver);
        cartPage = new CartPage(webDriver);
        softAssert = new SoftAssert();

        homePage.reachSignPopup("log-in");
        loginPopup.login(getGlobalValue("Latest username").toString(), getGlobalValue("Latest password").toString());
        categoriesPage.clickOnAnyItem();
        String itemName = itemPage.getItemTitle();
        itemPage.addToCart();
        switchToAlert();
        softAssert.assertTrue(getAlertText().contains(getTestData("addItem")));
        acceptAlert();
        itemPage.goToCart();

        softAssert.assertEquals(cartPage.getItemTitle(itemName).getText(), itemName);
        softAssert.assertAll();
    }

    @Test(description = "Verify remove item from the cart", priority = 5)
    public void removeItemFromCart() {
        homePage = new HomePage(webDriver);
        loginPopup = new LoginPopup(webDriver);
        categoriesPage = new CategoriesPage(webDriver);
        itemPage = new ItemPage(webDriver);
        cartPage = new CartPage(webDriver);

        homePage.reachSignPopup("log-in");
        loginPopup.login(getGlobalValue("Latest username").toString(), getGlobalValue("Latest password").toString());
        categoriesPage.clickOnAnyItem();
        String itemName = itemPage.getItemTitle();
        itemPage.addToCart();
        switchToAlert();
        acceptAlert();
        itemPage.goToCart();
        cartPage.clickOnDeleteButton(itemName);

        // Assertion
        try {
            if (!cartPage.getItemTitle(itemName).isDisplayed()) {
                System.out.println("Element is not displayed");
            }
        } catch (NoSuchElementException exception) {
            System.out.println("Element not found");
        }
    }

    @Test(description = "Verify complete successful checkout with random item", priority = 6)
    public void checkoutSuccessfully() {
        homePage = new HomePage(webDriver);
        loginPopup = new LoginPopup(webDriver);
        categoriesPage = new CategoriesPage(webDriver);
        itemPage = new ItemPage(webDriver);
        cartPage = new CartPage(webDriver);

        homePage.reachSignPopup("log-in");
        loginPopup.login(getGlobalValue("Latest username").toString(), getGlobalValue("Latest password").toString());
        categoriesPage.clickOnAnyItem();
        String itemName = itemPage.getItemTitle();
        itemPage.addToCart();
        switchToAlert();
        acceptAlert();
        itemPage.goToCart();
        cartPage.clickOnPlaceButton();
        cartPage.fillCheckoutForm();
        cartPage.clickOnPurchaseButton();

        Assert.assertEquals(cartPage.getSuccessMessage().getText(), getTestData("successCheckout"));
    }
}
