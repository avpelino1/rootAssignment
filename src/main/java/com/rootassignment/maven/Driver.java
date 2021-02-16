package com.rootassignment.maven;

import java.util.ArrayList;

public class Driver {

    private final String name;
    private Integer totalMiles;
    private ArrayList<Integer> tripSpeeds = new ArrayList<>();
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

    public ArrayList<Integer> getTripSpeeds() {
        return tripSpeeds;
    }

    public void setTripSpeeds(ArrayList<Integer> tripSpeeds) {
        this.tripSpeeds = tripSpeeds;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public void print() {
        if(this.totalMiles==0){
            System.out.println(name+": "+totalMiles+" miles");
        } else {
            System.out.println(name + ": " + totalMiles + " miles @ " + avgSpeed + " mph");
        }
    }
}
