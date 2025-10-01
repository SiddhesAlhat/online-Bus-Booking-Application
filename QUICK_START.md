# 🚀 Quick Start Guide - Bus Booking System MVP

## Prerequisites Checklist
- ✅ Java 17+ installed
- ✅ MySQL 8.0+ installed and running
- ✅ Maven 3.6+ installed
- ✅ IDE (Eclipse/IntelliJ/VS Code)
- ✅ Postman or cURL for API testing

## 5-Minute Setup

### Step 1: Database Setup (2 minutes)
```sql
-- Open MySQL command line or MySQL Workbench
CREATE DATABASE busbooking;
```

### Step 2: Configure Application (1 minute)
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Build & Run (2 minutes)
```bash
# Navigate to project directory
cd BusBookingSystem

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

**Application will start on:** `http://localhost:8080`

### Step 4: Load Sample Data
```bash
# In MySQL, run the sample data script
mysql -u root -p busbooking < sample-data.sql
```

### Step 5: Test Your First API
```bash
# Register a user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "phone": "9876543210",
    "password": "Test@123"
  }'
```

## 🎯 What You Get

### ✅ Implemented Features
1. **User Authentication** - Phone-based login/register
2. **Route Search** - Find buses by source/destination
3. **Ticket Booking** - Book seats with payment
4. **Digital Tickets** - QR code generation
5. **Pass Management** - Daily/Weekly/Monthly passes
6. **Booking History** - View all bookings
7. **Cancellation** - Cancel bookings

### 📁 Project Structure
```
BusBookingSystem/
├── src/main/java/com/example/busbooking/
│   ├── config/          # Security configuration
│   ├── controller/      # REST API endpoints
│   ├── dto/            # Request/Response objects
│   ├── enums/          # Status enumerations
│   ├── exception/      # Error handling
│   ├── model/          # Database entities
│   ├── repository/     # Data access layer
│   ├── service/        # Business logic
│   └── util/           # JWT & QR Code utilities
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── README.md
├── API_TESTING_GUIDE.md
├── sample-data.sql
└── QUICK_START.md (this file)
```

## 🔥 Quick Test Workflow

### 1. Register & Login
```bash
# Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@test.com","phone":"9876543210","password":"Test@123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"phone":"9876543210","password":"Test@123"}'
```

### 2. Search Buses
```bash
curl -X POST http://localhost:8080/api/routes/search \
  -H "Content-Type: application/json" \
  -d '{"source":"Mumbai","destination":"Pune","travelDate":"2025-10-15"}'
```

### 3. Book a Ticket
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -H "X-User-Phone: 9876543210" \
  -d '{"busId":1,"seatId":1,"travelDate":"2025-10-15","paymentMethod":"UPI"}'
```

### 4. View Bookings
```bash
curl -X GET http://localhost:8080/api/bookings \
  -H "X-User-Phone: 9876543210"
```

### 5. Get Digital Ticket
```bash
curl -X GET http://localhost:8080/api/bookings/1/ticket \
  -H "X-User-Phone: 9876543210"
```

## 📊 Database Tables Created

The application auto-creates these tables:
- `users` - User accounts
- `routes` - Bus routes
- `buses` - Bus details
- `seats` - Seat inventory
- `bookings` - Booking records
- `tickets` - Digital tickets with QR
- `passes` - Bus passes
- `payments` - Payment transactions

## 🛠️ Troubleshooting

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Database connection failed
**Solution:** 
1. Check MySQL is running: `mysql -u root -p`
2. Verify credentials in `application.properties`
3. Ensure database exists: `CREATE DATABASE busbooking;`

### Issue: Maven build fails
**Solution:**
```bash
mvn clean install -U
```

### Issue: Application starts but APIs fail
**Solution:** Check if sample data is loaded:
```sql
USE busbooking;
SELECT COUNT(*) FROM routes;
SELECT COUNT(*) FROM buses;
```

## 📚 Next Steps

1. **Read Full Documentation**: Check `README.md`
2. **API Testing**: See `API_TESTING_GUIDE.md`
3. **Add More Data**: Modify `sample-data.sql`
4. **Customize**: Update business logic in service classes

## 🎨 Sample Data Included

After running `sample-data.sql`, you'll have:
- **8 Routes** (Mumbai-Pune, Delhi-Jaipur, etc.)
- **13 Buses** with different types (AC, Non-AC, Sleeper)
- **840+ Seats** for next 7 days
- **Multiple fare options** (₹300 - ₹1500)

## 🔐 Security Notes

⚠️ **For Development Only**
- JWT secret is hardcoded (change for production)
- CORS is open to all origins
- Security is simplified for MVP

**For Production:**
- Use environment variables
- Enable HTTPS
- Implement proper authentication
- Add rate limiting
- Use secure password policies

## 💡 Pro Tips

1. **Use Postman**: Import the collection from `API_TESTING_GUIDE.md`
2. **Check Logs**: Watch console for SQL queries and errors
3. **Database GUI**: Use MySQL Workbench for easier data viewing
4. **Hot Reload**: Use Spring DevTools for faster development

## 📞 Support

- **Issues?** Check `README.md` troubleshooting section
- **API Questions?** See `API_TESTING_GUIDE.md`
- **Database Issues?** Verify `sample-data.sql` ran successfully

## ✅ Success Checklist

- [ ] MySQL database created
- [ ] Application starts without errors
- [ ] Sample data loaded successfully
- [ ] User registration works
- [ ] Login returns JWT token
- [ ] Bus search returns results
- [ ] Booking creation succeeds
- [ ] Ticket has QR code
- [ ] Booking history displays

## 🚀 You're Ready!

Your Bus Booking System MVP is now running!

**Base URL:** `http://localhost:8080`

**Test it:** Open Postman and start with `/api/auth/register`

---

**Happy Coding! 🎉**

Need help? Check the detailed guides:
- `README.md` - Complete documentation
- `API_TESTING_GUIDE.md` - API examples
- `sample-data.sql` - Database setup
