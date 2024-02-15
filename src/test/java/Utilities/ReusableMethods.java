package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import pages.HistoryPage;

public class ReusableMethods {

    public static void wait(int saniye){
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tumSayfaFotografCek(String resimAdi){
        // her screenshot'in benzersiz bir isme sahip olmasi icin
        // 1- method'un cagrildigi yerden resim adi yollanacak
        // 2- sonuna tarih etiketi ekleyelim 2310062013
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String tarihEtiketi = ldt.format(formatter);
        // target/screenShots/tumSayfaScreenshot.jpg
        String dinamikDosyaYolu = "target/screenShots/" + resimAdi + tarihEtiketi + ".jpg";
        TakesScreenshot tss = (TakesScreenshot) Driver.getDriver(); // tss objesi olusturduk
        File tumSayfaSS = new File(dinamikDosyaYolu); // kaydedecegimiz yeri hazirladik
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void webElementFotografCek(WebElement webElement, String resimAdi){
        // her screenshot'in benzersiz bir isme sahip olmasi icin
        // 1- method'un cagrildigi yerden resim adi yollanacak
        // 2- sonuna tarih etiketi ekleyelim 2310062013
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String tarihEtiketi = ldt.format(formatter);
        // target/screenShots/tumSayfaScreenshot.jpg
        String dinamikDosyaYolu = "target/screenShots/" + resimAdi + tarihEtiketi + ".jpg";
        File tumSayfaSS = new File(dinamikDosyaYolu); // kaydedecegimiz yeri hazirladik
        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }


    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }


    //JS
    // A function that scrolls the page up to the specified element.
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // A function that clicks the specified element using the JavaScriptExecutor.
    public static void clickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


  /*  public static void toplamlariTestEt() {
        Actions actions = new Actions(Driver.getDriver());
        double expSum = 0;
        HistoryPage historyPage = new HistoryPage();
        actions.moveToElement(historyPage.DDM).perform();
        historyPage.DDM.click();
        historyPage.row50.click();
        int currentPageNumber = 1;
        for (int i = 2; i < 9; i++) {
            int rowsPerPage = 50; // Her sayfadaki satır sayısı
            if (currentPageNumber == 3) {
                rowsPerPage = 6; // Son sayfadaki satır sayısı
            }

            //List<WebElement> tableOrder = Driver.getDriver().findElements(By.xpath("//td[" + i + "]"));
            WebElement actSumElement = Driver.getDriver().findElement(By.xpath("(//tbody/tr/td[" + i + "])[" + (rowsPerPage + 1) + "]"));

            for (int j = 1; j <= 50; j++) {
                WebElement cell = Driver.getDriver().findElement(By.xpath("(//tbody/tr/td[" + i + "])[" + rowsPerPage + "]"));
                String deger = cell.getText().replaceAll("[^\\d.]", "");
                expSum += Double.parseDouble(deger);
            }

            String actSumStr = actSumElement.getText().replaceAll("[^\\d.]", "");
            double actSum = Double.parseDouble(actSumStr);
            // double epsilon = 0.01; // İki double değeri karşılaştırırken kabul edilebilir hata payını belirtmek için kullanılır.
            System.out.println(i + " .column sums:" + expSum + " and " + actSum + " was seen as Grand Total");
            actions.moveToElement(historyPage.nextButton).perform();
            ReusableMethods.wait(2);
            historyPage.nextButton.click();
            currentPageNumber++;

            softAssert.assertEquals(actSum, expSum);
        }
        softAssert.assertAll();
    }

   */



}

