package com.edu.famBridge.Card;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PregnancyCardPageOneTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.get("http://localhost:3000/pregnancy-card-page-one/452620");

            fillPregnancyCardPageOne(driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("/pregnancy-card-page-two/"));

            fillPregnancyCardPageTwo(driver);

            wait.until(ExpectedConditions.urlContains("/pregnancy-card-page-three/"));

            fillPregnancyCardPageThree(driver);

            wait.until(ExpectedConditions.urlContains("/pregnancy-card-page-four/"));

            fillPregnancyCardPageFour(driver);

            Thread.sleep(10000);
            System.out.println("All four pages submitted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void safeClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private static void clearAndTypeDate(WebDriver driver, String fieldName, String value) throws InterruptedException {
        WebElement input = driver.findElement(By.name(fieldName));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                input, value
        );
        Thread.sleep(300);
    }

    private static void clearAndTypeText(WebDriver driver, String fieldName, String value) throws InterruptedException {
        WebElement input = driver.findElement(By.name(fieldName));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                input, value
        );
        Thread.sleep(300);
    }

    private static void fillPregnancyCardPageOne(WebDriver driver) throws InterruptedException {
        driver.findElement(By.name("pregnancyRecordNo")).sendKeys("452620");
        new Select(driver.findElement(By.name("riskType"))).selectByVisibleText("Moderate");
        new Select(driver.findElement(By.name("bloodGroup"))).selectByVisibleText("A+");

        driver.findElement(By.name("allergies")).sendKeys("Peanuts");
        driver.findElement(By.name("fieldClinicName")).sendKeys("Health Field Clinic");
        driver.findElement(By.name("hospitalClinicName")).sendKeys("City Hospital");
        driver.findElement(By.name("consultantObstetricians")).sendKeys("Dr. Smith");
        driver.findElement(By.name("antenatalRiskConditions")).sendKeys("None");

        safeClick(driver, By.cssSelector("input[name='consanguinity'][value='true']"));
        safeClick(driver, By.cssSelector("input[name='rubellaImmunization'][value='false']"));
        safeClick(driver, By.cssSelector("input[name='prePregnancyScreening'][value='true']"));
        safeClick(driver, By.cssSelector("input[name='preconceptionalFolicAcid'][value='false']"));
        safeClick(driver, By.cssSelector("input[name='historyOfSubfertility'][value='false']"));
        safeClick(driver, By.cssSelector("input[name='plannedPregnancy'][value='true']"));

        driver.findElement(By.name("lastUsedFamilyPlanningMethods")).sendKeys("Pills");

        clearAndTypeDate(driver, "visitingDate", "2025-05-17");
        clearAndTypeDate(driver, "LRMP", "2025-01-01");
        clearAndTypeDate(driver, "EDD", "2025-10-08");
        clearAndTypeDate(driver, "dateOf40Weeks", "2025-10-15");
        clearAndTypeDate(driver, "usCorrectedEdd", "2025-10-10");
        clearAndTypeDate(driver, "gestationPeriod", "2025-05-17");

        driver.findElement(By.name("gravidity")).sendKeys("G");
        driver.findElement(By.name("ageOfYoungestChild")).sendKeys("3");
        driver.findElement(By.name("poaAtScan")).sendKeys("28 weeks");
        driver.findElement(By.name("poaAtRegistration")).sendKeys("12 weeks");

        safeClick(driver, By.cssSelector("input[name='contraceptiveMethod'][value='true']"));
        driver.findElement(By.name("contraceptiveDetails")).sendKeys("Oral pills");

        WebElement nextButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-primary.short-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextButton);
        Thread.sleep(500);
        safeClick(driver, By.cssSelector("button.btn-primary.short-btn"));

        Thread.sleep(10000);
    }

    private static void fillPregnancyCardPageTwo(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.name("wifeAge")).sendKeys("28");
        driver.findElement(By.name("wifeHighestEducation")).sendKeys("Graduate");
        driver.findElement(By.name("wifeOccupation")).sendKeys("Teacher");
        driver.findElement(By.name("husbandAge")).sendKeys("32");
        driver.findElement(By.name("husbandHighestEducation")).sendKeys("Graduate");
        driver.findElement(By.name("husbandOccupation")).sendKeys("Engineer");

        safeClick(driver, By.xpath("//input[@name='diabetesMellitus' and @value='true']"));
        safeClick(driver, By.xpath("//input[@name='hypertension' and @value='false']"));
        safeClick(driver, By.xpath("//input[@name='haematologicalDiseases' and @value='false']"));
        safeClick(driver, By.xpath("//input[@name='twinOrMultiplePregnancies' and @value='false']"));

        driver.findElement(By.name("others")).sendKeys("None");

        String[] medicalConditions = {
                "diabetes", "hypertensionMedHis", "cardiacDiseases", "renalDiseases",
                "hepaticDiseases", "psychiatricIllnesses", "epilepsy", "malignancies",
                "haematologicalDiseasesMedHis", "tuberculosis", "thyroidDiseases", "bronchialAsthma"
        };

        for (String condition : medicalConditions) {
            safeClick(driver, By.xpath("//input[@name='" + condition + "' and @value='false']"));
        }

        driver.findElement(By.name("pregnancy")).sendKeys("2");
        driver.findElement(By.name("antenatalComplications")).sendKeys("None");
        driver.findElement(By.name("placeOfDelivery")).sendKeys("Hospital");
        driver.findElement(By.name("modeOfDelivery")).sendKeys("Normal");
        driver.findElement(By.name("outcome")).sendKeys("Alive");
        driver.findElement(By.name("brithWeight")).sendKeys("3.2");
        driver.findElement(By.name("postnatalComplications")).sendKeys("None");
        driver.findElement(By.name("sexOfChild")).sendKeys("Female");
        driver.findElement(By.name("ageOfChild")).sendKeys("1");

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next')]")));
        while (!nextButton.isEnabled()) {
            Thread.sleep(500);
            nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextButton);
        Thread.sleep(500);

        try {
            nextButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
        }

        Thread.sleep(500);
    }

    private static void fillPregnancyCardPageThree(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("poa")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("height")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("weight")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("sfhDate")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fundalHeight")));

        driver.findElement(By.name("poa")).clear();
        driver.findElement(By.name("poa")).sendKeys("28");
        driver.findElement(By.name("height")).clear();
        driver.findElement(By.name("height")).sendKeys("165");
        driver.findElement(By.name("weight")).clear();
        driver.findElement(By.name("weight")).sendKeys("65");

        clearAndTypeDate(driver, "sfhDate", "2025-05-17");

        driver.findElement(By.name("fundalHeight")).clear();
        driver.findElement(By.name("fundalHeight")).sendKeys("30");

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next')]")));
        while (!nextButton.isEnabled()) {
            Thread.sleep(500);
            nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextButton);
        Thread.sleep(500);

        try {
            nextButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
        }

        Thread.sleep(500);
    }

    private static void fillPregnancyCardPageFour(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("intendedHospital")));

        clearAndTypeText(driver, "intendedHospital", "City Hospital");
        clearAndTypeText(driver, "modeOfTransport", "Ambulance");
        clearAndTypeText(driver, "averageCost", "500");
        clearAndTypeText(driver, "distanceFromHome", "10");
        clearAndTypeText(driver, "timeTakenToReach", "30 mins");

        clearAndTypeText(driver, "intendedHospitalEmergency", "County Emergency");
        clearAndTypeText(driver, "modeOfTransportEmergency", "Private Car");
        clearAndTypeText(driver, "averageCostEmergency", "700");
        clearAndTypeText(driver, "distanceFromHomeEmergency", "12");
        clearAndTypeText(driver, "timeTakenToReachEmergency", "25 mins");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Submit')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);
        Thread.sleep(500);

        try {
            submitButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }

        Thread.sleep(500);
    }
}
