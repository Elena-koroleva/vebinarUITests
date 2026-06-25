package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    //локаторы для статуса заказа
    private final By statusButton = By.className("Header_Link__1TAG7"); //кнопка Статус заказа
    private final By enterField = By.className("Input_Input__1iN_Z"); //поле Введите номер заказа
    private final By goButton = By.cssSelector(".Header_Button__28dPO"); //кнопка Go
    //локатор для верхней кнопки Заказать
    private final By upperOrderButton = By.xpath("//div[contains(@class, 'Header_Nav')]/button[text()='Заказать']");
    //локатор для нижней кнопки Заказать
    private final By lowerOrderButton = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");
    //локатор для кук
    private final By cookieButton = By.id("rcc-confirm-button");
    //динамические локаторы для вопросов и ответов
    private final String questionTemplateId = "accordion__heading-%d";
    private final String answerTemplateId = "accordion__panel-%d";
    //конструктор
    public MainPage(WebDriver driver) {
        super(driver); // Передали драйвер наверх, там создался наш wait
    }
    //метод открывает главную страницу
    public void openPage(){
        driver.get(MAIN_PAGE_URL);
    }
    //принимаем куки
    public void acceptCookies() {
        waitForClick(cookieButton).click();
    }
    //метод нажимает на кнопку Статус заказа
    public void clickOrderStatus() {
        // Ждем, пока кнопка статуса заказа станет кликабельной, и только потом кликаем
        waitForClick(statusButton).click();
    }
    //метод вводит текст в поле "Введите номер заказа"
    public void enterOrderNumber(){
        // Ждем, пока поле ввода станет видимым на экране, прежде чем писать текст
        waitForElement(enterField).sendKeys("23254647657768");
    }
    //метод нажимает на кнопку Go
    public OrderStatusPage clickOnGoButton(){
        waitForClick(goButton).click();
        return new OrderStatusPage(driver);
    }
    //метод нажимает на верхнюю кнопку Заказать
    public OrderCustomerPage clickOnUpperOrderButton(){
        waitForClick(upperOrderButton).click();
        return new OrderCustomerPage(driver);
    }
    //метод нажимает на нижнюю кнопку Заказать
    public OrderCustomerPage clickOnLowerOrderButton(){
        WebElement element = waitForElement(lowerOrderButton);
        scrollToElement(element);
        waitForClick(element).click();
        return new OrderCustomerPage(driver);
    }
    //метод нажимает по вопросу
    public void clickQuestion(int index) {
        By questionLocator = By.id(String.format(questionTemplateId, index)); //готовим локатор
        scrollToElement(driver.findElement(questionLocator)); //находим элемент и скроллим
        waitForClick(questionLocator).click();
    }
    //метод получает текст ответа и отдает его
    public String getAnswerText(int index) {
        By answerLocator = By.id(String.format(answerTemplateId, index)); //готовим локатор
        return waitForElement(answerLocator).getText(); //ждем элемент и берем его текст
    }
}
