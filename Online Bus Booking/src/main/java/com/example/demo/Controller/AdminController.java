package com.example.demo.Controller;



import com.example.demo.entity.Bus;
import com.example.demo.entity.Route;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.RouteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RouteRepository routeRepo;
    private final BusRepository busRepo;

    public AdminController(RouteRepository routeRepo, BusRepository busRepo) {
        this.routeRepo = routeRepo;
        this.busRepo = busRepo;
    }

    @GetMapping("/routes")
    public String listRoutes(Model model) {
        model.addAttribute("routes", routeRepo.findAll());
        return "admin/routes";
    }

    @GetMapping("/routes/new")
    public String newRouteForm(Model model) {
        model.addAttribute("route", new Route());
        return "admin/newRoute";
    }

    @PostMapping("/routes")
    public String saveRoute(@ModelAttribute Route route) {
        routeRepo.save(route);
        return "redirect:/admin/routes";
    }
}

