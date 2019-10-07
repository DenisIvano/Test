import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Testy {
    WebDriver driver;
//firstbranch commit---1
    //push to master-1

    By userNameInput = By.xpath("//input[@id='login-form-username']");
    By passwordInput = By.xpath("//input[@id='login-form-password']");
    By loginButton = By.xpath("//*[@id='login-form-submit']");
    By createButton = By.xpath("//a[@class='aui-button aui-button-primary aui-style create-issue ']");
    By issueTypeDropdown = By.xpath("//*[@class='icon aui-ss-icon noloading drop-menu']");
    By issueType = By.xpath("//input[@class='text aui-ss-field ajs-dirty-warning-exempt'][@id='issuetype-field']");
    String userName = "divanov";
    String password = "x000000X";

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
        wait.until(ExpectedConditions.elementToBeClickable(issueType));
        this.driver.findElement(issueType).sendKeys("Bug");
        assertTrue(this.driver.findElement(issueTypeDropdown).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        // Close the driver
        this.driver.quit();
    }

}