package tests;

import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.ShoppingPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    @Test
    void basketShouldDisplayCorrectSumOfAddedProducts() throws InterruptedException {
        ShoppingPage shoppingPage = new ShoppingPage(driver, wait);
        shoppingPage.switchToListView();
        shoppingPage.addRandomProductsToTheBasket(5);
        assertEquals(shoppingPage.getExpectedBasketValue(), shoppingPage.getCurrentBasketValue());
    }

    @Test
    void shouldPlaceAnOrderForAddedProducts() throws InterruptedException {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("test@softie.pl", "1qaz!QAZ");

        ShoppingPage shoppingPage = new ShoppingPage(driver, wait);
        shoppingPage.switchToListView();
        shoppingPage.addRandomProductsToTheBasket(5);

        shoppingPage.placeOrderForCurrentBasket();
        assertTrue(shoppingPage.getOrderDetailsBoxText().contains("$" + shoppingPage.getExpectedBasketValue()));
    }

}
