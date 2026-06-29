# HayvanÖğren 

> Çocuklar ve yeni öğrenenler için hayvanları görsel ve eğlenceli bir şekilde tanıtmayı amaçlayan, kullanıcı dostu bir Android uygulaması.

HayvanÖğren, kullanıcıların farklı hayvan türlerini keşfetmesini, isimlerini, seslerini ve temel özelliklerini öğrenmesini sağlayan interaktif bir mobil uygulamadır. Geliştirme sürecinde temiz kod prensipleri ve optimize edilmiş XML tasarımları ön planda tutulmuştur.

## ✨ Özellikler

* **Görsel Kartlar:** Her hayvana özel yüksek kaliteli görseller barındıran kart tasarımları.
* **Kategori Sistemi:** Hayvanları (Evcil, Vahşi, Deniz Canlıları vb.) kolayca filtreleme seçeneği.
* **İnteraktif Arayüz:** Kullanıcı deneyimini artıran, gecikmesiz ve akıcı XML tasarımları.
* **Ses Desteği (Opsiyonel):** Hayvanların seslerini dinleyerek öğrenmeyi pekiştirme imkanı.

## 🛠️ Kullanılan Teknolojiler ve Araçlar

Uygulamanın geliştirilmesinde aşağıdaki modern Android geliştirme teknolojileri kullanılmıştır:

| Teknoloji / Araç | Kullanım Amacı | Versiyon / Detay |
| :--- | :--- | :--- |
| **Android Studio** | Resmi Entegre Geliştirme Ortamı (IDE) | Ladybug / Koala (Güncel) |
| **Kotlin / Java** | Uygulama Mantığı ve Kodlama Dili | Modern Android Standartları |
| **XML** | Kullanıcı Arayüzü (UI) ve Düzen Tasarımları | View-Based Layouts |
| **Gradle** | Proje Yapılandırma ve Bağımlılık Yönetimi | Kotlin DSL / Groovy |

## 🚀 Kurulum ve Çalıştırma

Projeyi kendi bilgisayarınızda açmak ve yerel bir emülatörde veya gerçek cihazda test etmek için aşağıdaki adımları takip edebilirsiniz:

### Gereksinimler

* **Android Studio** (En son kararlı sürüm önerilir)
* **Android SDK** (API 21 veya üzeri)
* Sisteminizde **Git** kurulu olmalıdır.

### Kurulum Adımları

1.  **Projeyi Klonlayın:**
    Terminalinizi veya Git Bash'i açıp projeyi bilgisayarınıza indirin:
    ```bash
    git clone [https://github.com/suleymantahasakalli/HayvanOgren.git](https://github.com/suleymantahasakalli/HayvanOgren.git)
    cd HayvanOgren
    ```

2.  **Android Studio ile Açın:**
    * Android Studio'yu başlatın.
    * `File -> Open` (Dosya -> Aç) seçeneğine tıklayın.
    * Projeyi indirdiğiniz `HayvanOgren` klasörünü seçip `OK` butonuna basın.

3.  **Gradle Senkronizasyonu:**
    * Proje ilk defa açıldığında Android Studio bağımlılıkları otomatik olarak indirecektir.
    * Eğer başlamazsa, sağ üst köşedeki **"Sync Project with Gradle Files"** (Fil simgesi) butonuna tıklayın.

4.  **Uygulamayı Çalıştırın:**
    * Bir emülatör (AVD) seçin veya fiziksel Android cihazınızı USB hata ayıklama modu açık şekilde bilgisayara bağlayın.
    * Üst menüdeki yeşil **"Run" (Oynat)** butonuna basarak uygulamayı cihazınıza yükleyin.

## 📁 Proje Yapısı

```text
HayvanOgren/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/hayvanogren/  # Kaynak kodlar (Activity, Adapter, Model)
│   │   │   └── res/
│   │   │       ├── layout/                    # XML Arayüz tasarımları
│   │   │       ├── drawable/                  # Hayvan görselleri ve ikonlar
│   │   │       └── values/                    # Renk, yazı ve tema tanımlamaları
│   └── build.gradle                           # Modül düzeyinde Gradle ayarları
└── build.gradle                               # Proje düzeyinde Gradle ayarları
