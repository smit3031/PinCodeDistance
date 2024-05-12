package com.example.pincodedistance.service;

import com.example.pincodedistance.model.DistanceResponse;

public interface DistanceService {
    DistanceResponse calculateDistance(String fromPincode, String toPincode);
    DistanceResponse getCachedDistance(String fromPincode, String toPincode);
}

