package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //локатор логотипа Самокат
    private final By logoScooter = By.className("Header_LogoScooter__3lsAR");
    //локатор логотипа Яндекс
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");

    protected WebDriver driver;
    // Создаем одну переменную ожидания на весь проект
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Настраиваем её один раз на 5 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    // скролл
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //метод клика по логотипу Самокат
    public MainPage  clickLogoScooter(){
        waitForClick(logoScooter).click();
        return new MainPage(driver); // Возвращаем объект страницы, на которую перешли
    }
    // метод клика по логотипу Яндекс
    public void clickLogoYandexAndSwitchToTab() {
        String originalWindow = driver.getWindowHandle(); //Запоминаем текущую вкладку
        waitForClick(logoYandex).click(); //Кликаем по логотипу Яндекса
        waitForNewTabToOpen();
        switchToNewTab(originalWindow);
    }
    //метод ждёт появление второй вкладки
    private void waitForNewTabToOpen() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }
    //метод переключения фокуса на вторую вкладку
    private void switchToNewTab(String originalWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    // Ожидание загрузки Дзена
    public void waitForDzenPageToLoad() {
        wait.until(ExpectedConditions.urlContains("dzen.ru"));
    }
    //метод ждет появления элемента на экране и возвращает его
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //метод ждет, когда кнопка станет кликабельной (принимает локатор)
    protected WebElement waitForClick(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    //метод ждет, когда кнопка станет кликабельной (принимает уже найденный элемент)
    protected WebElement waitForClick(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
