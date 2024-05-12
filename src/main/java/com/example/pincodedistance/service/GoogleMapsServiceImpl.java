package com.example.pincodedistance.service;

import com.example.pincodedistance.model.GoogleMapsResponse;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {

    private static final String API_KEY = "YOUR_GOOGLE_MAPS_API_KEY";

    @Override
    public GoogleMapsResponse getDistanceAndDuration(String fromPincode, String toPincode) {
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();

            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                    .origins(fromPincode)
                    .destinations(toPincode)
                    .await();

            long distance = distanceMatrix.rows[0].elements[0].distance.inMeters;
            long duration = distanceMatrix.rows[0].elements[0].duration.inSeconds;

            GoogleMapsResponse response = new GoogleMapsResponse();
            response.setDistance(distance);
            response.setDuration(duration);
            response.setRoute(new String[]{fromPincode, toPincode});

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch distance and duration from Google Maps API", e);
        }
    }
}

