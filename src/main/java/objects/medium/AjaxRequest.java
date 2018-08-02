package objects.medium;

import factory.DriverFactory;
import objects.low.AlertBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AjaxRequest {

    private static Logger logger = LogManager.getLogger(AlertBox.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/javascript-alert-box-demo.html";
    private static WebDriver driver;
    public static void ajaxAutomation(String browser)  {
        logger.info("Alert box automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);



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
