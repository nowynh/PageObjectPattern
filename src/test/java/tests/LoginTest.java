package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    void shouldBeAbleToLogInWhenCorrectCredentialsAreProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("test@softie.pl", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isUserLoggedIn());
    }

    @Test
    void shouldDisplayAuthenticationFailedWhenWrongCredentialsAreProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("test@gmail.com", "wrongPassword");
        Assertions.assertTrue(loginPage.isAuthenticationFailedAlertDisplayed());
    }

    @Test
    void shouldDisplayPasswordRequiredWhenNoPasswordProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("test@gmail.com", "");
        Assertions.assertTrue(loginPage.isPasswordRequiredAlertDisplayed());
    }

    @Test
    void shouldDisplayEmailAddressRequiredWhenNoEmailProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isEmaildRequiredAlertDisplayed());
    }

    @Test
    void shouldDisplayEmailAddressRequiredWhenNoCredentialsProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("", "");
        Assertions.assertTrue(loginPage.isEmaildRequiredAlertDisplayed());
    }

    @Test
    void shouldDisplayInvalidEmailErrorWhenEmailWithWrongSyntaxWasUsed() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("noDomainPart", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isInvalidEmaildAlertDisplayed());
        loginPage.login("invalidDominPart@domain@domin.pl", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isInvalidEmaildAlertDisplayed());
    }

}
