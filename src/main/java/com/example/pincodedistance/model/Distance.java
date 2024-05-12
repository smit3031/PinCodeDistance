package com.example.pincodedistance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "distance")
@Data
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromPincode;
    private String toPincode;
    private double distance;
    private double duration;
    private String route;
}
