# Test Automation Monitoring Stack

Bu proje, Selenium WebDriver ile test otomasyonu yaparken Prometheus, Grafana ve cAdvisor kullanarak gerçek zamanlı monitoring sağlar.

## 🚀 Özellikler

### 📊 Test Metrikleri
- **Test Executions**: Toplam test çalıştırma sayısı
- **Test Successes**: Başarılı test sayısı
- **Test Failures**: Başarısız test sayısı (hata türüne göre)
- **Test Success Rate**: Başarı oranı yüzdesi
- **Test Duration**: Test çalışma süreleri
- **Active Tests**: Şu anda çalışan test sayısı

### 🌐 Sayfa Metrikleri
- **Page Load Time**: Sayfa yükleme süreleri
- **Page Loads Total**: Toplam sayfa yükleme sayısı
- **Browser Memory Usage**: Browser memory kullanımı

### 🖥️ Sistem Metrikleri
- **Node Exporter**: CPU, Memory, Disk, Network kullanımı
- **cAdvisor**: Container metrikleri ve Docker stats
- **Prometheus**: Kendi metrikleri

## 🛠️ Kurulum

### Gereksinimler
- Java 8+
- Maven
- Docker & Docker Compose

### Adımlar

1. **Projeyi klonlayın:**
```bash
git clone <repository-url>
cd TestAuto_MonitoringStack
```

2. **Dependencies'leri yükleyin:**
```bash
mvn clean compile
```

3. **Monitoring stack'i başlatın:**
```bash
run-tests-with-monitoring.bat
```

## 📊 Monitoring Dashboard'ları

### Grafana Dashboard
- **URL**: http://localhost:3000
- **Kullanıcı**: admin
- **Şifre**: admin123

### Prometheus
- **URL**: http://localhost:9090

### PushGateway
- **URL**: http://localhost:9091

### cAdvisor
- **URL**: http://localhost:8080

### Node Exporter
- **URL**: http://localhost:9100

## 🧪 Test Çalıştırma

### Otomatik Çalıştırma
```bash
run-tests-with-monitoring.bat
```

Bu script:
1. Docker Compose ile monitoring stack'i başlatır
2. Eski Allure raporlarını temizler
3. Testleri çalıştırır
4. Dashboard linklerini gösterir

### Manuel Çalıştırma
```bash
# 1. Monitoring stack'i başlat
docker-compose up -d

# 2. Testleri çalıştır
mvn clean test

# 3. Grafana'ya eriş
# http://localhost:3000 (admin/admin123)
```

## 📈 Metrikler

### Test Metrikleri
- `test_executions_total` - Toplam test sayısı
- `test_successes_total` - Başarılı test sayısı
- `test_failures_total` - Başarısız test sayısı (failure_type label'ı ile)
- `test_success_rate` - Başarı oranı yüzdesi
- `test_execution_duration_seconds` - Test çalışma süreleri
- `active_tests` - Aktif test sayısı

### Sayfa Metrikleri
- `page_load_time_seconds` - Sayfa yükleme süreleri
- `page_loads_total` - Toplam sayfa yükleme sayısı
- `browser_memory_usage_bytes` - Browser memory kullanımı

## 🔧 Konfigürasyon

### Prometheus Konfigürasyonu
`monitoring/prometheus/prometheus.yml` dosyasında:
- Scrape interval: 5s
- Test automation metrics: PushGateway (localhost:9091)
- Node Exporter: node-exporter:9100
- cAdvisor: cadvisor:8080

### Grafana Konfigürasyonu
- Datasource: Prometheus (http://prometheus:9090)
- Dashboard: `monitoring/grafana/dashboards/complete-dashboard.json`

## 🐛 Sorun Giderme

### Metrics Server Başlamıyor
```bash
# PushGateway'ın çalışıp çalışmadığını kontrol edin
docker-compose ps pushgateway

# Metrics server'ı manuel başlatın
java -cp "target/classes;target/test-classes" com.testautomation.utils.MetricsExporter
```

### Docker Servisleri Başlamıyor
```bash
# Docker servislerini kontrol edin
docker-compose ps

# Logları kontrol edin
docker-compose logs prometheus
docker-compose logs grafana
```

### Grafana'da Metrikler Görünmüyor
1. Prometheus'ta targets'ları kontrol edin: http://localhost:9090/targets
2. Test automation target'ının UP olduğundan emin olun
3. 2-5 dakika bekleyin (ilk scrape için)

## 📝 Test Yazma

### Yeni Test Ekleme
```java
@Test
public void testExample() {
    String testName = "testExample";
    
    try {
        MetricsExporter.recordTestExecution(testName);
        
        // Test logic here
        // ...
        
        MetricsExporter.recordTestSuccess(testName);
        
    } catch (Exception e) {
        MetricsExporter.recordTestFailure(testName, "assertion_error");
        throw e;
    } finally {
        double duration = getTestDuration();
        MetricsExporter.recordTestDuration(testName, duration);
    }
}
```

### Sayfa Yükleme Süresi Kaydetme
```java
MetricsExporter.recordPageLoadTime("pageName", 2.5);
```

### Browser Memory Kaydetme
```java
Runtime runtime = Runtime.getRuntime();
long memoryUsage = runtime.totalMemory() - runtime.freeMemory();
MetricsExporter.recordBrowserMemoryUsage(memoryUsage);
```

## 🎯 Dashboard Kullanımı

### Grafana Dashboard'ında Görebileceğiniz Metrikler:

1. **Test Başarı Oranı**: Yüzde olarak başarılı test oranı
2. **Test Çalışma Süreleri**: Her testin ne kadar sürdüğü
3. **Sayfa Yükleme Süreleri**: Hangi sayfaların ne kadar sürdüğü
4. **Browser Memory Kullanımı**: Tarayıcı memory kullanımı
5. **Aktif Test Sayısı**: Şu anda çalışan test sayısı
6. **Sistem Metrikleri**: CPU, Memory, Disk kullanımı

### Dashboard Panelleri:
- **Test Executions**: Toplam test sayısı grafiği
- **Success Rate**: Başarı oranı trendi
- **Test Duration**: Test süreleri histogramı
- **Page Load Times**: Sayfa yükleme süreleri
- **System Resources**: CPU, Memory, Disk kullanımı
- **Container Metrics**: Docker container metrikleri

## 📊 Örnek Kullanım Senaryosu

1. **Monitoring stack'i başlatın:**
   ```bash
   run-tests-with-monitoring.bat
   ```

2. **Testler çalışırken Grafana'ya gidin:**
   - http://localhost:3000 (admin/admin123)

3. **Dashboard'da şunları göreceksiniz:**
   - Testlerin gerçek zamanlı çalışması
   - Başarı/başarısızlık oranları
   - Test süreleri
   - Sayfa yükleme performansı
   - Sistem kaynak kullanımı

4. **Test tamamlandıktan sonra:**
   - Final metrikleri görüntüleyin
   - Performans analizi yapın
   - Sorunlu testleri tespit edin

## 🔄 Sürekli Monitoring

Metrics server test suite tamamlandıktan sonra da çalışmaya devam eder. Bu sayede:
- Sürekli monitoring yapabilirsiniz
- Geçmiş metrikleri karşılaştırabilirsiniz
- Trend analizi yapabilirsiniz

## 📞 Destek

Sorun yaşarsanız:
1. Docker servislerinin çalıştığını kontrol edin
2. Port'ların açık olduğunu kontrol edin
3. Test loglarını inceleyin
4. Prometheus targets'larını kontrol edin

---

**Not**: Bu proje Windows ortamında test edilmiştir. Linux/Mac için script'leri uyarlamanız gerekebilir. 
