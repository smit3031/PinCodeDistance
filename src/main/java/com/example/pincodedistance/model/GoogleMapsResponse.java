package com.example.pincodedistance.model;

public class GoogleMapsResponse {
    private long distance;
    private long duration;
    private String[] route;

    // Getters and setters

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String[] getRoute() {
        return route;
    }

    public void setRoute(String[] route) {
        this.route = route;
    }
}

