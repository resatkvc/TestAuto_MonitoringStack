package com.testautomation.utils;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

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
    
    private static final Counter testSuccessesTotal = Counter.build()
            .name("test_successes_total")
            .help("Total number of successful tests")
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
    
    private static final Counter pageLoadsTotal = Counter.build()
            .name("page_loads_total")
            .help("Total number of page loads")
            .labelNames("page_name")
            .register();
    
    private static final Gauge activeTests = Gauge.build()
            .name("active_tests")
            .help("Number of currently running tests")
            .register();
    
    private static final Gauge totalTestsGauge = Gauge.build()
            .name("total_tests_gauge")
            .help("Total number of tests (gauge)")
            .register();
    
    private static final Gauge successfulTestsGauge = Gauge.build()
            .name("successful_tests_gauge")
            .help("Number of successful tests (gauge)")
            .register();
    
    private static PushGateway pushGateway;
    private static boolean isInitialized = false;
    private static final AtomicLong totalTests = new AtomicLong(0);
    private static final AtomicLong successfulTests = new AtomicLong(0);
    private static final AtomicLong activeTestCount = new AtomicLong(0);
    
    public static void initialize() {
        if (!isInitialized) {
            try {
                // Initialize PushGateway
                String[] pushGatewayUrls = {
                    "localhost:9091",
                    "127.0.0.1:9091",
                    "host.docker.internal:9091"
                };
                
                boolean connected = false;
                for (String url : pushGatewayUrls) {
                    try {
                        System.out.println("🔗 Trying PushGateway URL: " + url);
                        pushGateway = new PushGateway(url);
                        
                        // Test connection
                        testPushGatewayConnection();
                        connected = true;
                        System.out.println("✅ PushGateway connected successfully: " + url);
                        break;
                    } catch (Exception e) {
                        System.err.println("❌ Failed to connect to PushGateway at " + url + ": " + e.getMessage());
                        pushGateway = null;
                    }
                }
                
                if (!connected) {
                    System.err.println("❌ All PushGateway connection attempts failed");
                    System.err.println("⚠️ Continuing without PushGateway...");
                    pushGateway = null;
                }
                
                System.out.println("📈 Grafana Dashboard: http://localhost:3000 (admin/admin123)");
                System.out.println("📤 PushGateway: http://localhost:9091");
                isInitialized = true;
            } catch (Exception e) {
                System.err.println("❌ MetricsExporter initialization failed: " + e.getMessage());
            }
        } else {
            System.out.println("✅ MetricsExporter already initialized");
        }
    }
    
    private static void testPushGatewayConnection() {
        try {
            System.out.println("🔍 Testing PushGateway connection...");
            
            // Test with a simple metric
            Counter testCounter = Counter.build()
                    .name("test_connection_counter")
                    .help("Test connection counter")
                    .register();
            
            testCounter.inc();
            System.out.println("📊 Test metric created: test_connection_counter = 1");
            
            // Try to push to PushGateway
            pushGateway.pushAdd(CollectorRegistry.defaultRegistry, "test_connection_job");
            System.out.println("✅ PushGateway connection test successful");
            System.out.println("📤 Test metric pushed to PushGateway");
            
            // Clean up test metric
            CollectorRegistry.defaultRegistry.unregister(testCounter);
            System.out.println("🧹 Test metric cleaned up");
            
        } catch (Exception e) {
            System.err.println("❌ PushGateway connection test failed: " + e.getMessage());
            System.err.println("🔍 Error details: " + e.getClass().getSimpleName());
            e.printStackTrace();
        }
    }
    
    public static void recordTestExecution(String testName) {
        testExecutionsTotal.inc();
        totalTests.incrementAndGet();
        activeTestCount.incrementAndGet();
        activeTests.set(activeTestCount.get());
        totalTestsGauge.set(totalTests.get());
        System.out.println("✅ Test executed: " + testName + " (Total: " + testExecutionsTotal.get() + ")");
        updateSuccessRate();
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void recordTestFailure(String testName, String failureType) {
        testFailuresTotal.labels(failureType).inc();
        activeTestCount.decrementAndGet();
        activeTests.set(activeTestCount.get());
        System.out.println("❌ Test failed: " + testName + " - " + failureType);
        updateSuccessRate();
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void recordTestSuccess(String testName) {
        testSuccessesTotal.inc();
        successfulTests.incrementAndGet();
        activeTestCount.decrementAndGet();
        activeTests.set(activeTestCount.get());
        successfulTestsGauge.set(successfulTests.get());
        System.out.println("✅ Test passed: " + testName);
        updateSuccessRate();
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void recordTestDuration(String testName, double durationSeconds) {
        testExecutionDuration.labels(testName).observe(durationSeconds);
        System.out.println("⏱️ Test duration: " + testName + " - " + durationSeconds + "s");
        pushMetricsToGateway("selenium_test_job");
    }
    
    private static void updateSuccessRate() {
        long total = totalTests.get();
        long successful = successfulTests.get();
        if (total > 0) {
            double successRate = (double) successful / total * 100.0;
            testSuccessRate.set(successRate);
            System.out.println("📊 Success rate updated: " + String.format("%.2f", successRate) + "% (" + successful + "/" + total + ")");
        }
    }
    
    public static void recordPageLoadTime(String pageName, double loadTimeSeconds) {
        pageLoadTime.labels(pageName).observe(loadTimeSeconds);
        pageLoadsTotal.labels(pageName).inc();
        System.out.println("🌐 Page load time: " + pageName + " - " + loadTimeSeconds + "s");
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void recordBrowserMemoryUsage(long memoryBytes) {
        browserMemoryUsage.set(memoryBytes);
        System.out.println("💾 Browser memory: " + (memoryBytes / 1024 / 1024) + " MB");
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void recordPageLoad(String pageName) {
        pageLoadsTotal.labels(pageName).inc();
        System.out.println("🌐 Page loaded: " + pageName);
        pushMetricsToGateway("selenium_test_job");
    }
    
    public static void pushMetricsToGateway(String jobName) {
        if (pushGateway != null) {
            try {
                pushGateway.pushAdd(CollectorRegistry.defaultRegistry, jobName);
                System.out.println("📤 Metrics pushed to PushGateway (Job: " + jobName + ")");
            } catch (IOException e) {
                System.err.println("⚠️ Failed to push metrics to PushGateway: " + e.getMessage());
            }
        } else {
            System.err.println("⚠️ PushGateway is null - not initialized");
        }
    }
    
    public static void shutdown() {
        System.out.println("🛑 MetricsExporter shutdown");
        isInitialized = false;
    }
    
    // Main method to run MetricsExporter as standalone application
    public static void main(String[] args) {
        System.out.println("🚀 Starting MetricsExporter as standalone application...");
        initialize();
        
        // Keep the application running
        try {
            System.out.println("✅ MetricsExporter is running. Press Ctrl+C to stop.");
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("⚠️ MetricsExporter interrupted");
        } finally {
            shutdown();
        }
    }
} 