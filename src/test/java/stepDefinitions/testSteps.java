package stepDefinitions;

import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HistoryPage;
import pages.TransactionsPage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
        ReusableMethods.wait(18);
        historyPage.table.click();
        actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.END).perform();
        ReusableMethods.wait(1);

      double columnTotals=0;
      int currentPageNumber = 1; // Mevcut sayfa numarası
      while (historyPage.nextButton.isEnabled()) {
          int rowsPerPage = 10; // Her sayfadaki satır sayısı
          if (currentPageNumber == 10) {
              rowsPerPage = 5; // Son sayfadaki satır sayısı
          }
          double columnTotal = 0;
          for (int i = 2; i < 9; i++) {
              for (int j = 1; j <= 10; j++) {
                  WebElement cell = Driver.getDriver().findElement(By.xpath("(//tbody/tr/td[" + i + "])[" + j + "]"));
                  double value = Double.parseDouble(cell.getText().replaceAll("[^\\d.]", ""));
                  columnTotal += value;
              }
          }
          columnTotals += columnTotal;

          Actions actions = new Actions(Driver.getDriver()); // Click next button to navigate to the next page
          actions.moveToElement(historyPage.nextButton).perform();
          ReusableMethods.wait(1);
          historyPage.nextButton.click();
          currentPageNumber++; // Sayfa numarasını bir arttır
      }
        for (int i = 2; i < 9 ; i++) {
            WebElement totalCell = Driver.getDriver().findElement(By.xpath("(//tbody/tr/td[" + i + "])[11]"));
            double grandTotal = Double.parseDouble(totalCell.getText().replaceAll("[^\\d.]", ""));
            System.out.println(i + " .column sums:" + columnTotals + " and " + grandTotal + " was seen as Grand Total");
        }


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

        List<List<String>> data = getDataFromTable(Driver.getDriver().findElement(By.xpath("//table[@class='MuiTable-root css-15i8i05-MuiTable-root']")));
        // Verileri Excel dosyasına yaz
        writeDataToExcel(data, "generated.csv");

    }

    private static List<List<String>> getDataFromTable(WebElement table) {
        List<List<String>> data = new ArrayList<>();
        List<WebElement> columnNames = Driver.getDriver().findElements(By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle2 css-m09714-MuiTypography-root']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Kolon adlarını içeren satırı ekle
        List<String> headerRowData = new ArrayList<>();
        for (WebElement columnName : columnNames) {
            headerRowData.add(columnName.getText());
        }

        // Tablodaki diğer satırları ekle
        boolean isHeaderAdded = false; // Başlık satırı eklenip eklenmediğini kontrol etmek için
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();

            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }

            if (!isHeaderAdded && !rowData.isEmpty()) {
                // İlk satırda boşluk eklenmeyecek
                data.add(headerRowData);
                isHeaderAdded = true;
            }

            // Boş satırları eklememe kontrolü
            if (rowData.size() > 0) {
                data.add(rowData);
            }
        }

        return data;
    }



    private static void writeDataToExcel(List<List<String>> data, String outputPath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i);
                List<String> rowData = data.get(i);

                for (int j = 0; j < rowData.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
                workbook.write(fileOut);
                System.out.println("Veriler başarıyla Excel dosyasına yazıldı.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Given("Closes the page.")
    public void closes_the_page() { Driver.closeDriver();}

}


