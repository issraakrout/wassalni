# WASSALNI – Mobile Delivery Supervision Application

WASSALNI is a full-stack Android application designed for **real-time delivery supervision** within a delivery company.

The system supports two user roles:

- **Controllers** → supervise deliveries, monitor statistics, communicate with delivery agents
- **Delivery Drivers** → manage assigned deliveries, update delivery status, communicate urgent issues

The project was developed as part of my **Mobile Development Project (2025–2026)** at **ENICarthage**.

---

## Features

### Controller Module
- View all deliveries
- Filter deliveries by status, driver, client, or date
- Search deliveries
- View delivery details
- Monitor delivery statistics
- Send real-time messages to drivers

### Delivery Driver Module
- View daily assigned deliveries
- Access customer details
- Open destination directly in Google Maps
- Update delivery status:
  - Delivered
  - In Progress
  - Not Delivered
  - Postponed
- Send urgent messages to controller

### Offline Mode
The application supports **offline-first delivery management** using local SQLite storage.

If the API becomes unavailable:
- Data is loaded from local database
- Modifications are stored locally
- Synchronization happens automatically when connection is restored

---

## Tech Stack

### Mobile Application
- Java 17
- Android Studio
- RecyclerView
- CardView
- Room (SQLite)

### Backend
- Spring Boot 3.2.4
- REST API
- Java

### Database
- Oracle Database XE
- SQL Developer

### Networking
- Retrofit2
- Gson

### External Integration
- Google Maps

---

## Architecture

The project follows a **3-tier architecture**:

### Presentation Layer
Android mobile interface

### Business Logic Layer
Spring Boot REST API

### Data Layer
Oracle Database + Local SQLite cache

---

## Database Design

### Central Database (Oracle)
Main relational database storing:

- Personnel
- Clients
- Deliveries
- Orders
- Messages

### Local Database (Room / SQLite)
Used on delivery driver's device for:

- Offline access
- Local state updates
- Deferred synchronization

---

## API Endpoints

### Authentication
```http
POST /api/login