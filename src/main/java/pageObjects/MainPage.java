package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    //локаторы
    private final By statusButton = By.className("Header_Link__1TAG7"); //кнопка Статус заказа
    private final By enterField = By.className("Input_Input__1iN_Z"); //поле Введите номер заказа
    private final By goButton = By.cssSelector(".Header_Button__28dPO"); //кнопка Go
    //конструктор
    public MainPage(WebDriver driver) {
        super(driver); // Передали драйвер наверх, там создался наш wait
    }
    //методы
    public void openPage(){
        driver.get(MAIN_PAGE_URL);
    }
    public void clickOrderStatus() {
        // Ждем, пока кнопка статуса заказа станет кликабельной, и только потом кликаем
        waitForClick(statusButton).click();
    }
    public void enterOrderNumber(){
        // Ждем, пока поле ввода станет видимым на экране, прежде чем писать текст
        waitForElement(enterField).sendKeys("23254647657768");
    }
    public OrderStatusPage clickOnGoButton(){
        waitForClick(goButton).click();
        return new OrderStatusPage(driver);
    }
}
