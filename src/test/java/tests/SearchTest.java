package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.SearchResultsPage;

public class SearchTest extends BaseTest {

    @Test
    void shouldDisplaySearchResultsWhenSearchForExistingProduct() {
        HomePage homePage = new HomePage(driver, wait);
        SearchResultsPage searchResultsPage = homePage.searchForPhrase("Dress");
        Assertions.assertTrue(searchResultsPage.isSearchResultsListNotEmpty());
        Assertions.assertEquals(7, searchResultsPage.getValueOfFoundProductsCounter());
    }

    @Test
    void shouldDisplayEmptySearchResultsWhenSearchForNotExistingProduct() {
        HomePage homePage = new HomePage(driver, wait);
        SearchResultsPage searchResultsPage = homePage.searchForPhrase("Non existing product");
        Assertions.assertTrue(searchResultsPage.isSearchResultsListEmpty());
        Assertions.assertEquals(0, searchResultsPage.getValueOfFoundProductsCounter());
    }
}
