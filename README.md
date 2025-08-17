# 🚌 Online Bus Booking Application

An Online Bus Booking web application built with **Spring Boot, Thymeleaf, and MySQL**.  
This project allows users to book buses online, while admins can manage buses, routes, and schedules through an admin panel.  

---

## 🚀 Features
### 👨‍💻 User
- Register & Login  
- Search available buses by route  
- View bus details (timing, seats, route)  
- Book seats  

### 🛠 Admin
- Secure admin panel with login  
- Add, update, and delete buses  
- Manage routes (source & destination)  
- View all buses and schedules  

---

## 🏗 Tech Stack
- **Backend:** Spring Boot, Spring Data JPA, Hibernate  
- **Frontend:** Thymeleaf, Bootstrap 5  
- **Database:** MySQL  
- **Build Tool:** Maven  

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/SiddhesAlhat/online-Bus-Booking-Application.git
cd online-Bus-Booking-Application

CREATE DATABASE bus_booking;

src/main/java/com/example/demo/
│── Controller/        # REST + MVC Controllers
│── entity/            # JPA Entities (User, Bus, Route)
│── Repository/        # Spring Data JPA Repositories
│── service/           # Business logic (if added)
│── resources/
    ├── templates/     # Thymeleaf HTML files
    ├── static/        # CSS, JS, Images
    ├── application.properties
```
<img width="1830" height="892" alt="Screenshot 2025-08-18 021009" src="https://github.com/user-attachments/assets/5fc80870-bdce-47e1-83c6-d7f4503ca65f" />
<img width="1836" height="496" alt="Screenshot 2025-08-18 020958" src="https://github.com/user-attachments/assets/67efdac8-b08d-4629-94a1-3027d8d20a75" />
<img width="1768" height="522" alt="Screenshot 2025-08-18 020952" src="https://github.com/user-attachments/assets/3beab5a0-d0d4-4e3c-91e1-3893cdb9d4d2" />
<img width="1751" height="614" alt="Screenshot 2025-08-18 020944" src="https://github.com/user-attachments/assets/5e63aa50-c6a4-4a2d-b969-281aeadeff1b" />
<img width="1730" height="562" alt="Screenshot 2025-08-18 020932" src="https://github.com/user-attachments/assets/64c75260-4068-4581-8c24-a009ed2e06c9" />
<img width="1814" height="909" alt="Screenshot 2025-08-18 020817" src="https://github.com/user-attachments/assets/4023b937-d79d-4513-ac97-7c85564b6cf8" />

