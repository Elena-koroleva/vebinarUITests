package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

//форма заказа "Про аренду"
public class OrderRentPage extends BasePage {

    //поле Когда привезти самокат
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //поле Срок аренды
    private final By rentPeriodField = By.className("Dropdown-placeholder");
    //чекбокс Чёрный жемчуг
    private final By blackColorCheckbox = By.xpath("//input[@id='black']");
    //чекбокс Серая безысходность
    private final By greyColorCheckbox = By.xpath("//input[@id='grey']");
    //поле комментарий для курьера
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    //локатор на кнопку Да окна Хотите оформить заказ
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //локатор для всплывающего окна "Заказ оформлен"
    private final By successOrderModal = By.xpath(".//div[text()='Заказ оформлен']");
    //конструктор
    public OrderRentPage(WebDriver driver) {
        super(driver); // Передали драйвер наверх, там создался наш wait
    }
    // метод заполняет поле Когда привезти самокат
    public void inputDateField(String dateOrder) {
        waitForClick(dateField).click();
        By dateOption = By.xpath(".//div[@aria-label='" + dateOrder + "']");
        waitForClick(dateOption).click();
    }
    // метод заполняет поле Срок аренды
    public void inputRentPeriod(String rentPeriod) {
        waitForClick(rentPeriodField).click();
        By periodOption = By.xpath("//div[contains(text(), '" + rentPeriod + "')]");
        waitForClick(periodOption).click();
    }
    //метод выбирает цвет самоката в чекбоксе
    public void chooseColor(String color) {
        if ("чёрный жемчуг".equalsIgnoreCase(color)) {
            waitForClick(blackColorCheckbox).click();
        } else if ("серая безысходность".equalsIgnoreCase(color)) {
            waitForClick(greyColorCheckbox).click();
        }
    }
    //метод заполняет поле Комментарий для курьера
    public void inputComment(String comment){
        waitForElement(commentField).sendKeys(comment);
    }
    //метод нажимает кнопку Заказать
    public void clickOrderButton(){
        waitForClick(orderButton).click();
    }
    //метод заполняет форму заказа Про аренду
    public void fillAboutRentForm(String dateOrder, String rentPeriod, String color, String comment) {
        inputDateField(dateOrder);
        inputRentPeriod(rentPeriod);
        chooseColor(color);
        inputComment(comment);
        clickOrderButton();
    }
    //метод нажимает кнопку Да
    public void clickYesButton(){
        waitForClick(yesButton).click();
    }
    // Метод проверяет, что окно успешного заказа появилось на экране
    public boolean isSuccessModalDisplayed() {
        return waitForElement(successOrderModal).isDisplayed();
    }
}
