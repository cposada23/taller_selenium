package objects.low;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckBox {
    private static Logger logger = LogManager.getLogger(CheckBox.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/basic-checkbox-demo.html";
    private static WebDriver driver;
    public static void checkBoxAutomation(String browser)  {
        logger.info("Check box automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);
        By bySingle = By.id("isAgeSelected");
        WebElement singleCheckBox = driver.findElement(bySingle);
        /*Select checkbox*/
        singleCheckBox.click();
        sleep(500);
        /*Deselect checkbox*/
        singleCheckBox.click();
        sleep(500);


        /*Multiple checkboxes*/
        List<WebElement> checkboxes = driver.findElements(By.className("cb1-element"));
        logger.info(checkboxes.size() + " FOUNDED");

        /*CHECK ON E BY ONE*/
        for (WebElement checkbox: checkboxes) {
            checkbox.click();
            sleep(500);
        }

        /*UNCHECK ALL USING THE BUTTON AT THE BOTTOM*/
        WebElement button = driver.findElement(By.id("check1"));
        button.click();

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
