package com.example.busbooking.dto.request;

import com.example.busbooking.enums.PassType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PassPurchaseRequest {
    
    @NotNull(message = "Route ID is required")
    private Long routeId;
    
    @NotNull(message = "Pass type is required")
    private PassType passType;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    private String paymentMethod;
    
    // Getters and Setters
    public Long getRouteId() {
        return routeId;
    }
    
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
    
    public PassType getPassType() {
        return passType;
    }
    
    public void setPassType(PassType passType) {
        this.passType = passType;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
