package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class LogoutTest extends BaseTest {

    @Test
    void shouldBeLogoutWhenSignOutButtonIsClicked() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("test@softie.pl", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isUserLoggedIn());
        homePage.logoutUser();
        Assertions.assertTrue(loginPage.isUserNotLoggedIn());
    }
}
