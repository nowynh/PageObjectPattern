package tests;

import org.junit.jupiter.api.*;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test
    public void shouldRegisterNewUserWhenCorrectMandatoryDataProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();

        RegisterPage registerPage = loginPage.goToRegisterPage("@wp.pl");

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "Wojska Polskiego 12",
                "Warszawa", "02123", "Florida", "12412341");

        Assertions.assertTrue(homePage.isUserLoggedIn());
    }

    @Test
    public void shouldDisplayErrorMessageWhenNotAllMandatoryDataProvided() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();

        RegisterPage registerPage = loginPage.goToRegisterPage("@wp.pl");

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "",
                "", "", "Florida", "");

        Assertions.assertTrue(registerPage.isRegisterErrorDisplayed());
        Assertions.assertTrue(registerPage.isMissingAddress1ErrorDisplayed());
        Assertions.assertTrue(registerPage.isMissingCityErrorDisplayed());
        Assertions.assertTrue(registerPage.isInvalidPostcodeErrorDisplayed());
        Assertions.assertTrue(registerPage.isMissingPhoneNumberErrorDisplayed());
    }

    @Test
    public void shouldDisplayInvalidPostcodeErrorWhenPostcodeIsNotFiveDigits() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.goToLoginPage();

        RegisterPage registerPage = loginPage.goToRegisterPage("@wp.pl");

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "",
                "", "1234", "Florida", "");
        Assertions.assertTrue(registerPage.isInvalidPostcodeErrorDisplayed());

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "",
                "", "123456", "Florida", "");
        Assertions.assertTrue(registerPage.isInvalidPostcodeErrorDisplayed());

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "",
                "", "abcde", "Florida", "");
        Assertions.assertTrue(registerPage.isInvalidPostcodeErrorDisplayed());

        registerPage.registerNewUser("Jan", "Kowalski", "1qaz!QAZ", "",
                "", "12345", "Florida", "");
        Assertions.assertFalse(registerPage.isInvalidPostcodeErrorDisplayed());

    }


}
