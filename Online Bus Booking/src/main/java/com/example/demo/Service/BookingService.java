package com.example.demo.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.BusRepository;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Bus;
import com.example.demo.entity.User;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    BusRepository busRepo;

    @Transactional
    public Booking bookTicket(User user, Bus bus, Date date) {
        if (bus.getSeats() <= 0) {
            throw new RuntimeException("No seats available.");
        }

        // Decrement seat
        bus.setSeats(bus.getSeats() - 1);
        busRepo.save(bus);

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setTravelDate(date);
        booking.setStatus("PAID"); // set status

        return bookingRepo.save(booking);
    }
}
