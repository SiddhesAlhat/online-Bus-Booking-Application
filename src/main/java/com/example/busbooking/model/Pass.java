package com.example.busbooking.model;

import com.example.busbooking.enums.PassType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "passes")
public class Pass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
    
    @Enumerated(EnumType.STRING)
    private PassType passType;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Double price;
    
    private boolean active = true;
    
    private LocalDateTime purchaseTime = LocalDateTime.now();
    
    @Column(unique = true)
    private String passCode;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Route getRoute() {
        return route;
    }
    
    public void setRoute(Route route) {
        this.route = route;
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
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }
    
    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
    
    public String getPassCode() {
        return passCode;
    }
    
    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }
}
