# 📌 ITDP3 Project

## Kelompok 6

**Anggota:**
- Tio Hardadi Somantri
- Raffi Alfarizi Hadianto
- Khoirunnisa Putri Kania
- Muhammad Alwi Nugraha

---

# **📌 API Contract - User, Exchange Rate, Transaksi Exchange, Deposit & Withdraw**  

Format respons tetap seragam:  
```json
{
  "status": "success",
  "message": "Deskripsi singkat",
  "data": { ... }
}
```
Jika terjadi kesalahan:  
```json
{
  "status": "error",
  "message": "Deskripsi kesalahan"
}
```

---

## **1️⃣ User API (CRUD User + Deposit & Withdraw)**
Mengelola data pengguna, termasuk saldo dalam IDR, USD, dan JPY.

### **1.1. Get All Users**
- **Endpoint:** `GET /users`
- **Response:**
  ```json
  {
    "status": "success",
    "message": "Users retrieved successfully",
    "data": [
      {
        "id": 1,
        "name": "John Doe",
        "email": "john@example.com",
        "address": "Jl. Mawar No. 10",
        "gender": "Male",
        "balance": {
          "IDR": 500000,
          "USD": 100,
          "JPY": 10000
        }
      }
    ]
  }
  ```

### **1.2. Get User by ID**
- **Endpoint:** `GET /users/{id}`
- **Response sama seperti Get All Users tetapi hanya satu data user.**

### **1.3. Create User**
- **Endpoint:** `POST /users`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com",
    "address": "Jl. Mawar No. 10",
    "gender": "Male"
  }
  ```
- **Saldo Awal:**  
  - **IDR: 500,000**  
  - **USD: 100**  
  - **JPY: 10,000**
- **Response (201 Created):**
  ```json
  {
    "status": "success",
    "message": "User created successfully",
    "data": {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "address": "Jl. Mawar No. 10",
      "gender": "Male",
      "balance": {
        "IDR": 500000,
        "USD": 100,
        "JPY": 10000
      }
    }
  }
  ```

### **1.4. Update User**
- **Endpoint:** `PUT /users/{id}`
- **Request Body:**
  ```json
  {
    "name": "Jane Doe",
    "email": "jane@example.com",
    "address": "Jl. Melati No. 20",
    "gender": "Female"
  }
  ```

### **1.5. Delete User**
- **Endpoint:** `DELETE /users/{id}`

---

## **2️⃣ Deposit & Withdraw API**  

### **2.1. Deposit**  
Menambah saldo user dalam mata uang tertentu.  
- **Endpoint:** `POST /deposit`
- **Request Body:**  
  ```json
  {
    "user_id": 1,
    "currency": "IDR",
    "amount": 50000
  }
  ```
- **Response:**
  ```json
  {
    "status": "success",
    "message": "Deposit successful",
    "data": {
      "user_id": 1,
      "currency": "IDR",
      "amount": 50000,
      "new_balance": {
        "IDR": 550000,
        "USD": 100,
        "JPY": 10000
      }
    }
  }
  ```

### **2.2. Withdraw**  
Menarik saldo user dalam mata uang tertentu.  
- **Endpoint:** `POST /withdraw`
- **Request Body:**  
  ```json
  {
    "user_id": 1,
    "currency": "USD",
    "amount": 50
  }
  ```
- **Response (Success):**
  ```json
  {
    "status": "success",
    "message": "Withdrawal successful",
    "data": {
      "user_id": 1,
      "currency": "USD",
      "amount": 50,
      "new_balance": {
        "IDR": 500000,
        "USD": 50,
        "JPY": 10000
      }
    }
  }
  ```
- **Response (Error - Saldo Tidak Cukup):**  
  ```json
  {
    "status": "error",
    "message": "Insufficient balance"
  }
  ```

---

## **3️⃣ Exchange Rate API (CRUD Rate)**
Mengelola nilai tukar mata uang, termasuk JPY.

### **3.1. Get All Exchange Rates**
- **Endpoint:** `GET /rates`

### **3.2. Create Exchange Rate**
- **Endpoint:** `POST /rates`
- **Request Body:**
  ```json
  {
    "from_currency": "IDR",
    "to_currency": "JPY",
    "rate": 0.0095
  }
  ```

### **3.3. Update Exchange Rate**
- **Endpoint:** `PUT /rates/{id}`

### **3.4. Delete Exchange Rate**
- **Endpoint:** `DELETE /rates/{id}`

---

# **🚀 Feature**  
✅ **CRUD User** dengan saldo **IDR, USD, JPY**  
✅ **Detail akun user (nama, email, alamat, gender)**  
✅ **CRUD Exchange Rate**  
✅ **Transaksi Exchange**
✅ **Histori Transaksi**  
✅ **Fitur Deposit & Withdraw**  

