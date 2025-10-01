package com.example.busbooking.controller;

import com.example.busbooking.dto.request.RouteSearchRequest;
import com.example.busbooking.dto.response.ApiResponse;
import com.example.busbooking.dto.response.BusResponse;
import com.example.busbooking.model.Route;
import com.example.busbooking.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {
    
    @Autowired
    private RouteService routeService;
    
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<BusResponse>>> searchBuses(@Valid @RequestBody RouteSearchRequest request) {
        List<BusResponse> buses = routeService.searchBuses(request);
        return ResponseEntity.ok(ApiResponse.success("Buses found", buses));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Route>>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(ApiResponse.success("Routes retrieved", routes));
    }
}
