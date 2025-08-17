package com.example.demo.Controller;

import java.util.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repository.BusRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.BookingService;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Bus;
import com.example.demo.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BusRepository busRepo;

    @Autowired
    private BookingService bookingService;

    // Redirect root to login
    @GetMapping("/index")
    public String home() {
        return "login";
    }

    // Register page
    @GetMapping("/register")
    public String regForm(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    // Save user registration
    @PostMapping("/register")
    public String register(@ModelAttribute User u) {
        userRepo.save(u);
        return "login";
    }

    // Login form page
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Login logic and session setup
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model m) {

        User u = userRepo.findByUsername(username);

        if (u != null && u.getPassword().equals(password)) {
            session.setAttribute("loggedUser", u);
            m.addAttribute("user", u);
            m.addAttribute("allBuses", busRepo.findAll());
            return u.isAdmin() ? "admin_panel" : "user_dashboard";
        }

        m.addAttribute("error", "Invalid credentials");
        return "login";
    }

    // User dashboard page
    @GetMapping("/user_dashboard")
    public String userDashboard(HttpSession session, Model m) {
        //User user = (User) session.getAttribute("loggedUser");
    	User user = userRepo.findByUsername("testuser"); // your test username

        if (user == null) {
            return "redirect:/login";
        }

        m.addAttribute("user", user);
        m.addAttribute("allBuses", busRepo.findAll());
        return "user_dashboard";
    }

    // Handle booking form submission
    @PostMapping("/book_ticket")
    public String bookTicket(@RequestParam int busId,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date travelDate,
                             HttpSession session,
                             Model model) {

        User user = (User) session.getAttribute("loggedUser");
        Bus bus = busRepo.findById(busId).orElse(null);

        if (user != null && bus != null) {
            Booking booking = bookingService.bookTicket(user, bus, travelDate);
            model.addAttribute("booking", booking);
            return "confirmation";
        } else {
            model.addAttribute("error", "Booking failed. Invalid user or bus.");
            model.addAttribute("allBuses", busRepo.findAll());
            return "user_dashboard";
        }
    }

}