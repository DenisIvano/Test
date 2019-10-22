import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Testy {
    WebDriver driver;



    By userNameInput = By.xpath("//input[@id='login-form-username']");
    By passwordInput = By.xpath("//input[@id='login-form-password']");
    By loginButton = By.xpath("//*[@id='login-form-submit']");
    By createButton = By.xpath("//a[@class='aui-button aui-button-primary aui-style create-issue ']");
    By issueTypeDropdown = By.xpath("//*[@class='icon aui-ss-icon noloading drop-menu']");
    By issueType = By.xpath("//*[@id='issuetype-field']");
    By summaryInput = By.xpath("//input[@id='summary']");
    String userName = "divanov";
    String password = "x000000X";
    By descriptionInput = By.xpath("//*[@id='description']");
    By summary = By.xpath("//*[@for='summary']");

    @BeforeTest
    public void setUp() {
        // Fix for - The path to the driver executable must be set by the webdriver.chrome.driver system property
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        // Create a new instance of the Firefox driver
        this.driver = new ChromeDriver();
    }

    @Test
    public void firstTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        this.driver.get("https://jira.provectus.com/login.jsp");
        this.driver.findElement(userNameInput).sendKeys(userName);
        this.driver.findElement(passwordInput).sendKeys(password);
        this.driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        this.driver.findElement(createButton).click();
        enterText(issueType, "Task", 3, 5);
        enterText(summaryInput, "Automatic Test Create Issue", 3, 5);
        enterText(descriptionInput, "Automatic Test Create Issue", 3, 5);
        assertTrue(this.driver.findElement(issueTypeDropdown).isDisplayed());
    }


    private void enterText(By element, String text, int retry, int timeoutSeconds) throws InterruptedException {
        for (int i = retry; i > 0; i--) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
                driver.findElement(element).sendKeys(text);
                break;
            } catch (ElementNotInteractableException ex) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
                    driver.findElement(element).sendKeys(text);
                    break;
                } catch (ElementNotInteractableException  ex2) {
                    continue;
                }
            }
        }
    }




    @AfterTest
    public void tearDown() {
        // Close the driver
        this.driver.quit();
    }

}