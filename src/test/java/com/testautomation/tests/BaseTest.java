package com.testautomation.tests;

import com.testautomation.utils.MetricsExporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Instant;

public class BaseTest {
    
    protected WebDriver driver;
    protected Instant testStartTime;
    
    @BeforeSuite
    public void setUpSuite() {
        // Initialize metrics exporter
        MetricsExporter.initialize();
        System.out.println("🚀 Test suite başlatılıyor...");
        System.out.println("📤 PushGateway: http://localhost:9091");
        System.out.println("📈 Grafana Dashboard: http://localhost:3000");
    }
    
    @BeforeMethod
    public void setUp() {
        testStartTime = Instant.now();
        
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        
        System.out.println("🌐 Tarayıcı başlatıldı: " + driver.getCurrentUrl());
        
        // Record initial browser memory usage
        recordBrowserMemoryUsage();
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Record final browser memory usage before closing
            recordBrowserMemoryUsage();
            
            driver.quit();
            System.out.println("🔒 Tarayıcı kapatıldı");
        }
    }
    
    @AfterSuite
    public void tearDownSuite() {
        // Don't shutdown metrics exporter - keep it running for continuous monitoring
        System.out.println("✅ Test suite tamamlandı! Metrics server çalışmaya devam ediyor...");
        System.out.println("📤 PushGateway: http://localhost:9091");
        System.out.println("📈 Dashboard: http://localhost:3000 (admin/admin123)");
    }
    
    protected void recordBrowserMemoryUsage() {
        try {
            Runtime runtime = Runtime.getRuntime();
            long memoryUsage = runtime.totalMemory() - runtime.freeMemory();
            MetricsExporter.recordBrowserMemoryUsage(memoryUsage);
            System.out.println("💾 Browser memory: " + (memoryUsage / 1024 / 1024) + " MB");
        } catch (Exception e) {
            System.err.println("⚠️ Failed to record browser memory: " + e.getMessage());
        }
    }
    
    protected double getTestDuration() {
        if (testStartTime != null) {
            return (Instant.now().toEpochMilli() - testStartTime.toEpochMilli()) / 1000.0;
        }
        return 0.0;
    }
} 