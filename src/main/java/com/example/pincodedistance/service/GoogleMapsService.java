package com.example.pincodedistance.service;

import com.example.pincodedistance.model.GoogleMapsResponse;

public interface GoogleMapsService {
    GoogleMapsResponse getDistanceAndDuration(String fromPincode, String toPincode);
}
