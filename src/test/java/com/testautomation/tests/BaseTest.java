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

public class BaseTest {
    
    protected WebDriver driver;
    
    @BeforeSuite
    public void setUpSuite() {
        // Initialize metrics exporter
        MetricsExporter.initialize();
        System.out.println("🚀 Test suite başlatılıyor...");
    }
    
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        
        System.out.println("🌐 Tarayıcı başlatıldı: " + driver.getCurrentUrl());
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔒 Tarayıcı kapatıldı");
        }
    }
    
    @AfterSuite
    public void tearDownSuite() {
        // Don't shutdown metrics exporter - keep it running for continuous monitoring
        System.out.println("✅ Test suite tamamlandı! Metrics server çalışmaya devam ediyor...");
    }
} 