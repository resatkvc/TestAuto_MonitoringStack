package com.testautomation.tests;

import com.testautomation.pages.LoginPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.MetricsExporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductTest extends BaseTest {
    
    @Test
    @Description("Verify products are displayed after login")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductsDisplayed() {
        String testName = "testProductsDisplayed";
        
        System.out.println("🚀 Test başlıyor: " + testName);
        
        try {
            MetricsExporter.recordTestExecution(testName);
            System.out.println("✅ Test execution recorded");
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            System.out.println("✅ Login completed");
            
            ProductPage productPage = new ProductPage(driver);
            int productCount = productPage.getProductCount();
            System.out.println("✅ Product count: " + productCount);
            
            Assert.assertTrue(productCount > 0, "Products should be displayed");
            Assert.assertEquals(productCount, 6, "Should display exactly 6 products");
            
            // Record page load time
            MetricsExporter.recordPageLoadTime("products", 1.5);
            System.out.println("✅ Page load time recorded");
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
            System.out.println("✅ Test success recorded");
            
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("✅ Test duration recorded: " + duration + "s");
        }
    }
    
    @Test
    @Description("Add product to cart and verify cart badge")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCart() {
        String testName = "testAddProductToCart";
        
        System.out.println("🚀 Test başlıyor: " + testName);
        
        try {
            MetricsExporter.recordTestExecution(testName);
            System.out.println("✅ Test execution recorded");
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            System.out.println("✅ Login completed");
            
            ProductPage productPage = new ProductPage(driver);
            
            // Initially cart should be empty
            String initialCartCount = productPage.getCartItemCount();
            Assert.assertEquals(initialCartCount, "0", "Cart should be empty initially");
            
            // Add first product to cart
            productPage.addProductToCart(0);
            System.out.println("✅ Product added to cart");
            
            // Verify cart badge is displayed and count is 1
            Assert.assertTrue(productPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
            String cartCount = productPage.getCartItemCount();
            Assert.assertEquals(cartCount, "1", "Cart should contain 1 item");
            
            // Record page load time
            MetricsExporter.recordPageLoadTime("cart", 2.0);
            System.out.println("✅ Page load time recorded");
            
            // Record successful test
            MetricsExporter.recordTestSuccess(testName);
            System.out.println("✅ Test success recorded");
            
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = getTestDuration();
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("✅ Test duration recorded: " + duration + "s");
        }
    }
    
    @Test
    @Description("Add multiple products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testAddMultipleProductsToCart() {
        String testName = "testAddMultipleProductsToCart";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            
            ProductPage productPage = new ProductPage(driver);
            
            // Add first two products to cart
            productPage.addProductToCart(0);
            productPage.addProductToCart(1);
            
            // Verify cart count is 2
            String cartCount = productPage.getCartItemCount();
            Assert.assertEquals(cartCount, "2", "Cart should contain 2 items");
            
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
    @Description("Remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveProductFromCart() {
        String testName = "testRemoveProductFromCart";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            
            ProductPage productPage = new ProductPage(driver);
            
            // Add product to cart
            productPage.addProductToCart(0);
            Assert.assertEquals(productPage.getCartItemCount(), "1", "Cart should contain 1 item");
            
            // Remove product from cart
            productPage.removeProductFromCart(0);
            
            // Verify cart is empty
            String cartCount = productPage.getCartItemCount();
            Assert.assertEquals(cartCount, "0", "Cart should be empty after removal");
            
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
    @Description("Verify product names are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductNamesDisplayed() {
        String testName = "testProductNamesDisplayed";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            
            ProductPage productPage = new ProductPage(driver);
            
            // Check first product name
            String firstProductName = productPage.getProductName(0);
            Assert.assertNotNull(firstProductName, "Product name should not be null");
            Assert.assertFalse(firstProductName.isEmpty(), "Product name should not be empty");
            
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
    @Description("Verify product prices are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductPricesDisplayed() {
        String testName = "testProductPricesDisplayed";
        
        try {
            MetricsExporter.recordTestExecution(testName);
            
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            
            ProductPage productPage = new ProductPage(driver);
            
            // Check first product price
            String firstProductPrice = productPage.getProductPrice(0);
            Assert.assertNotNull(firstProductPrice, "Product price should not be null");
            Assert.assertTrue(firstProductPrice.startsWith("$"), "Product price should start with $");
            
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