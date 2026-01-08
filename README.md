# Tellink - Telkom University Link

<div align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue.svg" alt="Language">
  <img src="https://img.shields.io/badge/MinSDK-26-orange.svg" alt="Min SDK">
  <img src="https://img.shields.io/badge/TargetSDK-34-orange.svg" alt="Target SDK">
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License">
</div>

## 📋 Deskripsi

**Tellink** adalah aplikasi mobile berbasis Android yang dirancang khusus untuk menghubungkan mahasiswa Telkom University dalam berbagi dan berkolaborasi pada proyek-proyek akademik maupun non-akademik. Aplikasi ini menyediakan platform interaktif dimana mahasiswa dapat memposting proyek mereka, mencari kolaborator, memberikan like dan komentar, serta mengelola permintaan kolaborasi.

## ✨ Fitur Utama

### 🔐 Autentikasi
- **Login & Register**: Sistem autentikasi aman menggunakan NIM dan password
- **Manajemen Sesi**: Pengelolaan sesi pengguna yang persisten

### 🏠 Beranda (Home)
- **Feed Proyek**: Menampilkan daftar proyek terbaru dari mahasiswa
- **Recently Hot**: Menampilkan proyek yang sedang trending
- **Pencarian**: Fitur pencarian proyek berdasarkan judul atau konten
- **Filter Tag**: Pencarian proyek berdasarkan tag/kategori

### 📝 Manajemen Proyek
- **Tambah Proyek**: Membuat posting proyek baru dengan detail lengkap
  - Judul proyek
  - Deskripsi lengkap
  - Upload gambar
  - Penambahan tag/kategori
- **Edit Proyek**: Mengubah informasi proyek yang sudah dibuat
- **Hapus Proyek**: Menghapus proyek yang tidak diperlukan lagi

### 💬 Interaksi Sosial
- **Like System**: Memberikan like pada proyek yang menarik
- **Komentar**: Berdiskusi dan memberikan feedback pada proyek
- **Request Kolaborasi**: Mengajukan permintaan untuk bergabung dalam proyek
- **Accept/Reject**: Mengelola permintaan kolaborasi dari mahasiswa lain

### 👤 Profil Mahasiswa
- Informasi mahasiswa (NIM, Nama, Jurusan, Angkatan)
- Daftar proyek yang dibuat
- Statistik posting

## 🛠️ Teknologi Stack

### Framework & Library
- **Jetpack Compose**: Modern UI toolkit untuk Android
- **Material Design 3**: Komponen UI mengikuti design guidelines terbaru
- **Kotlin**: Bahasa pemrograman utama

### Architecture & Pattern
- **MVVM (Model-View-ViewModel)**: Clean architecture pattern
- **Dependency Injection**: Dagger Hilt untuk manajemen dependency
- **Navigation Component**: Jetpack Navigation dengan Compose

### Backend & Storage
- **Firebase Firestore**: Real-time NoSQL cloud database
- **Firebase Storage**: Cloud storage untuk media files
- **Retrofit**: HTTP client untuk REST API calls

### UI/UX Libraries
- **Coil**: Image loading library untuk Compose
- **Glide**: Image loading dan caching
- **Material Icons Extended**: Icon set lengkap

### Development Tools
- **KSP (Kotlin Symbol Processing)**: Annotation processing
- **Gradle Version Catalog**: Centralized dependency management

## 📱 Persyaratan Sistem

### Minimum Requirements
- **Android Version**: Android 8.0 (Oreo) - API Level 26
- **RAM**: 2 GB
- **Storage**: 100 MB free space
- **Internet**: Koneksi internet aktif

### Development Requirements
- **Android Studio**: Hedgehog (2023.1.1) atau lebih baru
- **JDK**: Java 19 atau lebih baru
- **Gradle**: 8.7.2
- **Kotlin**: 2.0.21

## 🚀 Instalasi & Setup

### 1. Clone Repository
```bash
git clone https://github.com/dxvnee/Tellink-3.git
cd Tellink-3
```

### 2. Konfigurasi Firebase

#### a. Buat Project Firebase
1. Buka [Firebase Console](https://console.firebase.google.com/)
2. Klik "Add Project" atau "Create a project"
3. Masukkan nama project (misalnya: "Tellink")
4. Ikuti wizard setup hingga selesai

#### b. Tambah Android App ke Firebase Project
1. Di Firebase Console, pilih project Anda
2. Klik icon Android untuk menambah Android app
3. Masukkan package name: `org.d3if3121.tellink`
4. (Optional) Masukkan app nickname dan SHA-1
5. Download file `google-services.json`

#### c. Setup google-services.json
```bash
# Letakkan file google-services.json di folder app/
cp /path/to/downloaded/google-services.json app/google-services.json
```

#### d. Enable Firebase Services
Di Firebase Console, aktifkan:
- **Firestore Database**:
  - Buka "Firestore Database"
  - Klik "Create database"
  - Pilih mode (production atau test)
  - Pilih region server
  
- **Firebase Storage**:
  - Buka "Storage"
  - Klik "Get started"
  - Setup security rules sesuai kebutuhan

#### e. Setup Firestore Collections
Buat collections berikut di Firestore:
- `mahasiswa` - Untuk data mahasiswa
  - Fields: nim, nama, password, jurusan, angkatan, posts, totalpost, requests, accept
- `projects` - Untuk data proyek
  - Fields: id, nim, title, desc, date, commentCount, requests, accept, image, tag, likes

### 3. Build Project

#### Sync & Build
```bash
# Sync project with Gradle files
./gradlew build

# Atau melalui Android Studio:
# File -> Sync Project with Gradle Files
```

### 4. Run Aplikasi

#### Via Android Studio
1. Buka project di Android Studio
2. Pilih device/emulator
3. Klik tombol "Run" (▶️) atau tekan `Shift + F10`

#### Via Command Line
```bash
# Install ke connected device/emulator
./gradlew installDebug

# Run app
adb shell am start -n org.d3if3121.tellink/.ui.MainActivity
```

## 📁 Struktur Project

```
Tellink-3/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/d3if3121/tellink/
│   │   │   │   ├── components/          # Reusable UI components
│   │   │   │   ├── data/                # Data layer
│   │   │   │   │   ├── model/           # Data models
│   │   │   │   │   │   ├── mahasiswa/   # Student models
│   │   │   │   │   │   ├── project/     # Project models
│   │   │   │   │   │   ├── comment/     # Comment models
│   │   │   │   │   │   └── response/    # API response models
│   │   │   │   │   ├── repository/      # Repository pattern
│   │   │   │   │   └── retrofit/        # API services
│   │   │   │   ├── di/                  # Dependency injection (Hilt modules)
│   │   │   │   ├── navigation/          # Navigation graphs
│   │   │   │   ├── ui/                  # UI layer
│   │   │   │   │   ├── animation/       # Custom animations
│   │   │   │   │   ├── component/       # Common UI components
│   │   │   │   │   ├── formula/         # UI calculations
│   │   │   │   │   ├── screen/          # App screens
│   │   │   │   │   │   ├── auth/        # Authentication screens
│   │   │   │   │   │   │   ├── login/
│   │   │   │   │   │   │   └── register/
│   │   │   │   │   │   └── content/     # Main content screens
│   │   │   │   │   │       ├── homepage/      # Home feed
│   │   │   │   │   │       ├── projectpage/   # Project details
│   │   │   │   │   │       ├── likepage/      # Liked projects
│   │   │   │   │   │       └── commentpage/   # Comments
│   │   │   │   │   ├── theme/           # App theming
│   │   │   │   │   └── viewmodel/       # Shared ViewModels
│   │   │   │   └── TellinkApp.kt        # Application class
│   │   │   ├── res/                     # Resources
│   │   │   │   ├── drawable/            # Images & icons
│   │   │   │   ├── mipmap/              # App icons
│   │   │   │   ├── values/              # Strings, colors, themes
│   │   │   │   └── xml/                 # XML configs
│   │   │   └── AndroidManifest.xml      # App manifest
│   │   ├── androidTest/                 # Instrumented tests
│   │   └── test/                        # Unit tests
│   ├── build.gradle.kts                 # App-level build config
│   └── google-services.json             # Firebase config
├── gradle/
│   └── libs.versions.toml               # Dependency versions
├── build.gradle.kts                     # Project-level build config
├── settings.gradle.kts                  # Gradle settings
└── README.md                            # This file
```

## 🏗️ Arsitektur Aplikasi

### MVVM Architecture

```
┌─────────────────┐
│   UI Layer      │  <- Jetpack Compose UI
│  (Composables)  │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   ViewModel     │  <- Business Logic & State Management
│                 │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   Repository    │  <- Data Access Layer
│                 │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│  Data Sources   │  <- Firebase, Retrofit, Local Storage
│  (Remote/Local) │
└─────────────────┘
```

### Key Components

#### 1. UI Layer (Jetpack Compose)
- Composable functions untuk UI
- Material Design 3 components
- Navigation dengan Compose

#### 2. ViewModel Layer
- Mengelola UI state
- Business logic
- LiveData/StateFlow untuk reaktif UI

#### 3. Repository Layer
- Single source of truth
- Abstract data sources
- Handle data operations

#### 4. Data Layer
- Firebase Firestore integration
- Retrofit untuk API calls
- Model classes

#### 5. Dependency Injection (Hilt)
- Module injection
- ViewModel injection
- Repository injection

## 🔧 Konfigurasi

### Build Variants
```kotlin
// Debug Build
buildTypes {
    debug {
        isMinifyEnabled = false
        applicationIdSuffix = ".debug"
    }
}

// Release Build
buildTypes {
    release {
        isMinifyEnabled = true
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }
}
```

### ProGuard Rules
Untuk release build, ProGuard rules sudah dikonfigurasi di `proguard-rules.pro`

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Run Specific Test
```bash
./gradlew test --tests "TestClassName"
```

## 🎨 Design System

### Color Palette
Aplikasi menggunakan Material Design 3 dengan custom color palette yang dapat ditemukan di `ui/theme/Color.kt`

### Typography
Custom typography system menggunakan Inter font family di `ui/theme/Type.kt`

### Components
Reusable components tersedia di folder `ui/component/` dan `components/`

## 📚 Dependencies

### Core Dependencies
- **Jetpack Compose BOM**: 2024.11.00
- **Kotlin**: 2.0.21
- **Dagger Hilt**: 2.52
- **Firebase BOM**: 33.6.0

### Full dependencies list
Lihat file `gradle/libs.versions.toml` untuk daftar lengkap dependencies dan versioning.

## 🤝 Contributing

Kontribusi sangat diterima! Untuk berkontribusi:

1. Fork repository ini
2. Buat feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buat Pull Request

### Coding Standards
- Ikuti [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Gunakan meaningful variable/function names
- Tambahkan comments untuk logic yang kompleks
- Write clean and maintainable code

## 🐛 Bug Reports & Feature Requests

Jika menemukan bug atau ingin request feature baru:
1. Buka [Issues](https://github.com/dxvnee/Tellink-3/issues)
2. Gunakan template yang sesuai
3. Berikan detail yang jelas dan lengkap

## 📄 License

Project ini dilisensikan di bawah MIT License. Lihat file `LICENSE` untuk detail lebih lanjut.

## 👥 Tim Pengembang

- **Muhammad Raihan Fahrifi** - Developer
- **dxvnee** - GitHub Repository Owner

## 📞 Kontak

- **Project Link**: [https://github.com/dxvnee/Tellink-3](https://github.com/dxvnee/Tellink-3)
- **Organization**: Telkom University
- **Program**: D3IF3121

## 🙏 Acknowledgments

- Telkom University untuk dukungan akademik
- Firebase untuk backend infrastructure
- Jetpack Compose team untuk modern UI toolkit
- Open source community untuk libraries yang digunakan

## 📝 Changelog

### Version 1.0 (Current)
- Initial release
- Authentication system (Login & Register)
- Project management (CRUD operations)
- Social features (Like, Comment, Request)
- Firebase integration
- Material Design 3 UI

---

<div align="center">
  <p>Made with ❤️ by Telkom University Students</p>
  <p>© 2024 Tellink. All rights reserved.</p>
</div>
