package objects.medium;

import com.google.common.base.Function;
import factory.DriverFactory;
import objects.low.CheckBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class DownloadFile {
    private static Logger logger = LogManager.getLogger(DownloadFile.class.getName());
    private static final String URL = "http://www.seleniumeasy.com/test/generate-file-to-download-demo.html";
    private static WebDriver driver;
    public static void downloadFileAutomation(String browser)  {
        logger.info("Check box automation started...");
        driver = DriverFactory.getDriver(browser, true, true);
        driver.get(URL);
        /*File creation*/
        WebElement textArea = driver.findElement(By.id("textbox"));
        textArea.sendKeys("Hola\nEste es el archivo para descargar");
        WebElement buttonCreate = driver.findElement(By.id("create"));
        buttonCreate.click();

        /*Fluentwait to wait for the link*/
        /*FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofMillis(2000));
        wait.withTimeout(Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class);
        Function<WebDriver, String> function = new Function<WebDriver, String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver input) {
                logger.info("Checking download link");
                WebElement aLink = input.findElement(By.id("link-to-download"));
                return aLink.getAttribute("href");
            }
        };*/

        // String url = wait.until(function);
        WebElement aLink = driver.findElement(By.id("link-to-download"));
        aLink.click();
        //logger.info("url " + url);
        sleep(1000);
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
