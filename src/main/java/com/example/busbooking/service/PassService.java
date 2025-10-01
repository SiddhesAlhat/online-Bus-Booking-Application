package com.example.busbooking.service;

import com.example.busbooking.dto.request.PassPurchaseRequest;
import com.example.busbooking.enums.PassType;
import com.example.busbooking.enums.PaymentStatus;
import com.example.busbooking.exception.ResourceNotFoundException;
import com.example.busbooking.model.Pass;
import com.example.busbooking.model.Payment;
import com.example.busbooking.model.Route;
import com.example.busbooking.model.User;
import com.example.busbooking.repository.PassRepository;
import com.example.busbooking.repository.PaymentRepository;
import com.example.busbooking.repository.RouteRepository;
import com.example.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PassService {
    
    @Autowired
    private PassRepository passRepository;
    
    @Autowired
    private RouteRepository routeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Transactional
    public Pass purchasePass(PassPurchaseRequest request, String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        
        // Calculate price based on pass type
        Double price = calculatePassPrice(request.getPassType());
        
        // Calculate end date
        LocalDate endDate = calculateEndDate(request.getStartDate(), request.getPassType());
        
        // Create pass
        Pass pass = new Pass();
        pass.setUser(user);
        pass.setRoute(route);
        pass.setPassType(request.getPassType());
        pass.setStartDate(request.getStartDate());
        pass.setEndDate(endDate);
        pass.setPrice(price);
        pass.setPassCode("PASS" + System.currentTimeMillis());
        
        pass = passRepository.save(pass);
        
        // Create payment
        Payment payment = new Payment();
        payment.setPass(pass);
        payment.setAmount(price);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus(PaymentStatus.SUCCESS); // Simulated payment
        paymentRepository.save(payment);
        
        return pass;
    }
    
    public List<Pass> getUserPasses(String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return passRepository.findByUserAndActiveTrue(user);
    }
    
    private Double calculatePassPrice(PassType passType) {
        switch (passType) {
            case DAILY:
                return 50.0;
            case WEEKLY:
                return 300.0;
            case MONTHLY:
                return 1000.0;
            default:
                return 0.0;
        }
    }
    
    private LocalDate calculateEndDate(LocalDate startDate, PassType passType) {
        switch (passType) {
            case DAILY:
                return startDate.plusDays(1);
            case WEEKLY:
                return startDate.plusWeeks(1);
            case MONTHLY:
                return startDate.plusMonths(1);
            default:
                return startDate;
        }
    }
}
