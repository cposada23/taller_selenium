package factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver getDriver(String driverName, Boolean maximize, boolean implicitWait) {
        driverName = driverName.toUpperCase();
        WebDriver driver;
        switch (driverName) {
            case "FIREFOX":
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                driver =  new FirefoxDriver(capabilities);
                break;
            case "CHROME":
                driver = new ChromeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        if(maximize) {
            driver.manage().window().maximize();
        }

        if(implicitWait) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
}