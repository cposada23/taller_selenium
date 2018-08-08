package KeyWordFramework.configuration;


import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeyWords {

    static WebDriver driver;
    private static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(KeyWords.class.getName());

    public boolean openBrowser(String browserName) {
        try {
            driver = DriverFactory.getDriver(browserName, true, true);
            return true;
        }catch (Exception e) {
            logger.info("Not able to open browser " + e.getMessage());
            return false;
        }
    }

    public void openUrl(String url){
        logger.info("Opening URL: " + url);
        try {
            driver.navigate().to(url);
        }catch (Exception e) {
            logger.error("Not able to open URL" + e.getMessage());
        }
    }

    public By locatorValue(String locatorType, String value) {
        By by;
        switch (locatorType) {
            case "id":
                by = By.id(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                by = null;
                break;
        }
        return by;
    }

    public void clickOnButton(String locatorType, String locatorValue){
        try {
            By locator = locatorValue(locatorType, locatorValue);
            WebElement button = driver.findElement(locator);
            button.click();
        } catch (NoSuchElementException e) {
            logger.error("No Element Found to enter text" + e);
        }
    }

    public void enterText(String locatorType, String value, String text) {
        try {
            By locator;
            locator = locatorValue(locatorType, value);
            WebElement element = driver.findElement(locator);
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            logger.error("No Element Found to enter text" + e);
        }
    }




    public void closeBrowser(){
        sleep(1000);
        driver.quit();
    }

    public void waitFor(String duration) {
        int d = Integer.parseInt(duration);
        sleep(d);
    }

    public void waitFor(Double duration){
        sleep(duration.intValue());
    }


    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
