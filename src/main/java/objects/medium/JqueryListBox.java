package objects.medium;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class JqueryListBox {
    private static Logger logger = LogManager.getLogger(DownloadFile.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/jquery-dual-list-box-demo.html";
    private static WebDriver driver;
    public static void jquerylistBoxAutomation(String browser)  {
        logger.info("Jquery list box automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);
        Select multiple = new Select(driver.findElement(By.xpath("//*[@id=\"pickList\"]/div/div[1]/select")));
        // Select by index
        multiple.selectByIndex(1);
        String xpathButtonAdd = "//*[@id=\"pickList\"]/div/div[2]/button[1]";
        WebElement buttonAdd = driver.findElement(By.xpath(xpathButtonAdd));
        // Add selected index to the other one
        buttonAdd.click();
        //Get all the options
        List<WebElement> options = multiple.getOptions();

        // Select all the options
        for (int i = 0; i < options.size(); i++) {
            multiple.selectByIndex(i);
            sleep(500);
        }
        // Add all the options
        buttonAdd.click();

        sleep(2000);
        driver.quit();
        logger.info("Download automation  ended");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
