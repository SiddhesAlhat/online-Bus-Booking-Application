package com.example.demo.Service;


import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.BusRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
	@Autowired
     BookingRepository bookingRepo;
	@Autowired
	BusRepository busRepo;
	
	@Transactional
	public Booking bookTicket()
	
    public BookingService(BookingRepository bookingRepo) { this.bookingRepo = bookingRepo; }

    public Booking save(Booking b) { return bookingRepo.save(b); }
}
