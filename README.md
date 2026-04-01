# 📋 Form Registrasi App
**Tugas Praktikum Mobile Programming - Week 5**

## 👤 Identitas
- **Nama:** Ahmad Agung Maulana
- **Mata Kuliah:** Pemrograman Mobile
- **Pertemuan:** Week 5

## 📱 Deskripsi Aplikasi
Aplikasi Form Registrasi Android yang dibuat menggunakan Kotlin dan Android Studio.
Aplikasi ini memiliki fitur lengkap mulai dari login, registrasi, hingga validasi form.

## ✨ Fitur Aplikasi

### 🏠 Welcome Screen
- Halaman awal dengan pilihan Login atau Daftar
- Tampilan modern dan bersih

### 🔐 Halaman Login
- Input Email & Password dengan TextInputLayout
- Validasi real-time format email
- Validasi panjang password minimal 6 karakter
- Link menuju halaman registrasi

### 📝 Halaman Registrasi
- **TextInputLayout** untuk Nama, Email, Password, Confirm Password
- **Validasi Real-time:**
  - Tidak boleh kosong
  - Format email valid
  - Password minimal 6 karakter
  - Konfirmasi password harus cocok
- **RadioGroup** pilihan Jenis Kelamin (Laki-laki / Perempuan)
- **CheckBox** pilihan Hobi minimal 3 (Membaca, Olahraga, Musik, Gaming, Memasak)
- **Spinner** pilihan Asal Kota (Jakarta, Bandung, Surabaya, Yogyakarta, Medan, Makassar)
- **AlertDialog** konfirmasi data sebelum submit
- **Gesture Interaction** Long Press pada tombol DAFTAR

## 🛠️ Teknologi yang Digunakan
- **Bahasa:** Kotlin
- **IDE:** Android Studio
- **Minimum SDK:** API 24 (Android 7.0)
- **Library:** Material Design Components

## 📸 Tampilan Aplikasi
| Welcome Screen | Login | Registrasi |
|---|---|---|
| (screenshot) | (screenshot) | (screenshot) |

## 🚀 Cara Menjalankan
1. Clone repository ini
2. Buka dengan Android Studio
3. Jalankan di emulator atau device Android (min. API 24)

## 📂 Struktur Project
```
app/
├── src/main/
│   ├── java/com/example/formregistrasi/
│   │   ├── WelcomeActivity.kt
│   │   ├── LoginActivity.kt
│   │   └── MainActivity.kt
│   └── res/
│       ├── layout/
│       │   ├── activity_welcome.xml
│       │   ├── activity_login.xml
│       │   └── activity_main.xml
│       └── drawable/
│           └── spinner_border.xml
```
