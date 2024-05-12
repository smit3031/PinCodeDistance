package com.example.pincodedistance.model;

import lombok.Data;

@Data
public class DistanceRequest {
    private String fromPincode;
    private String toPincode;
}

