package com.testautomation.utils;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class MetricsExporter {
    
    private static final Counter testExecutionsTotal = Counter.build()
            .name("test_executions_total")
            .help("Total number of test executions")
            .register();
    
    private static final Counter testFailuresTotal = Counter.build()
            .name("test_failures_total")
            .help("Total number of test failures")
            .labelNames("failure_type")
            .register();
    
    private static final Histogram testExecutionDuration = Histogram.build()
            .name("test_execution_duration_seconds")
            .help("Test execution duration in seconds")
            .labelNames("test_name")
            .register();
    
    private static final Gauge testSuccessRate = Gauge.build()
            .name("test_success_rate")
            .help("Test success rate percentage")
            .register();
    
    private static final Histogram pageLoadTime = Histogram.build()
            .name("page_load_time_seconds")
            .help("Page load time in seconds")
            .labelNames("page_name")
            .register();
    
    private static final Gauge browserMemoryUsage = Gauge.build()
            .name("browser_memory_usage_bytes")
            .help("Browser memory usage in bytes")
            .register();
    
    private static HTTPServer server;
    private static boolean isInitialized = false;
    
    public static void initialize() {
        if (!isInitialized) {
            try {
                server = new HTTPServer(8082);
                System.out.println("✅ Metrics server started on port 8082");
                isInitialized = true;
            } catch (IOException e) {
                System.err.println("⚠️ Failed to start metrics server: " + e.getMessage());
                if (e.getMessage().contains("Address already in use")) {
                    System.out.println("✅ Metrics server already running on port 8082");
                    isInitialized = true;
                }
            }
        }
    }
    
    public static void recordTestExecution(String testName) {
        testExecutionsTotal.inc();
        System.out.println("✅ Test executed: " + testName + " (Total: " + testExecutionsTotal.get() + ")");
    }
    
    public static void recordTestFailure(String testName, String failureType) {
        testFailuresTotal.labels(failureType).inc();
        System.out.println("❌ Test failed: " + testName + " - " + failureType);
    }
    
    public static void recordTestDuration(String testName, double durationSeconds) {
        testExecutionDuration.labels(testName).observe(durationSeconds);
        System.out.println("⏱️ Test duration: " + testName + " - " + durationSeconds + "s");
    }
    
    public static void updateSuccessRate(double successRate) {
        testSuccessRate.set(successRate);
        System.out.println("📊 Success rate updated: " + successRate + "%");
    }
    
    public static void recordPageLoadTime(String pageName, double loadTimeSeconds) {
        pageLoadTime.labels(pageName).observe(loadTimeSeconds);
        System.out.println("🌐 Page load time: " + pageName + " - " + loadTimeSeconds + "s");
    }
    
    public static void recordBrowserMemoryUsage(long memoryBytes) {
        browserMemoryUsage.set(memoryBytes);
        System.out.println("💾 Browser memory: " + (memoryBytes / 1024 / 1024) + " MB");
    }
    
    public static void shutdown() {
        if (server != null) {
            server.stop();
            System.out.println("🛑 Metrics server stopped");
            server = null;
        }
        isInitialized = false;
    }
    
    // Main method to run MetricsExporter as standalone application
    public static void main(String[] args) {
        System.out.println("🚀 Starting MetricsExporter as standalone application...");
        initialize();
        
        // Keep the application running
        try {
            System.out.println("✅ MetricsExporter is running on port 8082. Press Ctrl+C to stop.");
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ MetricsExporter interrupted");
        } finally {
            shutdown();
        }
    }
} 