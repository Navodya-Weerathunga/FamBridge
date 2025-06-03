package com.edu.famBridge.Suggestion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class SeleniumTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // Explicit wait to replace Thread.sleep()
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/suggestion");
    }

    @Test
    public void testAddSuggestionAndRedirect() {
        // Find the email input and fill it
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));
        emailInput.clear();
        emailInput.sendKeys("amadakalubowila@gmail.com");

        // Fill in the suggestion text
        WebElement suggestionInput = driver.findElement(By.cssSelector("textarea"));
        suggestionInput.sendKeys("This is a test suggestion from Selenium.");

        // Click the Add Suggestion button
        WebElement addButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        addButton.click();

        // Wait for the page to redirect (check for the /admin-complaints URL)
        wait.until(ExpectedConditions.urlContains("/admin-complaints"));

        // Assert that the URL has changed to include /admin-complaints
        assertTrue(driver.getCurrentUrl().contains("/admin-complaints"));

        // Optional: Wait for the table to be visible and check if the new suggestion appears in the table
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        assertTrue(table.getText().contains("This is a test suggestion from Selenium."));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
