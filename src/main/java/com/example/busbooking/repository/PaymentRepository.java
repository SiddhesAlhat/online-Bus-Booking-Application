package com.example.busbooking.repository;

import com.example.busbooking.model.Payment;
import com.example.busbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
    Optional<Payment> findByTransactionId(String transactionId);
}
