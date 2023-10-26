import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

public class TestSeturAutomation {

    private static WebDriver driver;
    private HomePage homePage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SERKAN\\Desktop\\Selenium\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSeturAutomation() {
        homePage = new HomePage(driver);
        driver.manage().window().maximize();

        // 1. Sayfaya gidilir
        homePage.goToSetur();

        // 2. Setur URL'sinin geldiğini kontrol edin
        homePage.verifySeturUrl();

        // 3. Ana sayfada Otel tabının default geldiğini kontrol edin
        homePage.verifyDefaultTab();

        // 4. "Nereye Gideceksiniz?" alanına "Antalya" yazın ve en üsteki Antalya seçeneğine tıklayın
        homePage.enterDestination("Antalya");
        homePage.selectAntalyaOption();

        // 5. Tarih alanında Nisan'ın ilk haftası için bir haftalık aralık seçilir
        homePage.selectDate("Nisan 1, 2024");

        // 6. Yetişkin sayısı 1 artırılır ve değiştiği kontrol edilir
        homePage.increaseAdultCount();

        // 7. "Ara" butonu'nun görünürlüğü kontrol edilir ve tıklanır
        homePage.verifySearchButtonVisibility();
        homePage.clickSearchButton();

        // 8. Açılan URL içinde "antalya" kelimesini içerdiği kontrol edilir
        homePage.verifyUrlContainsAntalya();

        // 9. "Diğer Bölgeleri Göster" alanında rastgele tıklama yapılır ve sayı kaydedilir
        int selectedNumber = homePage.clickRandomLocation();

        // 10. Sayfanın altına kaydırma yapılır ve kaydedilen değer ile karşılaştırılır
        homePage.scrollToAntalyaHotels();
        homePage.verifySelectedNumberMatches(selectedNumber);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
