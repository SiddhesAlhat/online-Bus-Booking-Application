# API Testing Guide - Bus Booking System MVP

## Quick Start Testing Steps

### Step 1: Start the Application
```bash
mvn spring-boot:run
```

### Step 2: Create Sample Data (SQL)

Before testing APIs, insert some sample data into your MySQL database:

```sql
USE busbooking;

-- Insert sample routes
INSERT INTO routes (source, destination, distance, duration, active) VALUES
('Mumbai', 'Pune', 150.0, 180, true),
('Delhi', 'Jaipur', 280.0, 300, true),
('Bangalore', 'Chennai', 350.0, 360, true);

-- Insert sample buses
INSERT INTO buses (bus_number, route_id, bus_type, departure_time, arrival_time, fare, total_seats, available_seats, active) VALUES
('MH-01-1234', 1, 'AC', '08:00:00', '11:00:00', 500.0, 40, 40, true),
('MH-01-5678', 1, 'NON_AC', '10:00:00', '13:00:00', 300.0, 40, 40, true),
('DL-02-9876', 2, 'SLEEPER', '22:00:00', '04:00:00', 800.0, 40, 40, true);

-- Insert sample seats for first bus
INSERT INTO seats (bus_id, seat_number, status, travel_date) VALUES
(1, 'A1', 'AVAILABLE', '2025-10-15'),
(1, 'A2', 'AVAILABLE', '2025-10-15'),
(1, 'A3', 'AVAILABLE', '2025-10-15'),
(1, 'B1', 'AVAILABLE', '2025-10-15'),
(1, 'B2', 'AVAILABLE', '2025-10-15');
```

## API Testing with Postman/cURL

### 1. User Registration

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Siddesh Kumar",
    "email": "siddesh@example.com",
    "phone": "9876543210",
    "password": "Test@123"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Registration successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "name": "Siddesh Kumar",
    "email": "siddesh@example.com",
    "phone": "9876543210",
    "role": "USER"
  }
}
```

### 2. User Login

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "phone": "9876543210",
    "password": "Test@123"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "name": "Siddesh Kumar",
    "email": "siddesh@example.com",
    "phone": "9876543210",
    "role": "USER"
  }
}
```

### 3. Search Buses

**Request:**
```bash
curl -X POST http://localhost:8080/api/routes/search \
  -H "Content-Type: application/json" \
  -d '{
    "source": "Mumbai",
    "destination": "Pune",
    "travelDate": "2025-10-15"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Buses found",
  "data": [
    {
      "id": 1,
      "busNumber": "MH-01-1234",
      "source": "Mumbai",
      "destination": "Pune",
      "busType": "AC",
      "departureTime": "08:00:00",
      "arrivalTime": "11:00:00",
      "fare": 500.0,
      "availableSeats": 40,
      "duration": 180
    },
    {
      "id": 2,
      "busNumber": "MH-01-5678",
      "source": "Mumbai",
      "destination": "Pune",
      "busType": "NON_AC",
      "departureTime": "10:00:00",
      "arrivalTime": "13:00:00",
      "fare": 300.0,
      "availableSeats": 40,
      "duration": 180
    }
  ]
}
```

### 4. Create Booking

**Request:**
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

**Expected Response:**
```json
{
  "success": true,
  "message": "Booking created successfully",
  "data": {
    "id": 1,
    "busNumber": "MH-01-1234",
    "source": "Mumbai",
    "destination": "Pune",
    "seatNumber": "A1",
    "travelDate": "2025-10-15",
    "fare": 500.0,
    "status": "CONFIRMED",
    "bookingTime": "2025-10-01T22:30:00",
    "ticketNumber": "TKT1727804400000"
  }
}
```

### 5. Get User Bookings

**Request:**
```bash
curl -X GET http://localhost:8080/api/bookings \
  -H "X-User-Phone: 9876543210"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Bookings retrieved",
  "data": [
    {
      "id": 1,
      "busNumber": "MH-01-1234",
      "source": "Mumbai",
      "destination": "Pune",
      "seatNumber": "A1",
      "travelDate": "2025-10-15",
      "fare": 500.0,
      "status": "CONFIRMED",
      "bookingTime": "2025-10-01T22:30:00",
      "ticketNumber": "TKT1727804400000"
    }
  ]
}
```

### 6. Get Digital Ticket

**Request:**
```bash
curl -X GET http://localhost:8080/api/bookings/1/ticket \
  -H "X-User-Phone: 9876543210"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Ticket retrieved",
  "data": {
    "ticketNumber": "TKT1727804400000",
    "passengerName": "Siddesh Kumar",
    "busNumber": "MH-01-1234",
    "source": "Mumbai",
    "destination": "Pune",
    "seatNumber": "A1",
    "travelDate": "2025-10-15",
    "departureTime": "08:00:00",
    "fare": 500.0,
    "qrCode": "iVBORw0KGgoAAAANSUhEUgAA..." 
  }
}
```

### 7. Purchase Bus Pass

**Request:**
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

**Expected Response:**
```json
{
  "success": true,
  "message": "Pass purchased successfully",
  "data": {
    "id": 1,
    "passType": "MONTHLY",
    "startDate": "2025-10-01",
    "endDate": "2025-11-01",
    "price": 1000.0,
    "active": true,
    "passCode": "PASS1727804500000"
  }
}
```

### 8. Get User Passes

**Request:**
```bash
curl -X GET http://localhost:8080/api/passes \
  -H "X-User-Phone: 9876543210"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Passes retrieved",
  "data": [
    {
      "id": 1,
      "passType": "MONTHLY",
      "startDate": "2025-10-01",
      "endDate": "2025-11-01",
      "price": 1000.0,
      "active": true,
      "passCode": "PASS1727804500000"
    }
  ]
}
```

### 9. Cancel Booking

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/bookings/1 \
  -H "X-User-Phone: 9876543210"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Booking cancelled successfully",
  "data": null
}
```

### 10. Get All Routes

**Request:**
```bash
curl -X GET http://localhost:8080/api/routes
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Routes retrieved",
  "data": [
    {
      "id": 1,
      "source": "Mumbai",
      "destination": "Pune",
      "distance": 150.0,
      "duration": 180,
      "active": true
    },
    {
      "id": 2,
      "source": "Delhi",
      "destination": "Jaipur",
      "distance": 280.0,
      "duration": 300,
      "active": true
    }
  ]
}
```

## Postman Collection

Import this JSON into Postman for easy testing:

```json
{
  "info": {
    "name": "Bus Booking System MVP",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Register",
          "request": {
            "method": "POST",
            "header": [{"key": "Content-Type", "value": "application/json"}],
            "url": "http://localhost:8080/api/auth/register",
            "body": {
              "mode": "raw",
              "raw": "{\n  \"name\": \"Test User\",\n  \"email\": \"test@example.com\",\n  \"phone\": \"9876543210\",\n  \"password\": \"Test@123\"\n}"
            }
          }
        },
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "header": [{"key": "Content-Type", "value": "application/json"}],
            "url": "http://localhost:8080/api/auth/login",
            "body": {
              "mode": "raw",
              "raw": "{\n  \"phone\": \"9876543210\",\n  \"password\": \"Test@123\"\n}"
            }
          }
        }
      ]
    }
  ]
}
```

## Testing Checklist

- [ ] User can register successfully
- [ ] User can login with phone and password
- [ ] User can search buses by route
- [ ] User can view available seats
- [ ] User can create a booking
- [ ] User receives digital ticket with QR code
- [ ] User can view booking history
- [ ] User can cancel a booking
- [ ] User can purchase a pass
- [ ] User can view active passes
- [ ] Seat availability updates correctly
- [ ] Payment records are created
- [ ] Error handling works properly

## Common Test Scenarios

### Scenario 1: Complete Booking Flow
1. Register user
2. Login
3. Search buses
4. Create booking
5. Get ticket
6. Verify booking in history

### Scenario 2: Pass Purchase Flow
1. Login
2. Get routes
3. Purchase monthly pass
4. Verify pass in user passes

### Scenario 3: Cancellation Flow
1. Create booking
2. Get booking details
3. Cancel booking
4. Verify seat is available again

## Error Testing

### Test Invalid Phone Number
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test",
    "phone": "123",
    "password": "Test@123"
  }'
```

Expected: Validation error

### Test Duplicate Registration
Register same phone number twice - should get error on second attempt.

### Test Invalid Booking
Try to book with non-existent bus ID - should get "Bus not found" error.

## Performance Testing

Monitor these metrics:
- API response time (should be < 500ms)
- Database query performance
- Concurrent booking handling
- Memory usage

---

**Happy Testing! 🚀**
