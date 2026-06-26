import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import static pageObjects.BasePage.MAIN_PAGE_URL;

public class LogoTests {

    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test //Если нажать на логотип Самоката, попадёшь на главную страницу Самоката
    public void logoScooterNavigatesToMainPage(){
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickOnUpperOrderButton()
                .clickLogoScooter()
                .waitForMainPageToLoad();
        String actualUrl = driver.getCurrentUrl(); //запрашиваем у браузера адрес URL и записываем в переменную
        Assert.assertEquals("Логотип 'Самокат' не вернул на главную страницу",MAIN_PAGE_URL, actualUrl);
    }
    @Test //Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
    public void logoYandexOpensDzenInNewTab(){
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickLogoYandexAndSwitchToTab(); // Кликаем и сразу переключаемся внутри метода
        mainPage.waitForDzenPageToLoad(); // Ждем загрузки Дзена на новой вкладке
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue("Клик на логотип Яндекса не открыл Дзен", actualUrl.contains("dzen.ru"));
    }
}