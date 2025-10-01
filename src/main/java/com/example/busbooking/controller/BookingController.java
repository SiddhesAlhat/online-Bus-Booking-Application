package com.example.busbooking.controller;

import com.example.busbooking.dto.request.BookingRequest;
import com.example.busbooking.dto.response.ApiResponse;
import com.example.busbooking.dto.response.BookingResponse;
import com.example.busbooking.dto.response.TicketResponse;
import com.example.busbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(
            @Valid @RequestBody BookingRequest request,
            @RequestHeader("X-User-Phone") String userPhone) {
        BookingResponse response = bookingService.createBooking(request, userPhone);
        return ResponseEntity.ok(ApiResponse.success("Booking created successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getUserBookings(
            @RequestHeader("X-User-Phone") String userPhone) {
        List<BookingResponse> bookings = bookingService.getUserBookings(userPhone);
        return ResponseEntity.ok(ApiResponse.success("Bookings retrieved", bookings));
    }
    
    @GetMapping("/{bookingId}/ticket")
    public ResponseEntity<ApiResponse<TicketResponse>> getTicket(
            @PathVariable Long bookingId,
            @RequestHeader("X-User-Phone") String userPhone) {
        TicketResponse ticket = bookingService.getTicket(bookingId, userPhone);
        return ResponseEntity.ok(ApiResponse.success("Ticket retrieved", ticket));
    }
    
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ApiResponse<Void>> cancelBooking(
            @PathVariable Long bookingId,
            @RequestHeader("X-User-Phone") String userPhone) {
        bookingService.cancelBooking(bookingId, userPhone);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully", null));
    }
}
