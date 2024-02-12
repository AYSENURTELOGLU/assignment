package stepDefinitions;

import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HistoryPage;
import pages.TransactionsPage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class testSteps {

    HistoryPage historyPage= new HistoryPage();
    TransactionsPage transactionsPage= new TransactionsPage();
    Actions actions;

    @Given("The browser opens and the user goes to {string}.")
    public void the_browser_opens_and_the_user_goes_to(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        ReusableMethods.wait(2);
    }

    @Given("The users clicks the login button after entering the relevant information in {string} and {string} box.")
    public void the_users_clicks_the_login_button_after_entering_the_relevant_information_in_and_box(String email, String password) {
        historyPage.emailTextBox.sendKeys(ConfigReader.getProperty(email));
        historyPage.passwordTextBox.sendKeys(ConfigReader.getProperty(password));
        historyPage.loginButton.click();
        ReusableMethods.wait(3);
        historyPage.skipforNowButton.click();
        ReusableMethods.wait(2);
    }

    @Given("Verify that the total amounts of each month at the bottom row  should be the sum of the values of the locations for each corresponding month.")
    public void verify_that_the_total_amounts_of_each_month_at_the_bottom_row_should_be_the_sum_of_the_values_of_the_locations_for_each_corresponding_month() {
        historyPage.chargebacksMenu.click();
        historyPage.historyByStoreMenu.click();
        ReusableMethods.wait(20);
      /*  historyPage.table.click();
        actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.END).perform();
        ReusableMethods.wait(3);
       */

        List<WebElement> octColumn= historyPage.oct2023;
        int sumOfOct=0;
            for (WebElement each : octColumn
            ) {
                System.out.println(each.getText());
                int value = Integer.parseInt(each.getText().replaceAll("\\W", ""));
                System.out.println("amount: " + value);
                sumOfOct += value;

            }

        //sumOfOct /=2;

        System.out.println("Sum of elements: " + sumOfOct);
        int totalAmount=Integer.parseInt(historyPage.totalAmountOfOct2023.getText());
        Assert.assertEquals(sumOfOct,totalAmount);

    }
    //P2
    @Given("The user select Artisan Alchemy and Blissful buffet checkbox from locations filter and Grubhub checkbox from marketplaces filter.")
    public void the_user_select_artisan_alchemy_and_blissful_buffet_checkbox_from_locations_filter_and_grubhub_checkbox_from_marketplaces_filter() {
        transactionsPage.chargebacksMenu.click();
        transactionsPage.transactionMenu.click();
        ReusableMethods.wait(2);
        transactionsPage.locationsFilter.click();
        transactionsPage.clearButtoninLocations.click();
        transactionsPage.checkboxArtisan.click();
        transactionsPage.checkboxBlissful.click();
        transactionsPage.applyButtoninLocations.click();
        ReusableMethods.wait(4);
        transactionsPage.marketplacesFilter.click();
        transactionsPage.clearButtoninMarketplaces.click();
        transactionsPage.checkboxGrubhub.click();
        transactionsPage.applyButtoninMarketplaces.click();
        ReusableMethods.wait(1);
    }

    @Given("The user downland the CVS and verify that transaction types are on the correct column from the CVS")
    public void the_user_downland_the_cvs_and_verify_that_transaction_types_are_on_the_correct_column_from_the_cvs() throws FileNotFoundException {
        ReusableMethods.scrollToElement(Driver.getDriver(), transactionsPage.downloadButton);
        transactionsPage.downloadButton.click();
        FileInputStream fis = new FileInputStream("excelDosyasi.xlsx");

    }


    @Given("Closes the page.")
    public void closes_the_page() {

    }

}


