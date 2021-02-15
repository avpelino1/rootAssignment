package com.rootassignment.maven;

import java.util.ArrayList;

public class Driver {

    private String name;
    private int totalMiles;
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

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", totalMiles=" + totalMiles +
                ", avgSpeed=" + avgSpeed +
                '}';
    }
}
