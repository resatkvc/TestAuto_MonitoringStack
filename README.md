# Test Automation Monitoring Stack

Bu proje, Selenium WebDriver ile test otomasyonu yaparken Prometheus, Grafana , cAdvisor ve PushGateway kullanarak gerçek zamanlı monitoring sağlar.

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

## 📖 Blog Yazısı

👉 Bu projenin detaylı kurulumunu, konfigürasyonunu ve kullanımını adım adım anlattığım blog yazım için aşağıdaki bağlantıdan inceleyebilirsiniz:

🔗 [Metrik Tabanlı Test Otomasyonu: Prometheus + Grafana ile Gerçek Zamanlı Gözlem](https://kavciresat.medium.com/metrik-tabanl%C4%B1-test-otomasyonu-prometheus-grafana-ile-ger%C3%A7ek-zamanl%C4%B1-g%C3%B6zlem-60e5d09ad2e7)

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
docker-compose up -d
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

### Manuel Çalıştırma
```bash
# 1. Monitoring stack'i başlat
docker-compose up -d

# 2. IDE'de testleri çalıştır
# IntelliJ IDEA veya Eclipse'de:
# src/test/java/com/testautomation/tests/LoginTest.java
# src/test/java/com/testautomation/tests/ProductTest.java

# 3. Grafana'ya eriş
# http://localhost:3000 (admin/admin123)
```

## 📈 Metrikler ve Açıklamaları

### Test Metrikleri

#### **Test Success Rate (Test Başarı Oranı)**
- **Amaç**: Test suite'inizin ne kadar başarılı olduğunu gösterir
- **Hesaplama**: (Başarılı Test Sayısı / Toplam Test Sayısı) × 100
- **Örnek**: %85 = 17 başarılı test / 20 toplam test
- **Kullanım**: Test kalitesini ve güvenilirliğini değerlendirmek için

#### **Test Execution Count (Test Çalıştırma Sayısı)**
- **Amaç**: Belirli bir zaman diliminde kaç test çalıştırıldığını gösterir
- **Örnek**: 42 test çalıştırıldı
- **Kullanım**: Test throughput'unu ve performansını ölçmek için

#### **Test Duration (Test Süresi)**
- **Amaç**: Her testin ne kadar sürdüğünü gösterir
- **Birim**: Saniye
- **Örnek**: Ortalama 2.5 saniye
- **Kullanım**: Yavaş testleri tespit etmek ve optimizasyon için

#### **Failed Tests Count (Başarısız Test Sayısı)**
- **Amaç**: Başarısız testlerin sayısını ve türünü gösterir
- **Hata Türleri**: assertion_error, timeout_error, element_not_found
- **Kullanım**: Hangi tür hataların daha sık olduğunu analiz etmek için

#### **Active Tests (Aktif Test Sayısı)**
- **Amaç**: Şu anda çalışan test sayısını gösterir
- **Kullanım**: Paralel test çalıştırma durumunu izlemek için

### Sayfa Metrikleri

#### **Page Load Times (Sayfa Yükleme Süreleri)**
- **Amaç**: Web sayfalarının ne kadar sürede yüklendiğini gösterir
- **Birim**: Saniye
- **Örnek**: Login sayfası 1.2s, Inventory sayfası 2.8s
- **Kullanım**: Web uygulamasının performansını değerlendirmek için

#### **Browser Memory Usage (Tarayıcı Bellek Kullanımı)**
- **Amaç**: Tarayıcının ne kadar bellek kullandığını gösterir
- **Birim**: MB (Megabyte)
- **Örnek**: 45 MB
- **Kullanım**: Memory leak'leri tespit etmek için

### Sistem Metrikleri

#### **Container CPU Usage (Kapsayıcı CPU Kullanımı)**
- **Amaç**: Docker container'larının CPU kullanımını gösterir
- **Birim**: CPU yüzdesi
- **Kullanım**: Sistem kaynaklarının kullanımını izlemek için

#### **Container Memory Usage (Kapsayıcı Bellek Kullanımı)**
- **Amaç**: Docker container'larının bellek kullanımını gösterir
- **Birim**: MiB (Mebibyte)
- **Kullanım**: Memory kullanımını optimize etmek için

## 🔧 Konfigürasyon

### Prometheus Konfigürasyonu
`monitoring/prometheus/prometheus.yml` dosyasında:
- Scrape interval: 15s
- Test automation metrics: PushGateway (localhost:9091)
- Node Exporter: node-exporter:9100
- cAdvisor: cadvisor:8080

### Grafana Konfigürasyonu
- Datasource: Prometheus (http://prometheus:9090)
- Dashboard: `monitoring/grafana/dashboards/complete-dashboard.json`

## 🐛 Sorun Giderme

### PushGateway Bağlantı Sorunu
```bash
# PushGateway'ın çalışıp çalışmadığını kontrol edin
docker-compose ps pushgateway

# PushGateway loglarını kontrol edin
docker-compose logs pushgateway
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
2. PushGateway target'ının UP olduğundan emin olun
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

1. **Test Success Rate**: Yüzde olarak başarılı test oranı
2. **Test Execution Count**: Belirli zaman diliminde çalışan test sayısı
3. **Test Duration**: Test süreleri histogramı
4. **Page Load Times**: Sayfa yükleme süreleri
5. **Browser Memory Usage**: Tarayıcı bellek kullanımı (MB)
6. **Failed Tests Count**: Başarısız test sayısı
7. **Container CPU/Memory**: Sistem kaynak kullanımı

### Dashboard Panelleri:
- **Test Success Rate**: Başarı oranı trendi (%)
- **Test Execution Count**: Test çalıştırma sayısı
- **Test Duration**: Test süreleri (saniye)
- **Page Load Times**: Sayfa yükleme süreleri (saniye)
- **Browser Memory Usage**: Tarayıcı bellek kullanımı (MB)
- **Container CPU Usage**: CPU kullanımı (%)
- **Container Memory Usage**: Bellek kullanımı (MiB)

## 📊 Örnek Kullanım Senaryosu

1. **Monitoring stack'i başlatın:**
   ```bash
   docker-compose up -d
   ```

2. **IDE'de testleri çalıştırın:**
   - IntelliJ IDEA veya Eclipse açın
   - `LoginTest.java` veya `ProductTest.java` çalıştırın

3. **Grafana'da izleyin:**
   - http://localhost:3000 (admin/admin123)
   - Testlerin gerçek zamanlı çalışmasını görün
   - Başarı oranını takip edin
   - Test sürelerini analiz edin

4. **Performans Analizi:**
   - Hangi testlerin yavaş olduğunu tespit edin
   - Başarısızlık nedenlerini analiz edin
   - Sayfa yükleme performansını değerlendirin

## 🔄 Sürekli Monitoring

PushGateway test suite tamamlandıktan sonra da çalışmaya devam eder. Bu sayede:
- Sürekli monitoring yapabilirsiniz
- Geçmiş metrikleri karşılaştırabilirsiniz
- Trend analizi yapabilirsiniz

## 📞 Destek

Sorun yaşarsanız:
1. Docker servislerinin çalıştığını kontrol edin
2. PushGateway'ın çalıştığını kontrol edin
3. Test loglarını inceleyin
4. Prometheus targets'larını kontrol edin

---

**Not**: Bu proje Windows ortamında test edilmiştir. Linux/Mac için script'leri uyarlamanız gerekebilir. 
