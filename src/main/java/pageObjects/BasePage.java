package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    protected WebDriver driver;
    // Создаем одну переменную ожидания на весь проект
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Настраиваем её один раз на 5 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //метод ждет появления элемента на экране и возвращает его
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //метод ждет, когда кнопка станет кликабельной
    protected WebElement waitForClick(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
