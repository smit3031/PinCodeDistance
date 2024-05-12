package com.example.pincodedistance.model;

import lombok.Data;

import java.util.List;

@Data
public class DistanceResponse {
    private double distance;
    private double duration;
    private List<String> route;
}

