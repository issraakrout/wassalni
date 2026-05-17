# 📦 WASSALNI  
### Mobile Delivery Supervision Application

WASSALNI is a **full-stack Android application** designed for real-time delivery supervision within a delivery company.

It was developed as part of the **Mobile Development Project (2025–2026)** at **ENICarthage**.

The system digitalizes and centralizes delivery management through real-time monitoring, role-based access, messaging, statistics, and offline synchronization.

---

## 🚀 Overview

The application supports **two user roles**:

### 👨‍💼 Controller
Responsible for supervising deliveries and monitoring operations.

### 🚚 Delivery Driver
Responsible for managing assigned deliveries and updating delivery states in real time.

The main objective is to provide:

- Real-time delivery tracking
- Instant communication
- Offline access
- Automatic synchronization
- Delivery performance supervision

---

# ✨ Features

## Controller Module

- View all deliveries
- Filter deliveries by:
  - Status
  - Driver
  - Client
  - Delivery date
- Search deliveries
- View delivery details
- Monitor performance statistics
- Send real-time messages to delivery drivers

---

## Delivery Driver Module

- View assigned daily deliveries
- Access customer information
- Open customer address directly in Google Maps
- Update delivery status:

  - Delivered
  - In Progress
  - Not Delivered
  - Postponed

- Send urgent messages to controller

---

## Offline Mode

WASSALNI supports **offline-first operation** using Room (SQLite).

When network access is unavailable:

✅ Delivery data is loaded from local database  
✅ Updates are stored locally  
✅ Synchronization happens automatically when connection is restored

---

# 🛠 Tech Stack

## Mobile Application
- Java 17
- Android Studio
- RecyclerView
- CardView
- Room (SQLite)

---

## Backend
- Spring Boot 3.2.4
- REST API
- Maven

---

## Database
- Oracle Database XE
- SQL Developer

---

## Networking
- Retrofit2
- Gson

---

## External Services
- Google Maps Integration

---

# 🏗 Architecture

The project follows a **3-tier architecture**.

## 1. Presentation Layer
Android mobile user interface

---

## 2. Business Logic Layer
Spring Boot REST API

Responsible for:

- Authentication
- Delivery management
- Messaging
- Statistics

---

## 3. Data Layer

### Central Database
Oracle Database

### Local Database
Room (SQLite)

Used for offline access and synchronization

---

# 🗄 Database Design

## Oracle Central Database

Main entities:

- Personnel
- Clients
- Orders
- Deliveries
- Messages
- Delivery details

---

## Local SQLite Database

Stores delivery data on delivery driver's device.

Purpose:

- Offline access
- Local updates
- Deferred synchronization

---

# 🔌 API Endpoints

## Authentication

```http
POST /api/login
GET /api/livraisons
GET /api/livraisons/today
GET /api/livraisons/{id}/detail
PUT /api/livraisons/{id}/etat
POST /api/messages/envoyer
GET /api/messages/conversation
GET /api/messages/nonlus
