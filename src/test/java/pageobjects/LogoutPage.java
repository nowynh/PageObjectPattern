package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends HomePage {
    @FindBy(css = ".logout")
    WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    private void clickOnLogoutButton() {
       logoutButton.click();
    }
}