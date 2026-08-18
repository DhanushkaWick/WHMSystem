package TestComponent;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties prop = new Properties();



    public BaseTest () throws IOException{
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "\\src\\main\\resources\\properties.properties");
        prop.load(fis);

    }
    public WebDriver initializeDriver() throws IOException {
        // properties class


        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");// browser eke g
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));//full screen

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "/Users/rahulshetty//documents//geckodriver");
            driver = new FirefoxDriver();
            // Firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            // Edge
            System.setProperty("webdriver.edge.driver", "edge.exe");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;

    }
    @AfterClass(alwaysRun = true)
    public void quitDriver(){
        if (driver != null) {
            driver.quit();
        }
    }
}
