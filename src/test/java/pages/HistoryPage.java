package pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HistoryPage {

    public HistoryPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@class='MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq']")
    public WebElement emailTextBox;

    @FindBy(xpath = "//*[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputAdornedEnd css-1uvydh2']")
    public WebElement passwordTextBox;

    @FindBy(xpath = "(//button[normalize-space()='Login'])[1]")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeLarge MuiButton-textSizeLarge MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeLarge MuiButton-textSizeLarge css-ic1i4k']")
    public WebElement skipforNowButton;

    @FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-18euqpt'])[2]")
    public WebElement chargebacksMenu;

    @FindBy(xpath = "(//a[@class='MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters css-rp9w8u'])[3]")
    public WebElement historyByStoreMenu;

    @FindBy(xpath = "//tbody")
    public WebElement table;

    @FindBy(xpath = "//tbody/tr/td[2]")
    public List<WebElement> aug2023;

    @FindBy(xpath = "(//tbody/tr/td[2])[11]")
    public WebElement totalAmountOfAug2023;

    @FindBy(xpath = "//tbody/tr/td[3]")
    public List<WebElement> sep2023;

    @FindBy(xpath = "(//tbody/tr/td[3])[11]")
    public WebElement totalAmountOfSep2023;

    @FindBy(xpath = "//tbody/tr/td[4]")
    public List<WebElement> oct2023;

    @FindBy(xpath = "(//tbody/tr/td[4])[11]")
    public WebElement totalAmountOfOct2023;

    @FindBy(xpath = "//tbody/tr/td[5]")
    public List<WebElement> nov2023;

    @FindBy(xpath = "(//tbody/tr/td[5])[11]")
    public WebElement totalAmountOfNov2023;

    @FindBy(xpath = "//tbody/tr/td[6]")
    public List<WebElement> dec2023;

    @FindBy(xpath = "(//tbody/tr/td[6])[11]")
    public WebElement totalAmountOfDec2023;

    @FindBy(xpath = "//tbody/tr/td[7]")
    public List<WebElement> jan2023;

    @FindBy(xpath = "(//tbody/tr/td[7])[11]")
    public WebElement totalAmountOfJan2023;

    @FindBy(xpath = "//tbody/tr/td[8]")
    public List<WebElement> feb2023;

    @FindBy(xpath = "(//tbody/tr/td[8])[11]")
    public WebElement totalAmountOfFeb2023;

    @FindBy(xpath = "(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-i4bv87-MuiSvgIcon-root'])[3]")
    public WebElement nextButton;
    // (//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-i4bv87-MuiSvgIcon-root'])[3]  ---- relxPath
    // /html[1]/body[1]/div[1]/div[3]/main[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/button[2]/*[name()='svg'][1]/*[name()='path'][1]





}
