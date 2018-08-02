package objects.low;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlertBox {
    private static Logger logger = LogManager.getLogger(AlertBox.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/javascript-alert-box-demo.html";
    private static WebDriver driver;
    public static void alertBoxAutomation(String browser)  {
        logger.info("Alert box automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);
        /***************    Simple alert   ********************/
        /*Open the alert box*/
        driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/button")).click();
        /*switch to alert*/
        Alert simpleAlert = driver.switchTo().alert();
        logger.info("Simple alert text " + simpleAlert.getText());
        sleep(500);
        simpleAlert.accept();
        logger.info("Simple alert text accepted");
        sleep(500);

        /********************   Confirmation alert  ********************/
        //Open alert
        logger.info("Confirmation alert");
        WebElement buttonConfirmationAlert = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/button"));
        buttonConfirmationAlert.click();
        Alert confirmationAlert = driver.switchTo().alert();
        // Dismiss
        logger.info("Confirmation alert opened");
        sleep(1000);
        confirmationAlert.dismiss();
        logger.info("Confirmation alert dismissed");
        sleep(1000);
        // Confirm
        buttonConfirmationAlert.click();
        confirmationAlert = driver.switchTo().alert();
        logger.info("Confirmation alert opened");
        sleep(1000);
        confirmationAlert.accept();
        logger.info("alert confirmed");


        /********************   Prompt alert  ********************/
        String xpath = "//*[@id=\"easycont\"]/div/div[2]/div[3]/div[2]/button";
        WebElement buttonPromtAlert = driver.findElement(By.xpath(xpath));

        //Open prompt alert
        buttonPromtAlert.click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Hello Prompt alert");
        sleep(1000);
        promptAlert.accept();


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
