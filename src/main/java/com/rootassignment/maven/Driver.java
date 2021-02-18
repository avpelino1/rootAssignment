package com.rootassignment.maven;

import java.util.ArrayList;

public class Driver {

    private final String name;
    private Integer totalMiles;
    private ArrayList<Double> tripSpeeds = new ArrayList<>();
    private int avgSpeed;

    public Driver(String name){
        this.name = name;
        this.totalMiles = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalMiles() {
        return totalMiles;
    }

    public void setTotalMiles(int totalMiles) {
        this.totalMiles = totalMiles;
    }

    public ArrayList<Double> getTripSpeeds() {
        return tripSpeeds;
    }

    public void setTripSpeeds(ArrayList<Double> tripSpeeds) {
        this.tripSpeeds = tripSpeeds;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Override
    public String toString() {
        if(this.totalMiles==0){
            return(name+": "+totalMiles+" miles");
        } else {
            return(name + ": " + totalMiles + " miles @ " + avgSpeed + " mph");
        }
    }
}
