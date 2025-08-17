package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repository.BusRepository;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Bus;
import com.example.demo.entity.Route;
import com.example.demo.entity.User;

//@Controller
//public class AdminController {
//
//    @Autowired
//    private BusRepository busRepo;
//
//    @Autowired
//    private RouteRepository routeRepo;
//
//    @Autowired
//    private UserRepository userRepo;
//	public List<Bus> getBus(){
//		List<Bus> data= busRepo.findAll();	
//		return data;
//	}
//	
//	public List<Route> getrouteList(){
//		List<Route> data =routeRepo.findAll();	
//		return data;
//	}
//
//   
//
//	
//    @GetMapping("/ad")
//    public String adminPanel(Model model, Principal principal) {
//        User user = userRepo.findByUsername(principal.getName());
//        if (!user.isAdmin()) 
//        {
//            return "redirect:/user_dashboard";
//        }
//
//        model.addAttribute("user", user);
//        model.addAttribute("buses", busRepo.findAll());
//        model.addAttribute("routes", routeRepo.findAll()); // for dropdown menu
//        model.addAttribute("newBus", new Bus()); // empty bus object for add form
//        return "admin_panel";
//        
//    }
//
//   
//    @PostMapping("/add_bus")
//    public String addBus(@ModelAttribute Bus newBus) {
//        busRepo.save(newBus);
//        return "redirect:/admin_panel";
//    }
//
//    
//    @GetMapping("/edit_bus/{id}")
//    public String editBus(@PathVariable int id, Model m, Principal principal) {
//        Optional<Bus> bus = busRepo.findById(id);
//        if (bus.isPresent()) {
//            m.addAttribute("user", userRepo.findByUsername(principal.getName()));
//            m.addAttribute("editBus", bus.get());
//            m.addAttribute("buses", busRepo.findAll());
//            m.addAttribute("routes", routeRepo.findAll());
//            m.addAttribute("newBus", new Bus());
//            return "admin_panel";
//        }
//        return "redirect:/admin_panel";
//    }
//
//   
//    @PostMapping("/update_bus")
//    public String updateBus(@ModelAttribute Bus editBus) {
//        busRepo.save(editBus);
//        return "redirect:/admin_panel";
//    }
//
//    @GetMapping("/delete_bus/{id}")
//    public String deleteBus(@PathVariable int id) {
//        busRepo.deleteById(id);
//        return "redirect:/admin_panel";
//    }

@Controller
public class AdminController {

    @Autowired
    private BusRepository busRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/admin_panel")
    public String adminPanel(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        if (!user.isAdmin()) {
            return "redirect:/user_dashboard";
        }

        model.addAttribute("user", user);
        model.addAttribute("buses", busRepo.findAll());
        model.addAttribute("routes", routeRepo.findAll()); 
        model.addAttribute("newBus", new Bus()); 
        return "admin_panel";
    }

    @PostMapping("/add_bus")
    public String addBus(@ModelAttribute Bus newBus) {
        busRepo.save(newBus);
        return "redirect:/admin_panel";
    }

    @GetMapping("/edit_bus/{id}")
    public String editBus(@PathVariable int id, Model m, Principal principal) {
        Bus bus = busRepo.findById(id).orElse(null);
        if (bus == null) {
            return "redirect:/admin_panel";
        }

        m.addAttribute("user", userRepo.findByUsername(principal.getName()));
        m.addAttribute("editBus", bus);
        m.addAttribute("buses", busRepo.findAll());
        m.addAttribute("routes", routeRepo.findAll());
        m.addAttribute("newBus", new Bus());
        return "admin_panel";
    }

    @PostMapping("/update_bus")
    public String updateBus(@ModelAttribute Bus editBus) {
        busRepo.save(editBus);
        return "redirect:/admin_panel";
    }

    @GetMapping("/delete_bus/{id}")
    public String deleteBus(@PathVariable int id) {
        busRepo.deleteById(id);
        return "redirect:/admin_panel";
    }
}

//}