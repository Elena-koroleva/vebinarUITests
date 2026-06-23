import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderStatusPage;
import static org.junit.Assert.assertTrue;

public class ScooterTests {

    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test //тест «Проверка окна "Заказ не найден" при вводе некорректного номера»
    public void orderNotFoundWindowCheckWhenIncorrectNumberTest(){
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickOrderStatus();
        mainPage.enterOrderNumber();
        OrderStatusPage orderStatusPage = mainPage.clickOnGoButton();
        assertTrue(orderStatusPage.isOrderNotFoundImageDisplayed());
    }
}
