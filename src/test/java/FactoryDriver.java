import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FactoryDriver extends ExternalResource {

    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }
    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else {
            startChrome();
        }
    }
    private void startChrome(){
        // Скачиваем и настраиваем нужный ChromeDriver под версию вашего Chrome
        WebDriverManager.chromedriver().setup();
        // Открываем сам браузер Chrome
        driver = new ChromeDriver();
        //разворачиваем окно на весь экран
        driver.manage().window().maximize();
    }
    private void startFirefox(){
        // Скачиваем и настраиваем нужный firefoxdriver под версию вашего Firefox
        WebDriverManager.firefoxdriver().setup();
        // Открываем сам браузер Firefox
        driver = new FirefoxDriver();
        //разворачиваем окно на весь экран
        driver.manage().window().maximize();
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {

        driver.quit();
    }
}
