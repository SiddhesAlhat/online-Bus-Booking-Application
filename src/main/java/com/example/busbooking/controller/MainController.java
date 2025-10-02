package com.example.busbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.busbooking.model.User;
import com.example.busbooking.repository.BookingRepository;
import com.example.busbooking.repository.BusRepository;
import com.example.busbooking.repository.UserRepository;

@Controller

public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String Login() {
		return "login";
	}
	
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	
	@GetMapping("/booking")
	public String booking() {
		return "booking";
	}
	
	@GetMapping("/bookings")
	public String bookings() {
		return "bookings";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}
	
	@GetMapping("/seat-selection")
	public String seatSelection() {
		return "seat-selection";
	}
	
	@GetMapping("/ticket")
	public String ticket() {
		return "ticket";
	}
	
	@GetMapping("/cancellation")
	public String cancellation() {
		return "cancellation";
	}
	
	@PostMapping()
	public String register(User user) {
		return " ";
	}
	
	
}
