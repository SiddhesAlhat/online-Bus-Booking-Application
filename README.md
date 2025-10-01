# Bus Ticket & Pass Booking Application - MVP

## Overview
A comprehensive bus booking system that allows users to book bus tickets and passes without standing in queues or dealing with cash transactions.

## Core Features Implemented

### 1. User Authentication ✅
- Phone number-based registration and login
- JWT token-based authentication
- User profile management (name, email, phone)

### 2. Route Search ✅
- Search buses by source and destination
- View available buses with departure times
- Display bus type, duration, and fare information

### 3. Single Ticket Booking ✅
- Select date and time for travel
- Choose seats from available options
- View total fare
- Confirm booking with payment
- Digital ticket generation with QR code

### 4. Bus Pass Purchase ✅
- Select pass type (DAILY/WEEKLY/MONTHLY)
- Choose route for the pass
- Make payment
- Receive digital pass with unique code

### 5. Payment Integration ✅
- Simulated payment gateway (ready for real integration)
- Payment confirmation
- Transaction ID generation

### 6. Booking Management ✅
- View active bookings
- Digital ticket with QR code
- Booking cancellation support
- Booking history

### 7. Digital Ticket Display ✅
- QR code for verification
- Complete booking details (route, time, seat)
- Pass validity dates

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.5.4
- **Language**: Java 17
- **Database**: MySQL 8.0
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA / Hibernate
- **QR Code**: ZXing (Google)

### Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- MySQL Connector
- JWT (jjwt 0.11.5)
- ZXing Core & JavaSE (3.5.1)
- Lombok (optional)

## Database Schema

### Tables Created
1. **users** - User information with phone-based authentication
2. **routes** - Bus routes with source and destination
3. **buses** - Bus details with route, type, fare, and seat availability
4. **seats** - Seat management for each bus
5. **bookings** - Booking records with status tracking
6. **tickets** - Digital tickets with QR codes
7. **passes** - Bus pass records
8. **payments** - Payment transaction records

## API Endpoints

### Authentication APIs
```
POST /api/auth/register - Register new user
POST /api/auth/login - User login
```

### Route & Bus Search APIs
```
POST /api/routes/search - Search buses by source/destination
GET /api/routes - Get all available routes
```

### Booking APIs
```
POST /api/bookings - Create new booking
GET /api/bookings - Get user's bookings
GET /api/bookings/{id}/ticket - Get digital ticket
DELETE /api/bookings/{id} - Cancel booking
```

### Pass APIs
```
POST /api/passes - Purchase bus pass
GET /api/passes - Get user's passes
```

## Setup Instructions

### Prerequisites
1. Java 17 or higher
2. MySQL 8.0 or higher
3. Maven 3.6+
4. IDE (Eclipse/IntelliJ/VS Code)

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE busbooking;
```

2. Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/busbooking
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Running the Application

1. **Clone/Navigate to project directory**

2. **Install dependencies**:
```bash
mvn clean install
```

3. **Run the application**:
```bash
mvn spring-boot:run
```

4. **Application will start on**: `http://localhost:8080`

### Testing the APIs

#### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "password": "password123"
  }'
```

#### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "phone": "9876543210",
    "password": "password123"
  }'
```

#### 3. Search Buses
```bash
curl -X POST http://localhost:8080/api/routes/search \
  -H "Content-Type: application/json" \
  -d '{
    "source": "Mumbai",
    "destination": "Pune",
    "travelDate": "2025-10-15"
  }'
```

#### 4. Create Booking
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -H "X-User-Phone: 9876543210" \
  -d '{
    "busId": 1,
    "seatId": 1,
    "travelDate": "2025-10-15",
    "paymentMethod": "UPI"
  }'
```

#### 5. Get User Bookings
```bash
curl -X GET http://localhost:8080/api/bookings \
  -H "X-User-Phone: 9876543210"
```

#### 6. Purchase Pass
```bash
curl -X POST http://localhost:8080/api/passes \
  -H "Content-Type: application/json" \
  -H "X-User-Phone: 9876543210" \
  -d '{
    "routeId": 1,
    "passType": "MONTHLY",
    "startDate": "2025-10-01",
    "paymentMethod": "CARD"
  }'
```

## Project Structure

```
BusBookingSystem/
├── src/main/java/com/example/busbooking/
│   ├── config/              # Security and app configuration
│   ├── controller/          # REST API controllers
│   ├── dto/                 # Data Transfer Objects
│   │   ├── request/         # API request DTOs
│   │   └── response/        # API response DTOs
│   ├── enums/               # Enumerations (Status types)
│   ├── exception/           # Custom exceptions and handlers
│   ├── model/               # JPA Entity models
│   ├── repository/          # JPA Repositories
│   ├── service/             # Business logic layer
│   └── util/                # Utility classes (JWT, QR Code)
├── src/main/resources/
│   ├── application.properties
│   └── templates/           # Thymeleaf templates (optional)
└── pom.xml                  # Maven dependencies
```

## Key Features Implementation Details

### QR Code Generation
- Uses ZXing library
- Generates Base64 encoded QR codes
- Embedded in ticket response
- Contains ticket number, bus details, seat, and date

### JWT Authentication
- Token-based authentication
- 24-hour token expiration
- Phone number as principal
- Role-based access (USER/ADMIN)

### Payment Integration
- Currently simulated for MVP
- Ready structure for real payment gateway integration
- Supports multiple payment methods (UPI, CARD, NET_BANKING)
- Transaction tracking with unique IDs

### Seat Management
- Real-time seat availability tracking
- Seat status: AVAILABLE, BOOKED, BLOCKED
- Automatic seat release on cancellation

## Future Enhancements (Post-MVP)

- [ ] Real payment gateway integration (Razorpay/Stripe)
- [ ] Live bus tracking
- [ ] Multi-city routes
- [ ] Seat preferences/selection UI
- [ ] Loyalty programs
- [ ] Reviews and ratings
- [ ] In-app customer support
- [ ] Push notifications
- [ ] Advanced refund policies
- [ ] Admin dashboard
- [ ] Analytics and reporting

## Success Metrics

Track these metrics for MVP success:
- Number of successful bookings per day
- Ticket booking completion rate
- Pass purchase rate
- App crash rate (target: < 2%)
- API response time
- User registration rate

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MySQL is running
   - Check credentials in application.properties
   - Ensure database 'busbooking' exists

2. **Port Already in Use**
   - Change port in application.properties: `server.port=8081`

3. **Maven Build Fails**
   - Run: `mvn clean install -U`
   - Check Java version: `java -version`

4. **QR Code Generation Fails**
   - Check ZXing dependencies are properly loaded
   - Booking will still succeed without QR code

## API Response Format

All APIs return responses in this format:

**Success Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { /* response data */ }
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "Error description",
  "data": null
}
```

## Security Notes

⚠️ **Important for Production:**
1. Change JWT secret key in `JwtUtil.java`
2. Use environment variables for sensitive data
3. Enable HTTPS
4. Implement rate limiting
5. Add input sanitization
6. Enable CORS only for trusted domains
7. Use secure password policies
8. Implement proper session management

## Contributing

This is an MVP implementation. For production deployment:
1. Add comprehensive unit and integration tests
2. Implement proper logging and monitoring
3. Add API documentation (Swagger/OpenAPI)
4. Implement caching (Redis)
5. Add load balancing support
6. Implement database migrations (Flyway/Liquibase)

## License

This project is for educational/demonstration purposes.

## Contact & Support

For issues or questions:
- Create an issue in the repository
- Check existing documentation
- Review API endpoint examples

---

**MVP Version**: 1.0.0  
**Last Updated**: October 2025  
**Status**: ✅ Ready for Testing
