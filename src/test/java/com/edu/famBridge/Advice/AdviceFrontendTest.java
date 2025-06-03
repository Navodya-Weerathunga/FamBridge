package com.edu.famBridge.Advice;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdviceFrontendTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver"); // Make sure chromedriver is in project root or provide full path
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/selectadvice");
    }


    @Test
    public void testSelectAdviceButtons() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // STEP 1: Click Pre-Pregnancy button
        WebElement prePregnancyBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Family Planning')]")));
        prePregnancyBtn.click();

        // STEP 2: Wait for navigation and textarea
        wait.until(ExpectedConditions.urlContains("/add?type=Family%20Planning"));
        WebElement adviceTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("textarea.atext")));

        // STEP 3: Fill advice text
        String originalAdvice = "This is a Selenium test advice for Pre-Pregnancy.";
        adviceTextArea.sendKeys(originalAdvice);
        Thread.sleep(1000);

        // STEP 4: Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.sb")));
        submitButton.click();
        Thread.sleep(1000);

        // STEP 5: Handle confirmation popup
        try {
            Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            confirmAlert.accept();
            Thread.sleep(1000);
        } catch (TimeoutException e) {
            fail("Confirmation alert was not shown after clicking submit.");
        }

        // STEP 6: Wait for redirection to /admin
        wait.until(ExpectedConditions.urlContains("/admin"));
        Thread.sleep(1000);

        // STEP 7: Locate the last row in the table (latest added advice)
        try {
            // Get the last row of the advice table (assuming newest advice is appended last)
            WebElement lastRow = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//table//tbody/tr)[last()]")));

            WebElement idCell = lastRow.findElement(By.xpath("td[1]"));
            String adviceId = idCell.getText().trim();
            Thread.sleep(1000);

            System.out.println("Found last advice row with ID: " + adviceId);

            // Scroll and click Update button with JS
            WebElement updateButton = lastRow.findElement(By.xpath(".//button[descendant::b[text()='Update']]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateButton);
            Thread.sleep(1000); // ensure layout stabilizes

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

            // STEP 7.5: Handle JS confirm popup
            try {
                Alert updateConfirm = driver.switchTo().alert();
                updateConfirm.accept();
                Thread.sleep(1000);
            } catch (NoAlertPresentException e) {
                fail("Update confirmation alert not shown.");
            }

            // STEP 8: Wait for redirect to update page
            wait.until(driver -> driver.getCurrentUrl().matches(".*/update/\\d+$"));
            Thread.sleep(1000);
            System.out.println("Navigated to update page for ID " + adviceId + ".");

            // STEP 9: Update the advice text
            WebElement updateTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("textarea.uatext")));
            updateTextarea.clear();
            String updatedAdvice = "Advice was updated via Selenium automation successfully.";
            updateTextarea.sendKeys(updatedAdvice);
            Thread.sleep(1000);

            // STEP 9.1: Set hidden ID input value explicitly (if exists)
            try {
                WebElement idHiddenInput = driver.findElement(By.cssSelector("input[type='hidden'][name='id']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", idHiddenInput, adviceId);
                System.out.println("Set hidden advice ID input to: " + adviceId);
            } catch (NoSuchElementException e) {
                System.out.println("No hidden ID input found. Please verify the update form includes advice ID.");
            }

            // STEP 10: Click Submit on update form
            WebElement updateSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.sbu")));

            updateSubmitBtn.click();

            try {
                Alert updateConfirm = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert text: " + updateConfirm.getText());
                updateConfirm.accept();
                Thread.sleep(1000);
            } catch (TimeoutException e) {
                fail("Update confirmation alert was not shown after clicking submit.");
            }

// Now it's safe to interact with the page or log button text



            // STEP 12: Verify redirection back to /admin
            wait.until(ExpectedConditions.urlContains("/admin"));
            assertTrue(driver.getCurrentUrl().contains("/admin"),
                    "Should return to /admin after updating advice.");
            Thread.sleep(2000);


            System.out.println("✅ Advice with ID " + adviceId + " updated and redirected to admin page successfully.");

        } catch (TimeoutException | NoSuchElementException | InterruptedException e) {
            fail("Could not complete update flow. Reason: " + e.getMessage());
            System.out.println("Could not complete update flow. Reason: " + e.getMessage());
        }
    }


    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
