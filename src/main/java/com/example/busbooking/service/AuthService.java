package com.example.busbooking.service;

import com.example.busbooking.dto.request.LoginRequest;
import com.example.busbooking.dto.request.RegisterRequest;
import com.example.busbooking.dto.response.AuthResponse;
import com.example.busbooking.exception.BadRequestException;
import com.example.busbooking.model.User;
import com.example.busbooking.repository.UserRepository;
import com.example.busbooking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Phone number already registered");
        }
        
        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }
        
        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        
        user = userRepository.save(user);
        
        // Generate token
        String token = jwtUtil.generateToken(user.getPhone(), user.getRole());
        
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new BadRequestException("Invalid phone number or password"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid phone number or password");
        }
        
        if (!user.isActive()) {
            throw new BadRequestException("Account is inactive");
        }
        
        // Generate token
        String token = jwtUtil.generateToken(user.getPhone(), user.getRole());
        
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }
}
