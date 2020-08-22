package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    List<WebElement> foundProductsList;

    @FindBy(xpath = "//*[@class='heading-counter']")
    WebElement foundProductsCounter;

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isSearchResultsListNotEmpty() {
        return !foundProductsList.isEmpty();
    }

    public boolean isSearchResultsListEmpty() {
        return foundProductsList.isEmpty();
    }

    public int getValueOfFoundProductsCounter() {
        String foundProductsCounterText = foundProductsCounter.getText();
        String extractedDigitsOnly = foundProductsCounterText.replaceAll("\\D+", "");
        int valueOfFoundProductsCounter = Integer.valueOf(extractedDigitsOnly);
        return valueOfFoundProductsCounter;
    }
}
