package com.example.busbooking.controller;

import com.example.busbooking.dto.response.ApiResponse;
import com.example.busbooking.dto.response.BookingResponse;
import com.example.busbooking.model.Booking;
import com.example.busbooking.model.User;
import com.example.busbooking.repository.BookingRepository;
import com.example.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponse> responses = new ArrayList<>();
        
        for (Booking booking : bookings) {
            BookingResponse response = new BookingResponse();
            response.setId(booking.getId());
            response.setBusNumber(booking.getBus().getBusNumber());
            response.setSource(booking.getBus().getRoute().getSource());
            response.setDestination(booking.getBus().getRoute().getDestination());
            response.setSeatNumber(booking.getSeat() != null ? booking.getSeat().getSeatNumber() : null);
            response.setTravelDate(booking.getTravelDate());
            response.setFare(booking.getFare());
            response.setStatus(booking.getStatus());
            response.setBookingTime(booking.getBookingTime());
            
            if (booking.getTicket() != null) {
                response.setTicketNumber(booking.getTicket().getTicketNumber());
            }
            
            responses.add(response);
        }
        
        return ResponseEntity.ok(ApiResponse.success("All bookings retrieved", responses));
    }
    
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<Map<String, Object>> userList = new ArrayList<>();
        
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("name", user.getName());
            userMap.put("email", user.getEmail());
            userMap.put("phone", user.getPhone());
            userMap.put("role", user.getRole());
            userMap.put("active", user.isActive());
            userMap.put("createdAt", user.getCreatedAt());
            userList.add(userMap);
        }
        
        return ResponseEntity.ok(ApiResponse.success("All users retrieved", userList));
    }
    
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        long totalUsers = userRepository.count();
        long totalBookings = bookingRepository.count();
        
        // Calculate total revenue
        List<Booking> allBookings = bookingRepository.findAll();
        double totalRevenue = allBookings.stream()
                .filter(b -> b.getFare() != null)
                .mapToDouble(Booking::getFare)
                .sum();
        
        stats.put("totalUsers", totalUsers);
        stats.put("totalBookings", totalBookings);
        stats.put("totalRevenue", totalRevenue);
        
        return ResponseEntity.ok(ApiResponse.success("Stats retrieved", stats));
    }
}
