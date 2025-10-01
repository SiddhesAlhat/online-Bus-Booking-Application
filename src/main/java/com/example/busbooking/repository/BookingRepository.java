package com.example.busbooking.repository;

import com.example.busbooking.model.Booking;
import com.example.busbooking.model.User;
import com.example.busbooking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByUserAndStatus(User user, BookingStatus status);
    List<Booking> findByUserOrderByBookingTimeDesc(User user);
}