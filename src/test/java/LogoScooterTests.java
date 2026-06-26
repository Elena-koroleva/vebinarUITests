import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import static pageObjects.BasePage.MAIN_PAGE_URL;

public class LogoScooterTests {

    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
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
}