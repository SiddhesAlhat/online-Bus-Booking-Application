package com.example.busbooking.controller;

import com.example.busbooking.dto.request.PassPurchaseRequest;
import com.example.busbooking.dto.response.ApiResponse;
import com.example.busbooking.model.Pass;
import com.example.busbooking.service.PassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passes")
@CrossOrigin(origins = "*")
public class PassController {
    
    @Autowired
    private PassService passService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Pass>> purchasePass(
            @Valid @RequestBody PassPurchaseRequest request,
            @RequestHeader("X-User-Phone") String userPhone) {
        Pass pass = passService.purchasePass(request, userPhone);
        return ResponseEntity.ok(ApiResponse.success("Pass purchased successfully", pass));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Pass>>> getUserPasses(
            @RequestHeader("X-User-Phone") String userPhone) {
        List<Pass> passes = passService.getUserPasses(userPhone);
        return ResponseEntity.ok(ApiResponse.success("Passes retrieved", passes));
    }
}
