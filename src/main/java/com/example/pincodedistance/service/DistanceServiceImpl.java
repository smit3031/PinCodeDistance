package com.example.pincodedistance.service;


import com.example.pincodedistance.model.Distance;
import com.example.pincodedistance.model.DistanceResponse;
import com.example.pincodedistance.model.GoogleMapsResponse;
import com.example.pincodedistance.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DistanceServiceImpl implements DistanceService {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private DistanceRepository distanceRepository;

    @Override
    public DistanceResponse calculateDistance(String fromPincode, String toPincode) {
        GoogleMapsResponse googleMapsResponse = googleMapsService.getDistanceAndDuration(fromPincode, toPincode);

        Distance distance = new Distance();
        distance.setFromPincode(fromPincode);
        distance.setToPincode(toPincode);
        distance.setDistance(googleMapsResponse.getDistance());
        distance.setDuration(googleMapsResponse.getDuration());
        distance.setRoute(String.join(" -> ", googleMapsResponse.getRoute()));
        distanceRepository.save(distance);

        DistanceResponse response = new DistanceResponse();
        response.setDistance(googleMapsResponse.getDistance());
        response.setDuration(googleMapsResponse.getDuration());
        response.setRoute(List.of(googleMapsResponse.getRoute()));

        return response;
    }

    @Override
    public DistanceResponse getCachedDistance(String fromPincode, String toPincode) {
        Optional<Distance> optionalDistance = distanceRepository.findByFromPincodeAndToPincode(fromPincode, toPincode);
        if (optionalDistance.isPresent()) {
            Distance distance = optionalDistance.get();
            DistanceResponse response = new DistanceResponse();
            response.setDistance(distance.getDistance());
            response.setDuration(distance.getDuration());
            response.setRoute(Arrays.asList(distance.getRoute().split(" -> ")));
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cached distance not found");
        }
    }
}
