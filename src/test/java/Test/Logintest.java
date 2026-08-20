package Test;

import PageObject.LoginPage;
import TestComponent.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Logintest extends BaseTest {

    public LoginPage loginPage;
    String invalidUser;
    String invalidPass;
    String validUser;
    String validPass;

    public Logintest () throws IOException {
        super();

}
@BeforeClass
    public void setUp() throws IOException {
    driver = initializeDriver();
    loginPage = new LoginPage(driver);

    invalidUser = prop.getProperty("invalidUser");
    invalidPass = prop.getProperty("invalidPass");
    validUser = prop.getProperty("validUser");
    validPass = prop.getProperty("validpass");


}
@Test(priority = 1)
    public void verifyUnSuccessLogin() throws InterruptedException {
        loginPage.invalidLogin(invalidUser,invalidPass);
        Thread.sleep(3000);
    Assert.assertEquals(loginPage.getErrorMessage(),"authentication failed");

}
    @Test(priority = 2)
    public void verifySuccessLogin() throws InterruptedException {
        System.out.println(validUser);
        System.out.println(validPass);
        loginPage.validLogin(validUser,validPass);
        Thread.sleep(3000);
    loginPage.handleModal();
        System.out.println("modal hadel");
        Thread.sleep(3000);
        Assert.assertEquals(loginPage.getSuccessLoginDashboard(),"Dashboard");
        System.out.println("mes");


    }

}
