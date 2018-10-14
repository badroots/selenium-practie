package space307.practise.selenium.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class FirstLessonTest {
    private WebDriver driver;

    @BeforeAll
    static void prepareTest() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void basicSearchTest() {

        driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        driver.findElement(By.id("lst-ib")).sendKeys("Space 307");

        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);

        waitForSpecialTitle(
                "Space 307",
                "Title does not meet expectations",
                15
        );

        assertEquals("Space 307 - Google Search", driver.getTitle());

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    private boolean waitForSpecialTitle(String titlePart, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.titleContains(titlePart));
    }

}
