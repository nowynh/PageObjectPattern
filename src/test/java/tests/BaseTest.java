package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setup() throws MalformedURLException {
//        driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://192.168.1.22:4444/wd/hub"),options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @AfterEach
    void clearCookies() {
        driver.manage().deleteAllCookies();
    }

}
