# 🚀 Test Automation Monitoring Stack

Bu proje, **Selenium Java test otomasyonu** ile **Grafana, Prometheus ve cAdvisor** kullanarak kapsamlı bir **monitoring stack** oluşturur. Test otomasyonu metriklerini gerçek zamanlı olarak izleyebilir ve görselleştirebilirsiniz.

## 📋 İçindekiler

- [🎯 Proje Özeti](#-proje-özeti)
- [🏗️ Mimari](#️-mimari)
- [📦 Teknolojiler](#-teknolojiler)
- [🚀 Hızlı Başlangıç](#-hızlı-başlangıç)
- [📊 Monitoring Stack](#-monitoring-stack)
- [🧪 Test Otomasyonu](#-test-otomasyonu)
- [📈 Metrikler](#-metrikler)
- [🔧 Konfigürasyon](#-konfigürasyon)
- [📁 Proje Yapısı](#-proje-yapısı)
- [🛠️ Geliştirme](#️-geliştirme)
- [📝 API Dokümantasyonu](#-api-dokümantasyonu)
- [🤝 Katkıda Bulunma](#-katkıda-bulunma)
- [📄 Lisans](#-lisans)

## 🎯 Proje Özeti

Bu proje, modern test otomasyonu için **end-to-end monitoring çözümü** sunar:

- ✅ **Selenium Java** test otomasyonu
- ✅ **Page Object Model (POM)** tasarım deseni
- ✅ **TestNG** test framework'ü
- ✅ **Grafana** dashboard'ları
- ✅ **Prometheus** metrik toplama
- ✅ **cAdvisor** container monitoring
- ✅ **Docker Compose** ile kolay deployment
- ✅ **Gerçek test verileri** ile dinamik metrikler

## 🏗️ Mimari

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Selenium      │    │   Prometheus    │    │     Grafana     │
│   Java Tests    │───▶│   Metrics       │───▶│   Dashboards    │
│   (Real Data)   │    │   Collection    │    │   & Alerts      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       ▼                       │
         │              ┌─────────────────┐             │
         │              │   cAdvisor      │             │
         │              │   Container     │             │
         │              │   Monitoring    │             │
         │              └─────────────────┘             │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Allure        │    │   Node Exporter │    │   Real-time     │
│   Reports       │    │   Host Metrics  │    │   Monitoring    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📦 Teknolojiler

### 🧪 Test Otomasyonu
- **Java 21** - Ana programlama dili
- **Selenium WebDriver 4.15.0** - Web otomasyon
- **TestNG 7.8.0** - Test framework
- **WebDriverManager 5.6.2** - Driver yönetimi
- **Allure 2.24.0** - Test raporlama
- **Prometheus Client** - Metrik export

### 📊 Monitoring Stack
- **Grafana 10.0.0** - Dashboard ve görselleştirme
- **Prometheus 2.45.0** - Metrik toplama ve depolama
- **cAdvisor** - Container monitoring
- **Node Exporter** - Host sistem metrikleri

### 🐳 Containerization
- **Docker** - Container runtime
- **Docker Compose** - Multi-container orchestration

## 🚀 Hızlı Başlangıç

### 📋 Gereksinimler

- ✅ **Docker** ve **Docker Compose** yüklü
- ✅ **Java 21** JDK
- ✅ **Maven** (opsiyonel)
- ✅ **Git** (opsiyonel)

### 🔧 Kurulum

1. **Projeyi klonlayın:**
```bash
git clone <repository-url>
cd TestAuto_MonitoringStack
```

2. **Monitoring stack'i başlatın:**
```bash
docker-compose up -d
```

3. **Testleri çalıştırın (MetricsExporter otomatik başlar):**
```bash
# Maven ile (önerilen)
mvn test

# Veya IDE'den TestNG suite'i çalıştırın
```

### 🌐 Erişim URL'leri

| Servis | URL | Kullanıcı/Şifre |
|--------|-----|------------------|
| **Grafana** | http://localhost:3000 | admin/admin |
| **Prometheus** | http://localhost:9090 | - |
| **cAdvisor** | http://localhost:8080 | - |
| **Node Exporter** | http://localhost:9100 | - |
| **Metrics Endpoint** | http://localhost:8081/metrics | - |

## 📊 Monitoring Stack

### 🎯 Grafana Dashboard

**"Complete Test Automation Dashboard"** şu metrikleri içerir:

#### 📈 Sistem Metrikleri
- **Container CPU Usage** - Docker container CPU kullanımı
- **Container Memory Usage** - Docker container bellek kullanımı

#### 🧪 Test Otomasyonu Metrikleri
- **Test Success Rate** - Gerçek test başarı oranı (%)
- **Test Execution Count** - Toplam test çalıştırma sayısı
- **Test Duration** - Gerçek test süreleri (saniye)
- **Failed Tests Count** - Başarısız test sayısı

#### 🌐 Web Performance Metrikleri
- **Browser Memory Usage** - Tarayıcı bellek kullanımı (MB)
- **Page Load Times** - Gerçek sayfa yükleme süreleri (saniye)

### 📸 Dashboard Önizlemesi

<img width="1350" height="682" alt="image" src="https://github.com/user-attachments/assets/c128e18b-32a1-46d0-b586-df1ac2deff51" />

*Yukarıdaki ekran görüntüsü, "Complete Test Automation Dashboard"ın gerçek zamanlı metriklerini göstermektedir. Dashboard, sistem performansı (CPU, bellek) ve test otomasyonu sonuçlarını (test başarı oranı, çalıştırma sayısı, süreler) kapsamlı bir şekilde görselleştirir.*

#### 🔍 Dashboard Detayları:

**Sistem Metrikleri:**
- **Container CPU Usage**: Docker container CPU kullanımı (stacked area chart)
- **Container Memory Usage**: Docker container bellek kullanımı (line chart)

**Test Metrikleri:**
- **Test Success Rate**: Gerçek test başarı oranı (timeseries)
- **Test Execution Count**: Toplam test çalıştırma sayısı (timeseries)
- **Test Duration**: Ortalama test süreleri (timeseries)
- **Failed Tests Count**: Başarısız test sayısı (timeseries)

**Performance Metrikleri:**
- **Browser Memory Usage**: Tarayıcı bellek kullanımı (timeseries)
- **Page Load Times**: Sayfa yükleme süreleri (timeseries)

### 🔄 Gerçek Zamanlı Güncelleme

- **Dashboard yenileme**: Her 5 saniye
- **Metrik güncelleme**: Test çalıştırma sırasında gerçek veriler
- **Test verileri**: MetricsExporter utility'den otomatik

## 🧪 Test Otomasyonu

### 🎯 Test Senaryoları

Proje **SauceDemo** web sitesi üzerinde testler içerir:

#### 🔐 Login Testleri
- ✅ Geçerli kullanıcı girişi (`standard_user`)
- ❌ Geçersiz kullanıcı girişi
- ❌ Kilitli kullanıcı girişi (`locked_out_user`)
- ✅ Problem kullanıcı girişi (`problem_user`)

#### 🛍️ Product Testleri
- ✅ Ürün listesi görüntüleme (6 ürün)
- ✅ Sepete ürün ekleme
- ✅ Sepetten ürün çıkarma
- ✅ Çoklu ürün ekleme
- ✅ Ürün isimleri kontrolü
- ✅ Ürün fiyatları kontrolü

### 📁 Test Yapısı

```
src/test/java/com/testautomation/
├── pages/
│   ├── BasePage.java          # Temel sayfa sınıfı
│   ├── LoginPage.java         # Giriş sayfası POM
│   └── ProductPage.java       # Ürün sayfası POM
├── tests/
│   ├── BaseTest.java          # Temel test sınıfı (MetricsExporter başlatma)
│   ├── LoginTest.java         # Giriş testleri (gerçek metrikler)
│   └── ProductTest.java       # Ürün testleri (gerçek metrikler)
└── utils/
    └── MetricsExporter.java   # Prometheus metrik export utility
```

### 🎨 Page Object Model (POM)

```java
// LoginPage.java örneği
public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

### 📊 Gerçek Test Metrikleri

Her test çalıştığında şu metrikler otomatik kaydedilir:

- **Test Execution Count** - Test çalıştırma sayısı
- **Test Duration** - Test süreleri (milisaniye)
- **Test Failures** - Hata sayısı ve türleri
- **Page Load Times** - Sayfa yükleme süreleri
- **Browser Memory** - Tarayıcı bellek kullanımı

## 📈 Metrikler

### 🔍 Prometheus Metrikleri

| Metrik Adı | Tip | Açıklama |
|------------|-----|----------|
| `test_success_rate` | Gauge | Test başarı oranı (%) |
| `test_executions_total` | Counter | Toplam test çalıştırma sayısı |
| `test_execution_duration_seconds` | Histogram | Test süreleri (saniye) |
| `test_failures_total` | Counter | Başarısız test sayısı |
| `browser_memory_usage_bytes` | Gauge | Tarayıcı bellek kullanımı (bytes) |
| `page_load_time_seconds` | Histogram | Sayfa yükleme süreleri (saniye) |

### 📊 Dashboard Panelleri

1. **Container CPU Usage** - Sistem CPU kullanımı
2. **Container Memory Usage** - Sistem bellek kullanımı
3. **Test Success Rate** - Test başarı oranı (gerçek veri)
4. **Test Execution Count** - Test çalıştırma sayısı (gerçek veri)
5. **Test Duration** - Test süreleri (gerçek veri)
6. **Failed Tests Count** - Başarısız test sayısı (gerçek veri)
7. **Browser Memory Usage** - Tarayıcı bellek kullanımı (gerçek veri)
8. **Page Load Times** - Sayfa yükleme süreleri (gerçek veri)
9. **Test Execution Duration** - Test çalıştırma süreleri (gerçek veri)
10. **Test Summary** - Test özeti (bar chart)

## 🔧 Konfigürasyon

### 📁 Docker Compose

```yaml
services:
  prometheus:
    image: prom/prometheus:latest
    ports: ["9090:9090"]
    volumes: ["./monitoring/prometheus:/etc/prometheus"]
    network_mode: host
    
  grafana:
    image: grafana/grafana:latest
    ports: ["3000:3000"]
    environment:
      - GF_SECURITY_ADMIN_USER=admin
      - GF_SECURITY_ADMIN_PASSWORD=admin
    volumes: ["./monitoring/grafana:/etc/grafana"]
      
  cadvisor:
    image: gcr.io/cadvisor/cadvisor:latest
    ports: ["8080:8080"]
    volumes: ["/:/rootfs:ro", "/var/run:/var/run:ro"]
    
  node-exporter:
    image: prom/node-exporter:latest
    ports: ["9100:9100"]
```

### 📊 Prometheus Config

```yaml
scrape_configs:
  - job_name: 'test-automation'
    static_configs:
      - targets: ['localhost:8081']
    scrape_interval: 5s
    metrics_path: '/metrics'
    honor_labels: true
```

### 🎨 Grafana Dashboard

- **Datasource**: Prometheus
- **Refresh Rate**: 5 saniye
- **Time Range**: Son 1 saat
- **Theme**: Dark
- **Panels**: Gerçek test verileri

## 📁 Proje Yapısı

```
TestAuto_MonitoringStack/
├── 📁 src/
│   └── 📁 test/java/com/testautomation/
│       ├── 📁 pages/           # Page Object Model
│       │   ├── BasePage.java
│       │   ├── LoginPage.java
│       │   └── ProductPage.java
│       ├── 📁 tests/           # Test sınıfları
│       │   ├── BaseTest.java   # MetricsExporter başlatma
│       │   ├── LoginTest.java  # Gerçek metrikler
│       │   └── ProductTest.java # Gerçek metrikler
│       └── 📁 utils/           # Utility sınıfları
│           └── MetricsExporter.java # Prometheus export
├── 📁 monitoring/
│   ├── 📁 grafana/
│   │   ├── 📁 dashboards/      # Dashboard JSON'ları
│   │   │   └── complete-dashboard.json
│   │   └── 📁 provisioning/    # Grafana konfigürasyonu
│   │       ├── datasources/
│   │       └── dashboards/
│   └── 📁 prometheus/
│       └── prometheus.yml      # Prometheus konfigürasyonu
├── 📄 pom.xml                  # Maven dependencies
├── 📄 testng.xml              # TestNG suite konfigürasyonu
├── 📄 docker-compose.yml      # Docker services
└── 📄 README.md               # Proje dokümantasyonu
```

## 🛠️ Geliştirme

### 🔧 Geliştirme Ortamı Kurulumu

1. **IDE Kurulumu:**
   - IntelliJ IDEA (önerilen)
   - Eclipse
   - VS Code

2. **Dependencies (pom.xml):**
   ```xml
   <dependencies>
     <dependency>
       <groupId>org.seleniumhq.selenium</groupId>
       <artifactId>selenium-java</artifactId>
       <version>4.15.0</version>
     </dependency>
     <dependency>
       <groupId>org.testng</groupId>
       <artifactId>testng</artifactId>
       <version>7.8.0</version>
     </dependency>
     <dependency>
       <groupId>io.prometheus</groupId>
       <artifactId>simpleclient</artifactId>
       <version>0.16.0</version>
     </dependency>
     <dependency>
       <groupId>io.prometheus</groupId>
       <artifactId>simpleclient_httpserver</artifactId>
       <version>0.16.0</version>
     </dependency>
   </dependencies>
   ```

3. **Test Çalıştırma:**
   ```bash
   # Maven ile
   mvn test
   
   # IDE'den
   Right-click → Run TestNG
   ```

### 🧪 Yeni Test Ekleme

1. **Page Object oluşturun:**
   ```java
   public class NewPage extends BasePage {
       @FindBy(id = "element-id")
       private WebElement element;
       
       public void performAction() {
           element.click();
       }
   }
   ```

2. **Test sınıfı oluşturun (MetricsExporter ile):**
   ```java
   public class NewTest extends BaseTest {
       @Test
       public void testNewFunctionality() {
           Instant startTime = Instant.now();
           String testName = "testNewFunctionality";
           
           try {
               MetricsExporter.recordTestExecution(testName);
               // Test logic
           } catch (Exception e) {
               MetricsExporter.recordTestFailure(testName, "error_type");
               throw e;
           } finally {
               double duration = (Instant.now().toEpochMilli() - startTime.toEpochMilli()) / 1000.0;
               MetricsExporter.recordTestDuration(testName, duration);
           }
       }
   }
   ```

3. **TestNG suite'e ekleyin:**
   ```xml
   <test name="New Tests">
       <classes>
           <class name="com.testautomation.tests.NewTest"/>
       </classes>
   </test>
   ```

### 📊 Yeni Metrik Ekleme

1. **MetricsExporter.java'ya ekleyin:**
   ```java
   private static final Gauge newMetric = Gauge.build()
       .name("new_metric_name")
       .help("Description of new metric")
       .register();
   
   public static void recordNewMetric(double value) {
       newMetric.set(value);
   }
   ```

2. **Dashboard'a panel ekleyin:**
   ```json
   {
     "title": "New Metric",
     "targets": [{
       "expr": "new_metric_name",
       "legendFormat": "New Metric"
     }]
   }
   ```

## 📝 API Dokümantasyonu

### 🔌 Metrics Endpoint

**URL:** `http://localhost:8081/metrics`  
**Method:** GET  
**Response:** Prometheus format

```bash
# Örnek response
# HELP test_success_rate Test success rate percentage
# TYPE test_success_rate gauge
test_success_rate 85

# HELP test_executions_total Total number of test executions
# TYPE test_executions_total counter
test_executions_total 42

# HELP test_execution_duration_seconds Test execution duration in seconds
# TYPE test_execution_duration_seconds histogram
test_execution_duration_seconds_bucket{le="0.1"} 5
test_execution_duration_seconds_bucket{le="0.5"} 15
test_execution_duration_seconds_bucket{le="1.0"} 25
```

### 📊 Prometheus Queries

```promql
# Test başarı oranı
test_success_rate

# Test çalıştırma sayısı
test_executions_total

# Test süreleri (ortalama)
rate(test_execution_duration_seconds_sum[5m]) / rate(test_execution_duration_seconds_count[5m])

# Başarısız test sayısı
test_failures_total

# Tarayıcı bellek kullanımı (MB)
browser_memory_usage_bytes / 1024 / 1024

# Sayfa yükleme süreleri (ortalama)
rate(page_load_time_seconds_sum[5m]) / rate(page_load_time_seconds_count[5m])
```

## 🤝 Katkıda Bulunma

### 📋 Katkı Süreci

1. **Fork** yapın
2. **Feature branch** oluşturun (`git checkout -b feature/amazing-feature`)
3. **Commit** yapın (`git commit -m 'Add amazing feature'`)
4. **Push** yapın (`git push origin feature/amazing-feature`)
5. **Pull Request** oluşturun

### 🧪 Test Katkıları

- ✅ Yeni test senaryoları ekleyin
- ✅ Page Object'leri geliştirin
- ✅ Test coverage artırın
- ✅ Performance testleri ekleyin
- ✅ Gerçek metriklerle entegre edin

### 📊 Monitoring Katkıları

- ✅ Yeni dashboard'lar oluşturun
- ✅ Alert kuralları ekleyin
- ✅ Yeni metrikler tanımlayın
- ✅ Grafana plugin'leri entegre edin

## 📄 Lisans

Bu proje **MIT License** altında lisanslanmıştır.

---

## 🎯 Sonuç

Bu proje, modern test otomasyonu için **kapsamlı bir monitoring çözümü** sunar. **Selenium Java** ile **Grafana, Prometheus ve cAdvisor** entegrasyonu sayesinde test otomasyonu süreçlerinizi **gerçek zamanlı** olarak izleyebilir ve **görselleştirebilirsiniz**.

**Key Features:**
- 🚀 **Kolay kurulum** (Docker Compose)
- 📊 **Gerçek zamanlı monitoring** (gerçek test verileri)
- 🧪 **Kapsamlı test coverage** (Login + Product tests)
- 📈 **Detaylı metrikler** (başarı oranı, süreler, bellek)
- 🎨 **Modern dashboard'lar** (Grafana)
- 🔄 **Otomatik metrik toplama** (Prometheus)

**Başlamak için:** `docker-compose up -d` komutunu çalıştırın, testleri çalıştırın ve http://localhost:3000 adresine gidin! 🎉 
