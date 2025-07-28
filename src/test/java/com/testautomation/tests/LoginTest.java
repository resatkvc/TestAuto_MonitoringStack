package com.testautomation.tests;

import com.testautomation.pages.LoginPage;
import com.testautomation.utils.MetricsExporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.time.Instant;

public class LoginTest extends BaseTest {
    
    @Test
    @Description("Successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        Instant startTime = Instant.now();
        String testName = "testSuccessfulLogin";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            
            // Verify successful login by checking URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/inventory.html"), 
                "Login should redirect to inventory page");
                
            // Record page load time
            MetricsExporter.recordPageLoadTime("inventory", 2.0);
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
        }
    }
    
    @Test
    @Description("Failed login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "invalid_password");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for invalid credentials");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Epic sadface"), 
            "Error message should contain expected text");
    }
    
    @Test
    @Description("Login with locked out user")
    @Severity(SeverityLevel.NORMAL)
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for locked out user");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Sorry, this user has been locked out"), 
            "Error message should indicate user is locked out");
    }
    
    @Test
    @Description("Login with problem user")
    @Severity(SeverityLevel.NORMAL)
    public void testProblemUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");
        
        // Verify successful login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/inventory.html"), 
            "Problem user should be able to login");
    }
} 