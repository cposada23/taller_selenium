package objects.low;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropDown {
    private static Logger logger = LogManager.getLogger(DropDown.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
    private static WebDriver driver;
    public static void dropDownAutomation(String browser)  {
        logger.info("Drop down automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);
        By by = By.id("select-demo");
        Select select = new Select(driver.findElement(by));
        /*Select by index*/
        select.selectByIndex(1);
        sleep(1000);

        /*Get all the options*/
        List<WebElement> options = select.getOptions();

        /*Seleccionar una a una*/
        String value;
        for (WebElement option : options) {
            value = option.getText();
            /*Seleccionar por el texto visible*/
            logger.info("Selecting: " + value);
            select.selectByVisibleText(value);
            sleep(500);
        }
        sleep(1000);
        driver.quit();
        logger.info("Drop down automation ended");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
