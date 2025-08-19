package com.example.demo.Controller;


import com.example.demo.entity.Booking;
import com.example.demo.entity.Bus;
import com.example.demo.entity.User;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import com.example.demo.Service.BookingService;
import com.example.demo.Service.UserService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BusRepository busRepo;

    @Autowired
    private BookingService bookingService;
    
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/register")
    public String showRegForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole("USER");
        userService.save(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // Login logic and session setup
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model m) {

        User u = userRepo.findByUsername(username);
//    	User u = userRepo.findByEmail(username);

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
//    @PostMapping("/book_ticket")
//    public String bookTicket(@RequestParam int busId,
//                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date travelDate,
//                             HttpSession session,
//                             Model model) {
//
//        User user = (User) session.getAttribute("loggedUser");
//        Bus bus = busRepo.findById(busId).orElse(null);
//       
//        
//
//        if (user != null && bus != null) {
//            Booking booking = bookingService.bookTicket(user, bus, travelDate);
//            model.addAttribute("booking", booking);
//            return "confirmation";
//        } else {
//            model.addAttribute("error", "Booking failed. Invalid user or bus.");
//            model.addAttribute("allBuses", busRepo.findAll());
//            return "user_dashboard";
//        }
//    }
    
    
    
    
}
