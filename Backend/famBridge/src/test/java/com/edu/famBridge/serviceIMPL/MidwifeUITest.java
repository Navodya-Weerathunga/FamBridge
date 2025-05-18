package com.edu.famBridge.serviceIMPL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MidwifeUITest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.get("http://localhost:3000/MidwifeLogin");
    }

    @Test
    public void testValidMidwifeLogin() throws InterruptedException {
        try {
            // --- LOGIN ---
            driver.findElement(By.id("typeEmail")).sendKeys("dewindi18weerathunga@gmail.com");
            driver.findElement(By.id("typePassword")).sendKeys("Midwife@123");
            driver.findElement(By.xpath("//button[text()='Log In']")).click();

            // --- REDIRECTION WAIT ---
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/MidwifeHome"),
                    ExpectedConditions.urlContains("/SuperMidwifeHome")
            ));

            // --- REQUEST DROPDOWN ---
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("requestDropdown")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

            // --- CLICK MARRIED COUPLE REQUEST ---
            WebElement coupleLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("marriedRequest")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", coupleLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", coupleLink);

            // --- WAIT FOR REQUEST PAGE LOAD ---
            wait.until(ExpectedConditions.urlContains("/MarriedCoupleRequests"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));

            // --- FIND REQUEST ID 8 ---
            WebElement registerButton = null;
            for (WebElement row : driver.findElements(By.xpath("//table/tbody/tr"))) {
                String requestId = row.findElement(By.xpath("th")).getText().trim();
                if ("8".equals(requestId)) {
                    try {
                        registerButton = row.findElement(By.xpath(".//button[text()='Register']"));
                        break;
                    } catch (NoSuchElementException e) {
                        System.out.println("Register button not found for requestId 8.");
                    }
                }
            }

            // --- IF REGISTER BUTTON FOUND ---
            if (registerButton != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
                try {
                    registerButton.click();
                } catch (Exception e) {
                    System.out.println("Standard click failed: " + e.getMessage());
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
                }

                // --- REGISTRATION FORM LOADED ---
                wait.until(ExpectedConditions.urlMatches(".*\\/MarriedCoupleRegistration\\/8$"));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dob")));

                // --- WIFE DETAILS ---
                ((JavascriptExecutor) driver).executeScript("document.getElementById('dob').value = '1999-05-10';");

                Select midwifeSelect = new Select(driver.findElement(By.id("assignMidwife")));
                boolean midwifeFound = false;
                for (WebElement option : midwifeSelect.getOptions()) {
                    if (option.getText().contains("Sumedha Rathnayake")) {
                        option.click();
                        midwifeFound = true;
                        break;
                    }
                }
                if (!midwifeFound) System.out.println("Midwife not found in dropdown.");
                driver.findElement(By.id("occupation")).sendKeys("Nurse");
                new Select(driver.findElement(By.id("educationLevel"))).selectByVisibleText("Degree");

                // --- HUSBAND DETAILS ---
                driver.findElement(By.id("husbandFirstName")).sendKeys("Gayan");
                driver.findElement(By.id("husbanLastName")).sendKeys("Samarasinghe");
                driver.findElement(By.id("husbandNic")).sendKeys("902304567V");
                ((JavascriptExecutor) driver).executeScript("document.getElementById('husbandDob').value = '1988-08-15';");
                new Select(driver.findElement(By.id("husbandEducationLevel"))).selectByVisibleText("Degree");
                driver.findElement(By.id("husbandOccupation")).sendKeys("Engineer");
                driver.findElement(By.id("husbandContactNo")).sendKeys("0771234567");

                // --- MARRIAGE DETAILS ---
                ((JavascriptExecutor) driver).executeScript("document.getElementById('marriedDate').value = '2015-06-20';");
                driver.findElement(By.id("marriageCertificateNo")).sendKeys("MC123456");
                driver.findElement(By.id("marriagePlace")).sendKeys("Battaramulla");

                // --- WAIT FOR SPINNER/OVERLAY TO DISAPPEAR ---
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay, .spinner")));
                Thread.sleep(500);

                // --- FINAL REGISTER BUTTON ---
                WebElement registerBtn = driver.findElement(By.xpath("//button[normalize-space(text())='Register']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerBtn);

                try {
                    new Actions(driver)
                            .moveToElement(registerBtn)
                            .pause(Duration.ofMillis(100))
                            .click()
                            .perform();
                    System.out.println("Successfully clicked Register via Actions.");
                } catch (Exception actionEx) {
                    System.out.println("Actions click failed: " + actionEx.getMessage());
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerBtn);
                        System.out.println("Successfully clicked Register via JavaScript.");
                    } catch (Exception jsEx) {
                        System.out.println("JavaScript click also failed: " + jsEx.getMessage());
                        throw jsEx;
                    }
                }

                // give a bit of time for the alert to show up (max 60 seconds)
                Alert alert;
                String alertText;
                try {
                    alert = new WebDriverWait(driver, Duration.ofSeconds(60))
                            .until(ExpectedConditions.alertIsPresent());
                    alertText = alert.getText();
                    System.out.println("Alert Text: " + alertText);
                    alert.accept();
                } catch (TimeoutException te) {
                    fail("Expected an alert after clicking Register, but none appeared.");
                    return; // unreachable, but keeps compiler happy
                }

                // fail if the alert is an error
                if (alertText.contains("An Error Occurred")) {
                    fail("Registration failed with error: " + alertText);
                }

                // --- ASSERT REDIRECTION TO /MarriedCoupleRequests ---
                try {
                    wait.until(ExpectedConditions.urlContains("/MarriedCoupleRequests"));
                    String currentUrl = driver.getCurrentUrl();
                    assertTrue(currentUrl.contains("/MarriedCoupleRequests"),
                            "Expected redirection to '/MarriedCoupleRequests', but got: " + currentUrl);
                    System.out.println("Successfully redirected to: " + currentUrl);
                    Thread.sleep(3000);
                } catch (TimeoutException te) {
                    fail("Redirection to /MarriedCoupleRequests failed.");
                }

            } else {
                assert false : "Register button for requestId 8 not found in the table.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Test failed due to exception: " + e.getMessage();
        }
    }

    @Test
    public void testInvalidLoginShowsAlert() {
        WebElement emailField = driver.findElement(By.id("typeEmail"));
        WebElement passwordField = driver.findElement(By.id("typePassword"));
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Log In']"));

        emailField.sendKeys("dewindi18weerathunga@gmail.com");
        passwordField.sendKeys("wrongpassword");

        loginButton.click();

        // Handle JS alert (from alert(err.message))
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("Invalid") || alertText.contains("not found"));
        alert.accept();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
