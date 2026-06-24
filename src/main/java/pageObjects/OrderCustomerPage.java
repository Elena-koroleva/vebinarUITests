package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//форма заказа "Для кого самокат"
public class OrderCustomerPage extends BasePage {

    //поле имя
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    //поле фамилия
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //поле адрес: куда привезти заказ
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //поле телефон:на него позвонит курьер
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    //конструктор
    public OrderCustomerPage(WebDriver driver) {
        super(driver); // Передали драйвер наверх, там создался наш wait
    }
    //метод заполняет поле Имя
    public void inputName(String name) {
        waitForElement(nameField).sendKeys(name);
    }
    //метод заполняет поле Фамилия
    public void inputSurname(String surname) {
        waitForElement(surnameField).sendKeys(surname);
    }
    //метод заполняет поле Адрес
    public void inputAddress(String address) {
        waitForElement(addressField).sendKeys(address);
    }
    //метод заполняет поле Станция метро
    public void inputMetroStation(String metroStation) {
        waitForElement(metroStationField).sendKeys(metroStation);
        By stationOption = By.xpath("//div[contains(text(), '" + metroStation + "')]");
        waitForClick(stationOption).click();
    }
    //метод заполняет поле Телефон
    public void inputPhone(String phone) {
        waitForElement(phoneField).sendKeys(phone);
    }
    //метод нажимает на кнопку Далее
    public OrderRentPage clickNextButton() {
        waitForClick(nextButton).click();
        return new OrderRentPage(driver);
    }
    //метод заполняет форму заказа Для кого самокат
    public OrderRentPage fillWhoIsScooterForm(String name, String surname, String address, String metroStation, String phone) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        inputMetroStation(metroStation);
        inputPhone(phone);
        return clickNextButton();
    }
}
