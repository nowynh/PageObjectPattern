package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    final String HOME_URL = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(HOME_URL);
        PageFactory.initElements(driver, this);
    }

    public HomePage(WebDriver driver) {
        super();
    }
}
