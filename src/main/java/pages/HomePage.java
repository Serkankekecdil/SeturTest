package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSetur() {
        driver.get("https://www.setur.com.tr/");
    }

    public void verifySeturUrl() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("setur.com.tr")) {
            System.out.println("Setur URL is correct.");
        } else {
            System.out.println("Setur URL is incorrect.");
        }
    }

    public void verifyDefaultTab() {
        WebElement hotelTab = driver.findElement(By.xpath("//span[@class='Tab__TabText-sc-1gyyeis-2 czbyok']"));
        if (hotelTab.getText().equals("Otel")) {
            System.out.println("Otel tab is default.");
        } else {
            System.out.println("Otel tab is not default.");
        }
        try {
            Thread.sleep(Long.parseLong("2000"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterDestination(String destination) {
        driver.findElement(By.xpath("//div[@class='styled__ResponsiveListingSearchLocationContainer-sc-9a6dl1-10 ivrQzo']")).sendKeys(destination);

    }

    public void selectAntalyaOption() {
        List<WebElement> options = driver.findElements(By.xpath("//div[@id='autocomplete-list']/div"));
        if (!options.isEmpty()) {
            options.get(0).click();
        }
    }

    public void selectDate(String date) {
        driver.findElement(By.xpath("//div[@class='ResponsiveListingSearchStyle__ResponsiveSearchDrawerItemText-sc-15kpnly-2 dAcipy'")).clear();
        driver.findElement(By.xpath("//div[@class='ResponsiveListingSearchStyle__ResponsiveSearchDrawerItemText-sc-15kpnly-2 dAcipy'")).sendKeys(date);
    }

    public void increaseAdultCount() {
        driver.findElement(By.xpath("//div[@class='ResponsiveListingSearchStyle__ResponsiveSearchDrawerItemText-sc-1kumzgx-2 kWHsTA'")).click();
        driver.findElement(By.xpath("//div[@class='CounterStyle__CounterButton-sc-1oucm61-1 bTTTYp'")).click();
        driver.findElement(By.xpath("//div[@class='styled__StyledButton-sc-1i7jkmi-0 iRHtqU ResponsiveListingSearchStyle__ResponsiveSearchDrawerButton-sc-1kumzgx-31 ePdyXS'")).click();
    }

    public void verifySearchButtonVisibility() {
        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Ara']"));
        if (searchButton.isDisplayed()) {
            System.out.println("Search button is visible.");
        } else {
            System.out.println("Search button is not visible.");
        }
    }

    public void clickSearchButton() {
        driver.findElement(By.xpath("//button[text()='Ara']")).click();
    }

    public void verifyUrlContainsAntalya() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        if (currentUrl.contains("antalya")) {
            System.out.println("URL contains 'antalya'.");
        } else {
            System.out.println("URL does not contain 'antalya'.");
        }
    }

    public int clickRandomLocation() {
        List<WebElement> locationButtons = driver.findElements(By.xpath("//button[@data-track-action='LocationFilter']"));
        int randomIndex = new Random().nextInt(locationButtons.size());
        WebElement randomLocationButton = locationButtons.get(randomIndex);
        String buttonText = randomLocationButton.getText();
        int selectedNumber = Integer.parseInt(buttonText.substring(1, buttonText.length() - 1));
        randomLocationButton.click();
        return selectedNumber;
    }

    public void scrollToAntalyaHotels() {
        WebElement antalyaHotelsSection = driver.findElement(By.xpath("//h2[text()='Antalya Otelleri ve En Uygun Antalya Otel Fiyatları']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", antalyaHotelsSection);
    }

    public void verifySelectedNumberMatches(int selectedNumber) {
        WebElement antalyaHotelsSection = driver.findElement(By.xpath("//h2[text()='Antalya Otelleri ve En Uygun Antalya Otel Fiyatları']"));
        String sectionText = antalyaHotelsSection.getText();
        int startIndex = sectionText.indexOf("(");
        int endIndex = sectionText.indexOf(")");
        String numberText = sectionText.substring(startIndex + 1, endIndex);
        int displayedNumber = Integer.parseInt(numberText);

        if (selectedNumber == displayedNumber) {
            System.out.println("Selected number matches displayed number: " + selectedNumber);
        } else {
            System.out.println("Selected number does not match displayed number.");
        }
    }
}
