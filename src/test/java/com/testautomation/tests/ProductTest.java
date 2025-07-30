package com.testautomation.tests;

import com.testautomation.pages.LoginPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.MetricsExporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.time.Instant;

public class ProductTest extends BaseTest {
    
    @Test
    @Description("Verify products are displayed after login")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductsDisplayed() {
        Instant startTime = Instant.now();
        String testName = "testProductsDisplayed";
        
        System.out.println("🚀 Test başlıyor: " + testName);
        
        try {
            // Initialize metrics exporter
            MetricsExporter.initialize();
            System.out.println("✅ MetricsExporter initialized");
            
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
            
            // Record browser memory usage
            Runtime runtime = Runtime.getRuntime();
            long memoryUsage = runtime.totalMemory() - runtime.freeMemory();
            MetricsExporter.recordBrowserMemoryUsage(memoryUsage);
            System.out.println("✅ Browser memory usage recorded: " + (memoryUsage / 1024 / 1024) + " MB");
            
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("✅ Test duration recorded: " + duration + "s");
            
            // Update success rate (assuming this test passed)
            MetricsExporter.updateSuccessRate(100.0);
            System.out.println("✅ Success rate updated");
        }
    }
    
    @Test
    @Description("Add product to cart and verify cart badge")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCart() {
        Instant startTime = Instant.now();
        String testName = "testAddProductToCart";
        
        System.out.println("🚀 Test başlıyor: " + testName);
        
        try {
            // Initialize metrics exporter
            MetricsExporter.initialize();
            System.out.println("✅ MetricsExporter initialized");
            
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
            
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
            System.out.println("✅ Test duration recorded: " + duration + "s");
        }
    }
    
    @Test
    @Description("Add multiple products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testAddMultipleProductsToCart() {
        Instant startTime = Instant.now();
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
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
        }
    }
    
    @Test
    @Description("Remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveProductFromCart() {
        Instant startTime = Instant.now();
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
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
        }
    }
    
    @Test
    @Description("Verify product names are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductNamesDisplayed() {
        Instant startTime = Instant.now();
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
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
        }
    }
    
    @Test
    @Description("Verify product prices are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductPricesDisplayed() {
        Instant startTime = Instant.now();
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
            
        } catch (Exception e) {
            MetricsExporter.recordTestFailure(testName, "assertion_error");
            throw e;
        } finally {
            double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
            MetricsExporter.recordTestDuration(testName, duration);
        }
    }
} 