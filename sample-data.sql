-- Bus Booking System - Sample Data Script
-- Run this after the application creates the tables

USE busbooking;

-- Clear existing data (optional - use with caution)
-- SET FOREIGN_KEY_CHECKS = 0;
-- TRUNCATE TABLE tickets;
-- TRUNCATE TABLE payments;
-- TRUNCATE TABLE bookings;
-- TRUNCATE TABLE passes;
-- TRUNCATE TABLE seats;
-- TRUNCATE TABLE buses;
-- TRUNCATE TABLE routes;
-- TRUNCATE TABLE users;
-- SET FOREIGN_KEY_CHECKS = 1;

-- Insert Sample Routes
INSERT INTO routes (source, destination, distance, duration, active) VALUES
('Mumbai', 'Pune', 150.0, 180, true),
('Delhi', 'Jaipur', 280.0, 300, true),
('Bangalore', 'Chennai', 350.0, 360, true),
('Hyderabad', 'Vijayawada', 275.0, 300, true),
('Kolkata', 'Bhubaneswar', 450.0, 480, true),
('Ahmedabad', 'Surat', 265.0, 240, true),
('Chennai', 'Coimbatore', 500.0, 480, true),
('Mumbai', 'Goa', 450.0, 540, true);

-- Insert Sample Buses
INSERT INTO buses (bus_number, route_id, bus_type, departure_time, arrival_time, fare, total_seats, available_seats, active) VALUES
-- Mumbai to Pune
('MH-01-1234', 1, 'AC', '08:00:00', '11:00:00', 500.0, 40, 40, true),
('MH-01-5678', 1, 'NON_AC', '10:00:00', '13:00:00', 300.0, 40, 40, true),
('MH-01-9012', 1, 'SEMI_SLEEPER', '14:00:00', '17:00:00', 450.0, 40, 40, true),
('MH-01-3456', 1, 'AC', '18:00:00', '21:00:00', 550.0, 40, 40, true),

-- Delhi to Jaipur
('DL-02-9876', 2, 'SLEEPER', '22:00:00', '04:00:00', 800.0, 40, 40, true),
('DL-02-5432', 2, 'AC', '09:00:00', '14:00:00', 700.0, 40, 40, true),
('DL-02-1098', 2, 'NON_AC', '15:00:00', '20:00:00', 500.0, 40, 40, true),

-- Bangalore to Chennai
('KA-03-7654', 3, 'AC', '23:00:00', '05:00:00', 900.0, 40, 40, true),
('KA-03-3210', 3, 'SLEEPER', '20:00:00', '02:00:00', 850.0, 40, 40, true),

-- Hyderabad to Vijayawada
('TS-04-8765', 4, 'AC', '07:00:00', '12:00:00', 600.0, 40, 40, true),
('TS-04-4321', 4, 'NON_AC', '13:00:00', '18:00:00', 400.0, 40, 40, true),

-- Mumbai to Goa
('MH-08-1111', 8, 'SLEEPER', '21:00:00', '06:00:00', 1200.0, 40, 40, true),
('MH-08-2222', 8, 'AC', '08:00:00', '17:00:00', 1500.0, 40, 40, true);

-- Insert Sample Seats for Bus 1 (MH-01-1234) for next 7 days
-- This creates seats for the next week
INSERT INTO seats (bus_id, seat_number, status, travel_date)
SELECT 
    1 as bus_id,
    seat_num as seat_number,
    'AVAILABLE' as status,
    DATE_ADD(CURDATE(), INTERVAL day_offset DAY) as travel_date
FROM (
    SELECT 'A1' as seat_num UNION ALL SELECT 'A2' UNION ALL SELECT 'A3' UNION ALL SELECT 'A4' UNION ALL
    SELECT 'B1' UNION ALL SELECT 'B2' UNION ALL SELECT 'B3' UNION ALL SELECT 'B4' UNION ALL
    SELECT 'C1' UNION ALL SELECT 'C2' UNION ALL SELECT 'C3' UNION ALL SELECT 'C4' UNION ALL
    SELECT 'D1' UNION ALL SELECT 'D2' UNION ALL SELECT 'D3' UNION ALL SELECT 'D4' UNION ALL
    SELECT 'E1' UNION ALL SELECT 'E2' UNION ALL SELECT 'E3' UNION ALL SELECT 'E4' UNION ALL
    SELECT 'F1' UNION ALL SELECT 'F2' UNION ALL SELECT 'F3' UNION ALL SELECT 'F4' UNION ALL
    SELECT 'G1' UNION ALL SELECT 'G2' UNION ALL SELECT 'G3' UNION ALL SELECT 'G4' UNION ALL
    SELECT 'H1' UNION ALL SELECT 'H2' UNION ALL SELECT 'H3' UNION ALL SELECT 'H4' UNION ALL
    SELECT 'I1' UNION ALL SELECT 'I2' UNION ALL SELECT 'I3' UNION ALL SELECT 'I4' UNION ALL
    SELECT 'J1' UNION ALL SELECT 'J2' UNION ALL SELECT 'J3' UNION ALL SELECT 'J4'
) seats
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL 
    SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
) days;

-- Insert Sample Seats for Bus 2 (MH-01-5678) for next 7 days
INSERT INTO seats (bus_id, seat_number, status, travel_date)
SELECT 
    2 as bus_id,
    seat_num as seat_number,
    'AVAILABLE' as status,
    DATE_ADD(CURDATE(), INTERVAL day_offset DAY) as travel_date
FROM (
    SELECT 'A1' as seat_num UNION ALL SELECT 'A2' UNION ALL SELECT 'A3' UNION ALL SELECT 'A4' UNION ALL
    SELECT 'B1' UNION ALL SELECT 'B2' UNION ALL SELECT 'B3' UNION ALL SELECT 'B4' UNION ALL
    SELECT 'C1' UNION ALL SELECT 'C2' UNION ALL SELECT 'C3' UNION ALL SELECT 'C4' UNION ALL
    SELECT 'D1' UNION ALL SELECT 'D2' UNION ALL SELECT 'D3' UNION ALL SELECT 'D4' UNION ALL
    SELECT 'E1' UNION ALL SELECT 'E2' UNION ALL SELECT 'E3' UNION ALL SELECT 'E4' UNION ALL
    SELECT 'F1' UNION ALL SELECT 'F2' UNION ALL SELECT 'F3' UNION ALL SELECT 'F4' UNION ALL
    SELECT 'G1' UNION ALL SELECT 'G2' UNION ALL SELECT 'G3' UNION ALL SELECT 'G4' UNION ALL
    SELECT 'H1' UNION ALL SELECT 'H2' UNION ALL SELECT 'H3' UNION ALL SELECT 'H4' UNION ALL
    SELECT 'I1' UNION ALL SELECT 'I2' UNION ALL SELECT 'I3' UNION ALL SELECT 'I4' UNION ALL
    SELECT 'J1' UNION ALL SELECT 'J2' UNION ALL SELECT 'J3' UNION ALL SELECT 'J4'
) seats
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL 
    SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
) days;

-- Insert Sample Seats for Bus 5 (DL-02-9876) for next 7 days
INSERT INTO seats (bus_id, seat_number, status, travel_date)
SELECT 
    5 as bus_id,
    seat_num as seat_number,
    'AVAILABLE' as status,
    DATE_ADD(CURDATE(), INTERVAL day_offset DAY) as travel_date
FROM (
    SELECT 'A1' as seat_num UNION ALL SELECT 'A2' UNION ALL SELECT 'A3' UNION ALL SELECT 'A4' UNION ALL
    SELECT 'B1' UNION ALL SELECT 'B2' UNION ALL SELECT 'B3' UNION ALL SELECT 'B4' UNION ALL
    SELECT 'C1' UNION ALL SELECT 'C2' UNION ALL SELECT 'C3' UNION ALL SELECT 'C4' UNION ALL
    SELECT 'D1' UNION ALL SELECT 'D2' UNION ALL SELECT 'D3' UNION ALL SELECT 'D4' UNION ALL
    SELECT 'E1' UNION ALL SELECT 'E2' UNION ALL SELECT 'E3' UNION ALL SELECT 'E4' UNION ALL
    SELECT 'F1' UNION ALL SELECT 'F2' UNION ALL SELECT 'F3' UNION ALL SELECT 'F4' UNION ALL
    SELECT 'G1' UNION ALL SELECT 'G2' UNION ALL SELECT 'G3' UNION ALL SELECT 'G4' UNION ALL
    SELECT 'H1' UNION ALL SELECT 'H2' UNION ALL SELECT 'H3' UNION ALL SELECT 'H4' UNION ALL
    SELECT 'I1' UNION ALL SELECT 'I2' UNION ALL SELECT 'I3' UNION ALL SELECT 'I4' UNION ALL
    SELECT 'J1' UNION ALL SELECT 'J2' UNION ALL SELECT 'J3' UNION ALL SELECT 'J4'
) seats
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL 
    SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
) days;

-- Verify the data
SELECT 'Routes Created:' as Info, COUNT(*) as Count FROM routes;
SELECT 'Buses Created:' as Info, COUNT(*) as Count FROM buses;
SELECT 'Seats Created:' as Info, COUNT(*) as Count FROM seats;

-- Display sample routes with buses
SELECT 
    r.source,
    r.destination,
    COUNT(DISTINCT b.id) as total_buses,
    MIN(b.fare) as min_fare,
    MAX(b.fare) as max_fare
FROM routes r
LEFT JOIN buses b ON r.id = b.route_id
GROUP BY r.id, r.source, r.destination;

-- Display buses with route info
SELECT 
    b.bus_number,
    r.source,
    r.destination,
    b.bus_type,
    b.departure_time,
    b.fare,
    b.available_seats
FROM buses b
JOIN routes r ON b.route_id = r.id
ORDER BY r.source, b.departure_time;

COMMIT;

-- Sample queries to verify seat availability
SELECT 
    b.bus_number,
    s.travel_date,
    COUNT(*) as total_seats,
    SUM(CASE WHEN s.status = 'AVAILABLE' THEN 1 ELSE 0 END) as available_seats,
    SUM(CASE WHEN s.status = 'BOOKED' THEN 1 ELSE 0 END) as booked_seats
FROM seats s
JOIN buses b ON s.bus_id = b.id
GROUP BY b.bus_number, s.travel_date
ORDER BY s.travel_date, b.bus_number
LIMIT 20;

SELECT '✅ Sample data inserted successfully!' as Status;
