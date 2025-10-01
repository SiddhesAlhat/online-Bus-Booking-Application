package com.example.busbooking.repository;

import com.example.busbooking.model.Ticket;
import com.example.busbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByBooking(Booking booking);
    Optional<Ticket> findByTicketNumber(String ticketNumber);
}
