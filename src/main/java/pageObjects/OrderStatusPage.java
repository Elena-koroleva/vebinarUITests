package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage extends BasePage {
    private final By image = By.cssSelector("img[alt='Not found']");
    //конструктор
    public OrderStatusPage(WebDriver driver) {
        super(driver); // Передали драйвер наверх, там создался наш wait
    }
    public boolean isOrderNotFoundImageDisplayed(){
        return waitForElement(image).isDisplayed();
    }
}
