import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import static org.junit.Assert.assertEquals;

// Тест на выпадающий список в разделе «Вопросы о важном»
@RunWith(Parameterized.class)
public class FaqTests {

    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    // Параметры для текущего теста: индекс вопроса и ожидаемый текст
    private final int index;
    private final String expectedText;
    //Конструктор, который принимает параметры
    public FaqTests(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }
    @Parameterized.Parameters(name = "Тест вопроса под индексом: {0}")
    public static Object[][] getAccordionData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @Test
    public void checkTextFaq(){
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage(); //открываем главную страницу
        mainPage.acceptCookies(); //убираем куки
        mainPage.clickQuestion(index); //кликаем на вопрос
        String actualText = mainPage.getAnswerText(index); //получаем текст ответа и сохраняем в переменной
        assertEquals("Текст ответа под индексом " + index + " не совпадает с ожидаемым!",
                expectedText, actualText); //сравниваем текст
    }
}
