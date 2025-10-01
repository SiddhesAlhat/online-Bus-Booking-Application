package com.example.busbooking.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookingRequest {
    
    @NotNull(message = "Bus ID is required")
    private Long busId;
    
    @NotNull(message = "Seat ID is required")
    private Long seatId;
    
    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;
    
    private String paymentMethod;
    
    // Getters and Setters
    public Long getBusId() {
        return busId;
    }
    
    public void setBusId(Long busId) {
        this.busId = busId;
    }
    
    public Long getSeatId() {
        return seatId;
    }
    
    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
    
    public LocalDate getTravelDate() {
        return travelDate;
    }
    
    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
