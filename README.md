# Mini-Wallet Backend Service

## 📌 Overview
This project is a backend implementation of a **Mini-Wallet system** that allows users to:
- Create a wallet account with an initial balance
- Check account balance
- Transfer money between users

---

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory for local development) 
- Maven

> Note: The application is database-agnostic and can be configured to use PostgreSQL if required.

---

## ▶️ How to Run the Application Locally

### Prerequisites
- Java 17
- Maven

### Steps
```bash
mvn clean install
mvn spring-boot:run
```

The server will start at:
```
http://localhost:8080
```

---

## 📡 API Endpoints

### 1️⃣ Create Account
**POST** `/wallet/create`

Parameters:
- name – User name
- balance – Initial balance

Example:
```
/wallet/create?name=Alice&balance=100
```

---

### 2️⃣ Get Balance
**GET** `/wallet/balance/{userId}`

Example:
```
/wallet/balance/1
```

---

### 3️⃣ Transfer Money
**POST** `/wallet/transfer`

Parameters:
- fromId – Sender user ID
- toId – Receiver user ID
- amount – Amount to transfer

Example:
```
/wallet/transfer?fromId=1&toId=2&amount=50
```

---

## 🗄 Data Model

### User Entity

| Field | Type | Description |
|------|------|-------------|
| id | Long | Primary key |
| name | String | User name |
| balance | BigDecimal | Wallet balance |

---

## 🔗 Data Relationships
- Each user has exactly one wallet
- Wallet balance is stored in the users table
- One-to-one relationship between user and wallet balance

---

## 🔐 Data Integrity & Reliability
- Negative balances are prevented by validation
- Debit and credit operations are wrapped in a single transaction using @Transactional
- If any step in a transfer fails, the entire operation is rolled back
- Monetary values use BigDecimal to avoid floating-point precision errors

---

## 🧪 Testing

An integration test is included to validate the money transfer functionality.

Test Scenario:
- Alice starts with balance 100
- Bob starts with balance 50
- Alice transfers 40 to Bob
- Final balances:
  - Alice → 60
  - Bob → 90

Run tests:
```bash
mvn test
```

---

## 🗃 Database Configuration

### Default (H2 – Local)
- In-memory database
- No installation required
- Tables are auto-created on startup

### Optional (PostgreSQL)
The application can be configured to use PostgreSQL by updating database properties or using Spring profiles.

---

## ✅ Conclusion
This project demonstrates a reliable backend solution with:
- Transactional safety
- Proper testing
- Clear API design
- Strong data integrity guarantees
