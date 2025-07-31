package com.testautomation.tests;

import com.testautomation.pages.LoginPage;
import com.testautomation.utils.MetricsExporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginTest extends BaseTest {
    
    @Test
    @Description("Successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
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
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("⏱️ Test duration: " + testName + " - " + duration + "s");
        }
    }
    
    @Test
    @Description("Failed login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testFailedLogin() {
        String testName = "testFailedLogin";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("invalid_user", "invalid_password");
            
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message should be displayed for invalid credentials");
            
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertTrue(errorMessage.contains("Epic sadface"), 
                "Error message should contain expected text");
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
                
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("⏱️ Test duration: " + testName + " - " + duration + "s");
        }
    }
    
    @Test
    @Description("Login with locked out user")
    @Severity(SeverityLevel.NORMAL)
    public void testLockedOutUser() {
        String testName = "testLockedOutUser";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("locked_out_user", "secret_sauce");
            
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message should be displayed for locked out user");
            
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertTrue(errorMessage.contains("Sorry, this user has been locked out"), 
                "Error message should indicate user is locked out");
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
                
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("⏱️ Test duration: " + testName + " - " + duration + "s");
        }
    }
    
    @Test
    @Description("Login with problem user")
    @Severity(SeverityLevel.NORMAL)
    public void testProblemUser() {
        String testName = "testProblemUser";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("problem_user", "secret_sauce");
            
            // Verify successful login
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/inventory.html"), 
                "Problem user should be able to login");
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
                
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("⏱️ Test duration: " + testName + " - " + duration + "s");
        }
    }
} 