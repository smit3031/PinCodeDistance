package com.example.pincodedistance;

import com.example.pincodedistance.model.Distance;
import com.example.pincodedistance.model.DistanceResponse;
import com.example.pincodedistance.model.GoogleMapsResponse;
import com.example.pincodedistance.repository.DistanceRepository;
import com.example.pincodedistance.service.DistanceServiceImpl;
import com.example.pincodedistance.service.GoogleMapsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceImplTest {

    @Mock
    private GoogleMapsService googleMapsService;

    @Mock
    private DistanceRepository distanceRepository;

    @InjectMocks
    private DistanceServiceImpl distanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCalculateDistance() {
        String fromPincode = "141106";
        String toPincode = "110060";
        long distance = 10000;
        long duration = 3600;
        String[] route = {"141106", "110060"};

        GoogleMapsResponse googleMapsResponse = new GoogleMapsResponse();
        googleMapsResponse.setDistance(distance);
        googleMapsResponse.setDuration(duration);
        googleMapsResponse.setRoute(route);

        Distance distanceEntity = new Distance();
        distanceEntity.setFromPincode(fromPincode);
        distanceEntity.setToPincode(toPincode);
        distanceEntity.setDistance(distance);
        distanceEntity.setDuration(duration);
        distanceEntity.setRoute(String.join(" -> ", route));

        when(googleMapsService.getDistanceAndDuration(fromPincode, toPincode)).thenReturn(googleMapsResponse);
        when(distanceRepository.save(any())).thenReturn(distanceEntity);

        DistanceResponse result = distanceService.calculateDistance(fromPincode, toPincode);

        assertEquals(distance, result.getDistance());
        assertEquals(duration, result.getDuration());
        assertArrayEquals(route, result.getRoute().toArray());

        verify(googleMapsService, times(1)).getDistanceAndDuration(fromPincode, toPincode);
        verify(distanceRepository, times(1)).save(any());
    }

    @Test
    void testGetCachedDistance() {
        String fromPincode = "141106";
        String toPincode = "110060";
        long distance = 10000;
        long duration = 3600;
        String[] route = {"141106", "110060"};

        Distance distanceEntity = new Distance();
        distanceEntity.setFromPincode(fromPincode);
        distanceEntity.setToPincode(toPincode);
        distanceEntity.setDistance(distance);
        distanceEntity.setDuration(duration);
        distanceEntity.setRoute(String.join(" -> ", route));

        when(distanceRepository.findByFromPincodeAndToPincode(fromPincode, toPincode)).thenReturn(java.util.Optional.of(distanceEntity));

        DistanceResponse result = distanceService.getCachedDistance(fromPincode, toPincode);

        assertEquals(distance, result.getDistance());
        assertEquals(duration, result.getDuration());
        assertArrayEquals(route, result.getRoute().toArray());

        verify(distanceRepository, times(1)).findByFromPincodeAndToPincode(fromPincode, toPincode);
    }
}

