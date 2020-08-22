package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".login")
    WebElement goToLoginPageLink;

    @FindBy(css = ".logout")
    WebElement logOutButton;

    @FindBy(id = "search_query_top")
    WebElement searchInputField;

    @FindBy(name = "submit_search")
    WebElement searchButton;

    public LoginPage goToLoginPage() {
        goToLoginPageLink.click();
        LoginPage loginPage = new LoginPage(driver, wait);
        return loginPage;
    }

    public boolean isUserLoggedIn() {
        return logOutButton.isDisplayed();
    }

    public boolean isUserNotLoggedIn() {
        return goToLoginPageLink.isDisplayed();
    }

    public void logoutUser() {
        logOutButton.click();
    }

    public SearchResultsPage searchForPhrase(String searchPhrase) {
        searchInputField.clear();
        searchInputField.sendKeys(searchPhrase);
        searchButton.click();
        return new SearchResultsPage(driver, wait);
    }

}
