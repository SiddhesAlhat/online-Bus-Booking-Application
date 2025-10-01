package com.example.busbooking.repository;

import com.example.busbooking.model.Bus;
import com.example.busbooking.model.Seat;
import com.example.busbooking.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusAndTravelDateAndStatus(Bus bus, LocalDate travelDate, SeatStatus status);
    List<Seat> findByBusAndTravelDate(Bus bus, LocalDate travelDate);
}
