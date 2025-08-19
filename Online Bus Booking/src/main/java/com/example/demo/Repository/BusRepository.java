package com.example.demo.repository;

import com.example.demo.entity.Bus;
import com.example.demo.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByRoute(Route route);
}
