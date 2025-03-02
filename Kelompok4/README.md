# Kelompok 4 - Sistem Transaksi Valuta Asing

## Anggota Kelompok:
- **Bintang Samudro**
- **Hafizh Daffa Septianto**
- **Faisal Aulia Ghani**
- **Dewi Ilmi Rizqi**

## Deskripsi Proyek
Sistem ini dirancang untuk mengelola transaksi valuta asing berdasarkan nilai tukar yang ada di pasar keuangan. Sistem mencakup manajemen pelanggan, akun pelanggan, transaksi pertukaran mata uang, dan nilai tukar pasar valuta asing.

## Pembagian Tugas
### **Bintang Samudro**
- Bertanggung jawab atas parameter **Transaction** (pencatatan transaksi pertukaran mata uang).
- Mencakup pembuatan **entity, repository, service, controller**.
- Penjelasan API.
- Pembuatan **SQL (DDL dan DML)**.

### **Hafizh Daffa Septianto**
- Bertanggung jawab atas parameter **Foreign Exchange Market** (pengelolaan nilai tukar antar mata uang).
- Mencakup **entity, repository, service, controller**.
- Merancang **API program update dan delete** pada setiap class.
- Pembuatan **API Documentation**.

### **Faisal Aulia Ghani**
- Bertanggung jawab atas parameter **Customer Account** (mengelola akun customer/rekening customer).
- Mencakup **entity, repository, service, controller**.

### **Dewi Ilmi Rizqi**
- Bertanggung jawab atas parameter **Customer** (penyimpanan dan pengelolaan data customer).
- Mencakup **entity, repository, service, controller**.
- Pembuatan **Diagram Schema**.

---

## API Documentation
![Image](https://drive.google.com/uc?id=1Lw9ai6xGXZS5i5XdGHIxPiA7uqiJylv2)
![Image](https://drive.google.com/uc?id=14pYmG-T7uEcTsF8CWlgMBT-VUEsZaOmx)

## **API Utama**
- `[GET] /api/v1/getAllCustomerAccountByCustomerId` → Mengambil seluruh rekening nasabah berdasarkan ID customer.
- `[GET] /api/v1/getBalanceByCustomerAccount` → Mengambil saldo berdasarkan nomor rekening.
- `[GET] /api/v1/getAllTransactionByCustomerId` → Mengambil seluruh transaksi berdasarkan ID customer.
- `[GET] /api/v1/getForeignExchangeMarket` → Mengambil data kurs terbaru berdasarkan currency tujuan. Sebelum melakukan ini harap untuk menambahkan data pada ForeignExchangeMarket
- `[POST] /api/v1/createTransactionExchange` → Melakukan konversi dari IDR ke mata uang lain. Sebelum melakukan ini harap untuk menambahkan data pada ForeignExchangeMarket

**Request Body untuk /api/v1/createTransactionExchange**
```json
{
  "fromAccount": "1223", // berisikan rekening nasabah tipe IDR
  "toAccount": "4567", // berisikan rekening nasabah tipe Valas
  "fromCurrency": "IDR", 
  "toCurrency": "USD",
  "amount": 20000 // rupiah yang mau di tukar menjadi valas
}
```

**Response (Jika berhasil)**
```json
{
  "id": 3,
  "dateTime": "2025-03-02T22:13:36.1580622",
  "fromAccount": "1223",
  "toAccount": "4567",
  "fromCurrency": "IDR",
  "toCurrency": "USD",
  "amount": 20000,
  "exchangeRate": {
    "id": 1,
    "currencyFrom": "IDR",
    "currencyTo": "USD",
    "exchangePrice": 16000,
    "updatedAt": "2025-03-11T01:51:36"
  },
  "exchangeRateAtTransaction": 16000,
  "convertedAmount": 1.25,
  "fromAccountBalanceAfter": 10000,
  "toAccountBalanceAfter": 8.75,
  "status": "SUCCESS"
}
```

## API CRUD (Tambahan)
### **Endpoint untuk Customer Account**
- `[POST] /api/v1/createCustomerAccount` → Membuat akun customer.
- `[PUT] /api/v1/updateCustomerAccount` → Memperbarui informasi akun customer.
- `[DELETE] /api/v1/deleteCustomerAccount` → Menghapus akun customer.
- `[POST] /api/v1/depositCustomerAccount` → Menambahkan saldo ke akun customer/rekening customer.


### **Endpoint untuk Transaksi**
- `[PUT] /api/v1/updateTransaction` → Memperbarui transaksi yang sudah ada dalam sistem.
- `[DELETE] /api/v1/deleteTransaction` → Menghapus transaksi tertentu.

### **Endpoint untuk Foreign Exchange Market**
- `[POST] /api/v1/createForeignExchangeMarket` → Menambahkan data baru tentang Foreign Exchange Market.
- `[PUT] /api/v1/updateForeignExchangeMarket` → Memperbarui data Foreign Exchange Market.
- `[DELETE] /api/v1/deleteForeignMarket` → Menghapus data pasar valuta asing tertentu.

### **Endpoint untuk Customer**
- `[GET] /api/v1/getCustomer` → Mengambil informasi customer berdasarkan ID.
- `[POST] /api/v1/createCustomer` → Membuat customer baru.
- `[PUT] /api/v1/updateCustomer` → Memperbarui informasi customer.
- `[DELETE] /api/v1/deleteCustomer` → Menghapus customer dari sistem.

---

## Database Schema
![Image](https://drive.google.com/uc?id=1TCc1BTVRX1mX6kqoEXAcs82q4XuGsMmV)
### **Entitas dalam Sistem**
#### **Customer**
| Nama Kolom  | Tipe Data | Keterangan |
|-------------|----------|------------|
| id_customer | INT (PK) | Identitas unik customer |
| name        | VARCHAR  | Nama customer |
| address     | VARCHAR  | Alamat customer |
| gender      | VARCHAR  | Jenis kelamin customer |

#### **Customer Account**
| Nama Kolom           | Tipe Data | Keterangan |
|----------------------|----------|------------|
| id_customer_account | INT (PK) | Identitas unik akun customer |
| id_customer        | INT (FK)  | Foreign key ke Customer |
| account           | VARCHAR  | Nomor rekening customer |
| balance          | VARCHAR   | Saldo customer |
| type            | VARCHAR   | Jenis mata uang (USD, IDR, dll.) |

#### **Foreign Exchange Market**
| Nama Kolom             | Tipe Data | Keterangan |
|------------------------|----------|------------|
| id_foreign_exchange_market | INT (PK) | Identitas unik nilai tukar |
| currency_from          | VARCHAR  | Mata uang asal |
| currency_to           | VARCHAR  | Mata uang tujuan |
| exchange_price        | DECIMAL   | Nilai tukar |
| update_at             | DATETIME  | Waktu terakhir diperbarui |

#### **Transaction**
| Nama Kolom                   | Tipe Data | Keterangan |
|------------------------------|----------|------------|
| id_transaction               | INT (PK) | Identitas unik transaksi |
| id_customer                  | INT (FK) | Foreign key ke Customer |
| id_foreign_exchange_market   | INT (FK) | Foreign key ke Foreign Exchange Market |
| date_time                    | DATETIME  | Tanggal dan waktu transaksi |
| from_account                 | VARCHAR  | Akun asal transaksi |
| to_account                   | VARCHAR  | Akun tujuan transaksi |
| from_currency                | VARCHAR  | Mata uang asal transaksi |
| to_currency                  | VARCHAR  | Mata uang tujuan transaksi |
| amount                       | DECIMAL  | Jumlah uang dalam mata uang asal |
| exchange_rate_at_transaction | DECIMAL  | Nilai tukar pada transaksi |
| convert_amount               | DECIMAL  | Jumlah uang setelah konversi |
| from_account_after_balance   | DECIMAL  | Saldo akun asal setelah transaksi |
| to_account_after_balance     | DECIMAL  | Saldo akun tujuan setelah transaksi |

---

## Hubungan Kardinalitas Antar Tabel
1. **Customer ↔ Customer Account** → Satu customer dapat memiliki banyak rekening (**one-to-many**).
2. **Customer ↔ Transaction** → Satu customer dapat melakukan banyak transaksi (**one-to-many**).
3. **Transaction ↔ Foreign Exchange Market** → Satu nilai tukar dapat digunakan oleh banyak transaksi (**many-to-one**).

---

## Cara Menjalankan Proyek
1. Clone repositori ini.
```sh
 git clone https://github.com/username/repository.git
```
2. Konfigurasi database sesuai skema di atas.
3. Jalankan server backend menggunakan Spring Boot atau framework yang digunakan.
4. Gunakan **Postman** atau **Swagger** untuk menguji API yang telah dibuat.

---
