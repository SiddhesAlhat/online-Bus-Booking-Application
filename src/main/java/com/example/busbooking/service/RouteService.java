package com.example.busbooking.service;

import com.example.busbooking.dto.request.RouteSearchRequest;
import com.example.busbooking.dto.response.BusResponse;
import com.example.busbooking.model.Bus;
import com.example.busbooking.model.Route;
import com.example.busbooking.repository.BusRepository;
import com.example.busbooking.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    
    @Autowired
    private RouteRepository routeRepository;
    
    @Autowired
    private BusRepository busRepository;
    
    public List<BusResponse> searchBuses(RouteSearchRequest request) {
        List<Route> routes = routeRepository.findBySourceAndDestinationAndActiveTrue(
                request.getSource(), request.getDestination());
        
        List<BusResponse> busResponses = new ArrayList<>();
        
        for (Route route : routes) {
            List<Bus> buses = busRepository.findByRouteAndActiveTrue(route);
            
            for (Bus bus : buses) {
                BusResponse response = new BusResponse();
                response.setId(bus.getId());
                response.setBusNumber(bus.getBusNumber());
                response.setSource(route.getSource());
                response.setDestination(route.getDestination());
                response.setBusType(bus.getBusType());
                response.setDepartureTime(bus.getDepartureTime());
                response.setArrivalTime(bus.getArrivalTime());
                response.setFare(bus.getFare());
                response.setAvailableSeats(bus.getAvailableSeats());
                response.setDuration(route.getDuration());
                
                busResponses.add(response);
            }
        }
        
        return busResponses;
    }
    
    public List<Route> getAllRoutes() {
        return routeRepository.findByActiveTrue();
    }
}
