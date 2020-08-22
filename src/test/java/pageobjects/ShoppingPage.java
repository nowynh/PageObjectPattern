package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class ShoppingPage extends BasePage {

    @FindBy(xpath = "//i[@class='icon-th-list']")
    WebElement switchToListButton;

    @FindBy(xpath = "//div[@class='right-block-content row']//span[@class='price product-price']")
    private List<WebElement> productsPrices;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> products;

    @FindBy(xpath = "//a[contains(@class,'ajax_add_to_cart_button')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//span[@class='price cart_block_total ajax_block_cart_total']")
    private WebElement currentBasketTotal;

    @FindBy(xpath = "//div[@class='shopping_cart']//b")
    private WebElement cartIcon;

    @FindBy(id = "button_order_cart")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']")
    private WebElement firstStepProceedButton;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']/button[@type='submit']")
    private WebElement nextStepsProceedButton;

    @FindBy(id = "cgv")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[@name='processCarrier']")
    private WebElement processOrderButton;

    @FindBy(xpath = "//a[@class='bankwire']")
    private WebElement payByBankWireButton;

    @FindBy(xpath = "//div[@class='box']")
    private WebElement orderDetailsBox;

    private double expectedBasketValue = 2; //add shopping cost at start

    public ShoppingPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        PageFactory.initElements(driver, this);
    }

    public void switchToListView() {
        switchToListButton.click();
    }

    public void addRandomProductsToTheBasket(int productNumber) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < productNumber; i++) {
            int randomProductIndex = random.nextInt(products.size());
            updateExpectedBasketValue(randomProductIndex);
            clickOnAddToCartButton(randomProductIndex);
            clickOnContinueShoppingButton();
        }
    }

    private void updateExpectedBasketValue(int productIndex) {
        wait.until(ExpectedConditions.visibilityOf(productsPrices.get(productIndex)));
        String prodcutPrice = productsPrices.get(productIndex).getText(); //$12.12
        prodcutPrice = prodcutPrice.substring(1, prodcutPrice.length()); //12.12
        double price = Double.parseDouble(prodcutPrice);
        expectedBasketValue += price;
    }

    private void clickOnAddToCartButton(int index) {
        addToCartButtons.get(index).click();
    }

    private void clickOnContinueShoppingButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        int counter = 0;
        while (continueShoppingButton.isDisplayed() && counter < 10) {
            continueShoppingButton.click();
            Thread.sleep(200);
            counter++;
        }
    }

    public double getExpectedBasketValue() {
        double roundOffValue = Math.round(expectedBasketValue * 100.0) / 100.0;
        return roundOffValue;
    }

    public double getCurrentBasketValue() {
        scrollToElement(cartIcon);
        Actions builder = new Actions(driver);
        builder.moveToElement(cartIcon).build().perform();
        wait.until(ExpectedConditions.visibilityOf(currentBasketTotal));
        String currentBasketTotalText = currentBasketTotal.getText().substring(1, currentBasketTotal.getText().length());
        Double currentBasketTotalValue = Double.parseDouble(currentBasketTotalText);
        return currentBasketTotalValue;
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void placeOrderForCurrentBasket() {
        clickOnBasketPlaceOrderButton();
        proccedToPayment();
        payByBankWireAndConfirmOrder();
    }

    private void clickOnBasketPlaceOrderButton() {
        scrollToElement(cartIcon);
        Actions builder = new Actions(driver);
        builder.moveToElement(cartIcon).build().perform();
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        placeOrderButton.click();
    }

    public String getOrderDetailsBoxText() {
        wait.until(ExpectedConditions.visibilityOf(orderDetailsBox));
        return orderDetailsBox.getText();
    }

    private void proccedToPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(firstStepProceedButton));
        firstStepProceedButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(nextStepsProceedButton));
        nextStepsProceedButton.click();
        termsCheckbox.click();
        wait.until(ExpectedConditions.elementToBeClickable(processOrderButton));
        processOrderButton.click();
    }

    private void payByBankWireAndConfirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(payByBankWireButton));
        payByBankWireButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(nextStepsProceedButton));
        nextStepsProceedButton.click();
    }
}
