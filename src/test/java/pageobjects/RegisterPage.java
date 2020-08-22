package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    @FindBy(id = "customer_firstname")
    WebElement firstNameInputfield;

    @FindBy(id = "customer_lastname")
    WebElement lastNameInputfield;

    @FindBy(id = "passwd")
    WebElement passwordInputfield;

    @FindBy(id = "address1")
    WebElement address1Inputfield;

    @FindBy(id = "id_state")
    WebElement stateSelectfield;

    @FindBy(id = "city")
    WebElement cityInputfield;

    @FindBy(id = "postcode")
    WebElement postcodeInputfield;

    @FindBy(id = "phone_mobile")
    WebElement phoneMobileInputfield;

    @FindBy(id = "submitAccount")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    WebElement errorMessage;

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void registerNewUser(String firstName, String lastName, String password, String address1,
                                String city, String postcode, String state, String phoneNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputfield));

        firstNameInputfield.clear();
        firstNameInputfield.sendKeys(firstName);
        lastNameInputfield.clear();
        lastNameInputfield.sendKeys(lastName);
        passwordInputfield.clear();
        passwordInputfield.sendKeys(password);
        address1Inputfield.clear();
        address1Inputfield.sendKeys(address1);
        cityInputfield.clear();
        cityInputfield.sendKeys(city);
        postcodeInputfield.clear();
        postcodeInputfield.sendKeys(postcode);

        Select stateSelect = new Select(stateSelectfield);
        stateSelect.selectByVisibleText(state);

        phoneMobileInputfield.clear();
        phoneMobileInputfield.sendKeys(phoneNumber);

        clickOnRegisterButton();
    }

    private void clickOnRegisterButton() {
        submitButton.click();
    }

    public boolean isRegisterErrorDisplayed() {
        return errorMessage.isDisplayed();
    }

    public boolean isMissingPasswordErrorDisplayed() {
        return errorMessage.getText().contains("passwd is required.");
    }

    public boolean isMissingCityErrorDisplayed() {
        return errorMessage.getText().contains("city is required.");
    }

    public boolean isMissingAddress1ErrorDisplayed() {
        return errorMessage.getText().contains("address1 is required.");
    }

    public boolean isInvalidPostcodeErrorDisplayed() {
        return errorMessage.getText().contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
    }

    public boolean isMissingStateErrorDisplayed() {
        return errorMessage.getText().contains("This country requires you to choose a State.");
    }

    public boolean isMissingPhoneNumberErrorDisplayed() {
        return errorMessage.getText().contains("You must register at least one phone number.");
    }


}

