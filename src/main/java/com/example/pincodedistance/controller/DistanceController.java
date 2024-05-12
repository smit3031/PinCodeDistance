package com.example.pincodedistance.controller;

import com.example.pincodedistance.model.DistanceRequest;
import com.example.pincodedistance.model.DistanceResponse;
import com.example.pincodedistance.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    @PostMapping("/distance")
    public ResponseEntity<DistanceResponse> calculateDistance(@RequestBody DistanceRequest request) {
        DistanceResponse response = distanceService.calculateDistance(request.getFromPincode(), request.getToPincode());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cached-distance")
    public ResponseEntity<DistanceResponse> getCachedDistance(@RequestParam String fromPincode, @RequestParam String toPincode) {
        DistanceResponse response = distanceService.getCachedDistance(fromPincode, toPincode);
        return ResponseEntity.ok(response);
    }
}

