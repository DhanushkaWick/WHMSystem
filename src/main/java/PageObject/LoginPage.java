package PageObject;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractComponent {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement email_ele;
    @FindBy(xpath = "//input[@id='password']")
    WebElement password_ele;
    @FindBy(xpath = "//button[@id='login-btn']")
    WebElement loginButton_ele;
    @FindBy(xpath="(//div[@aria-label='authentication failed'])[1]")
    WebElement authenticationFailed_ele;
    @FindBy(xpath="//div[@id='cdk-overlay-0']")
    WebElement modalLable_ele;
    @FindBy(xpath = "//button[.//span[normalize-space()='Clear Session']]")
    WebElement clearSession_ele;
    @FindBy(xpath="//button[.//span[normalize-space()='Cancel']]")
    WebElement cancelButton_ele;
    @FindBy(xpath="//span[contains(text(),'Dashboard')]")
    WebElement dashboardLink_ele;





    public void validLogin(String email, String password) {
        email_ele.clear();
        password_ele.clear();
        email_ele.sendKeys(email);
        password_ele.sendKeys(password);
        loginButton_ele.click();
    }
    public void invalidLogin(String email, String password) {
        email_ele.sendKeys(email);
        password_ele.sendKeys(password);
        loginButton_ele.click();
    }

    public String getErrorMessage() {
        waitForWEbElementToAppear(authenticationFailed_ele);
        return authenticationFailed_ele.getText();
    }


    public void handleModal() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(modalLable_ele)
            );

            System.out.println("Modal appeared.");

            wait.until(
                    ExpectedConditions.elementToBeClickable(clearSession_ele)
            );

            clearSession_ele.click();

            wait.until(
                    ExpectedConditions.invisibilityOf(modalLable_ele)
            );

            System.out.println("Modal handled successfully.");

        } catch (TimeoutException e) {

            System.out.println(
                    "No session modal appeared."
            );
        }
    }


//    public void handleModal() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // Wait until the modal is visible
//            wait.until(ExpectedConditions.visibilityOf(modalLable_ele));
//            System.out.println("Modal appeared. Attempting to click clearSessionsButton.");
//
//            // Ensure the button is clickable
//            wait.until(ExpectedConditions.elementToBeClickable(clearSession_ele));
//            clearSession_ele.click();
//            wait.until(ExpectedConditions.invisibilityOf(modalLable_ele));
//            System.out.println("Modal handled successfully.");
//        } catch (Exception e) {
//            System.out.println("Modal did not appear or could not be handled: " + e.getMessage());
//        }
//    }

//    public String getSuccessLoginDashboard() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
//        wait.until(ExpectedConditions.visibilityOf(dashboardLink_ele));
//        //waitForWebElementToAppear(successMessage);
//        return dashboardLink_ele.getText();
//    }

    public String getSuccessLoginDashboard() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class,'hide-menu') and normalize-space()='Dashboard']")
        ));

        System.out.println("Dashboard found.");

        return dashboardLink_ele.getText();
    }

}