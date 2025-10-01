package com.example.busbooking.repository;

import com.example.busbooking.model.Bus;
import com.example.busbooking.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByRouteAndActiveTrue(Route route);
    List<Bus> findByActiveTrue();
}
