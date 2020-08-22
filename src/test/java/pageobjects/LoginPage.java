package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    WebElement loginInputField;

    @FindBy(id = "passwd")
    WebElement passwordInputField;

    @FindBy(css = "#center_column >.alert-danger")
    WebElement alertMessage;

    @FindBy(id = "email_create")
    WebElement registerEmailInputField;

    public RegisterPage goToRegisterPage(String email) {
        Random random = new Random();
        String newEmail = "losowyemail" + random.nextInt(100000) + email;
        registerEmailInputField.sendKeys(newEmail);
        registerEmailInputField.sendKeys(Keys.ENTER);
        System.out.println(newEmail);
        return new RegisterPage(driver, wait);
    }

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String userName, String password) {
        loginInputField.clear();
        loginInputField.sendKeys(userName);
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
        passwordInputField.sendKeys(Keys.ENTER);
    }

    public boolean isAuthenticationFailedAlertDisplayed() {
        String alertText = alertMessage.getText();
        boolean isCorrectAlertText = alertText.equals("There is 1 error\nAuthentication failed.");
        return isCorrectAlertText;
    }

    public boolean isPasswordRequiredAlertDisplayed() {
        String alertText = alertMessage.getText();
        boolean isCorrectAlertText = alertText.equals("There is 1 error\nPassword is required.");
        return isCorrectAlertText;
    }

    public boolean isEmaildRequiredAlertDisplayed() {
        String alertText = alertMessage.getText();
        boolean isCorrectAlertText = alertText.equals("There is 1 error\nAn email address required.");
        return isCorrectAlertText;
    }

    public boolean isInvalidEmaildAlertDisplayed() {
        String alertText = alertMessage.getText();
        boolean isCorrectAlertText = alertText.equals("There is 1 error\nInvalid email address.");
        return isCorrectAlertText;
    }

}
