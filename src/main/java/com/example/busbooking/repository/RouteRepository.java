package com.example.busbooking.repository;

import com.example.busbooking.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findBySourceAndDestinationAndActiveTrue(String source, String destination);
    List<Route> findByActiveTrue();
}
