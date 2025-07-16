# 🎓 **Tuition Handler**  

[![Java](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com/)  
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue)](https://www.mysql.com/)  
![Status](https://img.shields.io/badge/Status-Completed-success)  
![IDE](https://img.shields.io/badge/Editor-VS%20Code%20%7C%20Notepad-lightgrey)  
![GUI](https://img.shields.io/badge/GUI-Java%20Swing-orange)  
![Email](https://img.shields.io/badge/Email-JavaMail%20API-green)  
![License](https://img.shields.io/badge/License-MIT-blue)  
![PRs](https://img.shields.io/badge/PRs-Welcome-brightgreen)  

**Tuition Handler** is a **Java-based desktop application** designed to simplify **tuition class management**.  
It helps administrators efficiently manage **student records, batches, and courses**, while automatically  
**generating payment receipts and sending them via email**.

Built with **Java Swing** for the GUI and **MySQL** for database management, it ensures smooth performance  
with **multi-threaded background operations** for receipt generation and email notifications.

---

## 🚀 **Quick Start**

```bash
# Clone the Repository
git clone https://github.com/YourUsername/Tuition_Handler.git
cd Tuition_Handler

# Create Database
# (Copy-paste SQL script from "Database Setup" section below into MySQL)

# Update DB Credentials in utility/DB.java
# Run the Application (Windows)
java -classpath ".;D:\realsoft;D:\realsoft\utility\lib\jdatepicker-1.3.4.jar;D:\realsoft\lib\mysql-connector-j-8.1.0.jar;D:\realsoft\lib\mail.jar;D:\realsoft;D:\realsoft\lib\activation.jar;D:\realsoft\lib\smtp-1.4.4.jar" application.LandingPage
```

## 🔑 **Default Login**

* **Username:** `admin`  
* **Password:** `admin`  

---
<!--
## ✨ **Features**

✅ **Student Management** – Add, update, and view student details  
✅ **Batch, Course & Trade Handling** – Organize students by academic details  
✅ **Receipt Generation & Email Sending** – Auto-generate & email payment receipts  
✅ **City & College Database** – Maintain structured city-wise & college-wise records  
✅ **Secure Login** – Admin-only login access  
✅ **Multi-threading** – Background email/receipt operations without UI freezing  

---
 -->
## ✨ **Features**

- 👨‍🎓 **Student Management**  
  Add, update, and view student details with ease.

- 📚 **Batch, Course & Trade Handling**  
  Organize students by batches, courses, and trades efficiently.

- 🧾 **Automated Receipt Generation & Emailing**  
  Automatically generate payment receipts and send them via email.

- 🗂 **City & College Database**  
  Maintain structured records city-wise and college-wise.

- 🔐 **Secure Admin Login**  
  Access restricted to authorized users only.

- ⚙️ **Multi-threading Support**  
  Performs background tasks like email sending without freezing the UI.

---
## 🛠 **Tech Stack**

| **Category**  | **Technology**                                  |
| ------------- | ------------------------------------------------|
| **Language**  | Java (Core + Swing for GUI)                      |
| **Database**  | MySQL (`realsoft` schema)                        |
| **Libraries** | JavaMail API, MySQL Connector, JDatePicker      |
| **Tools**     | VS Code, Notepad                                 |
| **Threads**   | Background threads for asynchronous operations  |
| **License**   | MIT License                                      |

---

## 📂 **Project Structure**

```
Tuition_Handler/
│
├── application/       # Main entry point 
├── batch/             # Batch operations
├── city/              # City management
├── college/           # College records
├── course/            # Course-related logic
├── display/           # GUI display components
├── email/             # Email sending (JavaMail API)
├── lib/               # External libraries (JARs)
├── output/            # Output/log files
├── receipt/           # Receipt generation logic
├── student/           # Student CRUD operations
├── threads/           # Background threads
├── trade/             # Trade-related records
├── utility/           # Database connection & helper functions 
└── README.md          # Project documentation
```

---

## 📊 **Database Setup**

**1. Database Name** – `realsoft`  

**2. Tables** – Run the following queries in MySQL:

<details>
<summary>📌 Click to view full SQL script</summary>

```sql
USE realsoft;

CREATE TABLE Trade(
    trid INT PRIMARY KEY,
    trname VARCHAR(20),
    trstate INT
);

CREATE TABLE College(
    clid INT PRIMARY KEY,
    clname VARCHAR(20),
    clstate INT
);

CREATE TABLE City(
    ctid INT PRIMARY KEY,
    ctname VARCHAR(20),
    ctstate INT
);

CREATE TABLE Course(
    coid INT PRIMARY KEY,
    coname VARCHAR(20),
    codfee INT,
    corfee INT,
    costate INT
);

CREATE TABLE Batch(
    btid INT PRIMARY KEY,
    btname VARCHAR(20),
    btstdate DATE,
    bteddate DATE,
    bttime VARCHAR(20),
    btstate INT
);

CREATE TABLE Receipt(
    reno INT PRIMARY KEY,
    redate DATE,
    rerollno INT,
    reamt INT,
    retype VARCHAR(2),
    redetail VARCHAR(10),
    react VARCHAR(4),
    restate INT
);

CREATE TABLE Student(
    stno INT PRIMARY KEY,
    stdate DATE,
    stname1 VARCHAR(30),
    stname2 VARCHAR(30),
    stname3 VARCHAR(30),
    stgender VARCHAR(10),
    stdob DATE,
    stclnm VARCHAR(20),
    sttrnm VARCHAR(10),
    stacyr INT,
    stpaddr VARCHAR(30),
    stladdr VARCHAR(30),
    stctnm VARCHAR(15),
    stsmob VARCHAR(10),
    stpmob VARCHAR(10),
    stemail VARCHAR(30),
    stconm VARCHAR(10),
    stbtnm VARCHAR(10),
    sbttime VARCHAR(30),
    stcofee INT,
    stadmfee INT,
    stbalfee INT,
    sttrntype VARCHAR(20),
    sttrndetail VARCHAR(10),
    sttrnto VARCHAR(20),
    stmode VARCHAR(10),
    ststate INT
);
```

</details>

---

## ⚡ **Setup & Installation**

### ✅ **1. Prerequisites**

* Install **Java JDK 8 or above**
* Install **MySQL Server**
* Ensure the following JARs are present in `/lib`:

  * `mysql-connector-j-8.1.0.jar`
  * `mail.jar`
  * `activation.jar`
  * `smtp-1.4.4.jar`
  * `jdatepicker-1.3.4.jar`

### ✅ **2. Clone the Repository**

```bash
git clone https://github.com/YourUsername/Tuition_Handler.git
cd Tuition_Handler
```

### ✅ **3. Update DB Credentials**

Update the file `utility/DB.java` with your local MySQL username and password.

### ✅ **4. Run the Application**

Use the following command (**Windows**):

```bash
java -classpath ".;D:\realsoft;D:\realsoft\utility\lib\jdatepicker-1.3.4.jar;D:\realsoft\lib\mysql-connector-j-8.1.0.jar;D:\realsoft\lib\mail.jar;D:\realsoft;D:\realsoft\lib\activation.jar;D:\realsoft\lib\smtp-1.4.4.jar" application.LandingPage
```

(Replace `D:\realsoft` with your actual project path)

---

## 🔮 **Future Enhancements**

* Attendance tracking  
* Fee analytics dashboard  
* SMS notifications  

---

## 👩‍💻 **Author**

**Prajakta Darekar**  
[![GitHub](https://img.shields.io/badge/GitHub-black?logo=github)](https://github.com/prajudarekar04)  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin)](https://linkedin.com/in/prajaktadarekar)  

---
