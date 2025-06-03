package com.edu.famBridge.appointment;


import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
public class AppointmentFlowTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/appointments");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Set localStorage items
        js.executeScript("localStorage.setItem('midwifeId', '1');");


        // Refresh the page to apply the localStorage settings
        driver.navigate().refresh();
    }

    @Test
    public void testCompleteAppointmentFlow() throws InterruptedException {
        Thread.sleep(1500);
        // Step 1: Schedule an appointment
        WebElement scheduleBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("addschedule")));
        scheduleBtn.click();

        Thread.sleep(1000);
        // Wait for the Midwife ID and Midwife Name fields to be auto-filled (no need to interact with them)
        WebElement midwifeIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("midwifeId")));
        assertTrue(midwifeIdField.isDisplayed(), "Midwife ID field is not displayed");

        WebElement midwifeNameField = driver.findElement(By.id("midwifeName"));
        assertTrue(midwifeNameField.isDisplayed(), "Midwife Name field is not displayed");

        WebElement dateInput = driver.findElement(By.id("appointmentDate"));
        WebElement startTime = driver.findElement(By.id("startTime"));
        WebElement endTime = driver.findElement(By.id("endTime"));
        WebElement slots = driver.findElement(By.id("appointmentsPerDay"));

        dateInput.sendKeys("2025-07-22");
        Thread.sleep(500);
        startTime.clear();
        startTime.sendKeys("09:00AM");
        startTime.sendKeys(Keys.TAB);
        Thread.sleep(500);

        endTime.clear();
        endTime.sendKeys("10:00AM");
        endTime.sendKeys(Keys.TAB);
        Thread.sleep(500);

        slots.clear();
        slots.sendKeys("4");
        Thread.sleep(500);

        Select areaSelect = new Select(driver.findElement(By.id("area")));
        areaSelect.selectByIndex(1);
        Thread.sleep(500);

        WebElement workTypeRadio = driver.findElement(By.cssSelector("input[type='radio'][value='Field']"));
        workTypeRadio.click();
        Thread.sleep(500);

        driver.findElement(By.className("submit")).click();
        Thread.sleep(1000);

        try {
            // Wait for the alert to appear and switch to it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Optionally print the alert text
            System.out.println("Alert found: " + alert.getText());

            // Accept or dismiss the alert
            alert.accept(); // or alert.dismiss();
        } catch (TimeoutException e) {
            // No alert appeared, continue normally
            System.out.println("No alert present.");
        }

        // Step 3: Success Page Verification
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("subheadingcancel1")));
        String successText = successMessage.getText();
        assertTrue(successText.contains("You have submitted Availability Successfully!"), "Success message is incorrect");

        // Verify that the "Edit Availability" button is displayed and enabled
        WebElement editButton = driver.findElement(By.id("available1"));
        assertTrue(editButton.isDisplayed(), "Edit button is not displayed");
        assertTrue(editButton.isEnabled(), "Edit button is not enabled");

        // Verify that the "Delete Availability" button is displayed and enabled
        WebElement deleteButton = driver.findElement(By.id("available2"));
        assertTrue(deleteButton.isDisplayed(), "Delete button is not displayed");
        assertTrue(deleteButton.isEnabled(), "Delete button is not enabled");

        // Verify that the "Done" button is displayed and enabled
        WebElement doneButton = driver.findElement(By.id("available3"));
        assertTrue(doneButton.isDisplayed(), "Done button is not displayed");
        assertTrue(doneButton.isEnabled(), "Done button is not enabled");


        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        Thread.sleep(1000);


// Wait and verify the redirection to the edit appointments page
// If the appointment ID is dynamic, you might need to generalize this check
        // Wait for the URL to match the expected pattern
        wait.until(ExpectedConditions.urlMatches("http://localhost:3000/edit-appointments/\\d+/1"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.matches("http://localhost:3000/edit-appointments/\\d+/1"),
                "Did not navigate to edit appointments page");

// Wait for and interact with form fields safely
        WebElement startTimeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startTime")));
        WebElement endTimeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endTime")));
        WebElement appointmentsPerDayField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appointmentsPerDay")));

// Clear and enter values
        startTimeField.clear();
        startTimeField.sendKeys("10:00AM");
        startTimeField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        endTimeField.clear();
        endTimeField.sendKeys("18:00");
        endTimeField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        appointmentsPerDayField.clear();
        appointmentsPerDayField.sendKeys("70");
        appointmentsPerDayField.sendKeys(Keys.TAB);
        Thread.sleep(500);

// Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("submit")));
        submitButton.click();
        Thread.sleep(1000);

// Handle unexpected alert if it appears
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText()); // Optional
            alert.accept(); // Accepts the alert
        } catch (TimeoutException e) {
            System.out.println("No alert appeared after submitting the form.");
        }


        // Step 6: Back to Success Page
        WebElement updatedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("subheadingcancel1")));
        assertTrue(updatedMessage.getText().contains("Successfully updated your schedule!") ||
                updatedMessage.getText().contains("You have submitted Availability Successfully!"));


        // Verify that the "Edit Availability" button is displayed and enabled
        WebElement editedButton = driver.findElement(By.id("available1"));
        assertTrue(editedButton.isDisplayed(), "Edit button is not displayed");
        assertTrue(editedButton.isEnabled(), "Edit button is not enabled");

        // Verify that the "Delete Availability" button is displayed and enabled
        WebElement deletedButton = driver.findElement(By.id("available2"));
        assertTrue(deletedButton.isDisplayed(), "Delete button is not displayed");
        assertTrue(deletedButton.isEnabled(), "Delete button is not enabled");

        // Verify that the "Done" button is displayed and enabled
        WebElement donebutton = driver.findElement(By.id("available3"));
        assertTrue(donebutton.isDisplayed(), "Done button is not displayed");
        assertTrue(donebutton.isEnabled(), "Done button is not enabled");


        wait.until(ExpectedConditions.elementToBeClickable(donebutton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", donebutton);

        Thread.sleep(500);
        wait.until(ExpectedConditions.urlContains("/appointments"));
        Thread.sleep(1000);


        driver.get("http://localhost:3000/");
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        // Final check: Schedule table should appear or fallback text
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 10; i++) {
            js.executeScript("window.scrollBy(0, 200);"); // Scroll down 200 pixels
            Thread.sleep(1000); // Wait 0.5 seconds between scrolls for a smooth effect
        }

    }


    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000); // Short delay to observe result
        if (driver != null) {
            driver.quit();
        }
    }
}
