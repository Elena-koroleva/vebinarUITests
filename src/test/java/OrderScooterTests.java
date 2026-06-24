import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderCustomerPage;
import pageObjects.OrderRentPage;

import static org.junit.Assert.assertTrue;

// Тест на Заказ самоката
@RunWith(Parameterized.class)
public class OrderScooterTests {

    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();
    // Параметры теста
    private final String orderButtonLocation; // "Верхняя" или "Нижняя" кнопки
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String dateOrder;
    private final String rentPeriod;
    private final String color;
    private final String comment;

    public OrderScooterTests(String orderButtonLocation, String name, String surname, String address,
                             String metroStation, String phone, String dateOrder, String rentPeriod,
                             String color, String comment) {
        this.orderButtonLocation = orderButtonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters(name = "Заказ через кнопку: {0}, пользователь: {1} {2}")
    public static Object[][] getParams() {
        return new Object[][]{
                {"Верхняя", "Александр", "Пушкин", "Тульская, 5", "Тульская", "+79163456678",
                        "Choose вторник, 30-е июня 2026 г.", "сутки", "серая безысходность", "Позвонить на месте"},
                {"Нижняя", "Мария", "Ивановна", "Павелецкая, 36", "Павелецкая", "+79181234567",
                        "Choose среда, 24-е июня 2026 г.", "семеро суток", "серая безысходность", "Не звонить, только смс"}
        };

    }
    @Test
    public void scooterOrderFlowTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.acceptCookies();
        OrderCustomerPage customerPage=null;
        if("Верхняя".equalsIgnoreCase(orderButtonLocation)){
            customerPage = mainPage.clickOnUpperOrderButton();
        } else if("Нижняя".equalsIgnoreCase(orderButtonLocation)) {
            customerPage = mainPage.clickOnLowerOrderButton();
        }
        OrderRentPage orderRentPage = customerPage.fillWhoIsScooterForm(name, surname, address, metroStation, phone);
        orderRentPage.fillAboutRentForm(dateOrder,rentPeriod,color,comment);
        orderRentPage.clickYesButton();
        assertTrue("Окно успешного заказа не появилось", orderRentPage.isSuccessModalDisplayed());
    }
}
