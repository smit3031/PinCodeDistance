package com.example.pincodedistance.repository;


import com.example.pincodedistance.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistanceRepository extends JpaRepository<Distance, Long> {
    Optional<Distance> findByFromPincodeAndToPincode(String fromPincode, String toPincode);
}

