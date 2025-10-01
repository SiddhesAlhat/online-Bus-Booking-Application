package com.example.busbooking.repository;

import com.example.busbooking.model.Pass;
import com.example.busbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PassRepository extends JpaRepository<Pass, Long> {
    List<Pass> findByUserAndActiveTrue(User user);
    Optional<Pass> findByPassCode(String passCode);
}
