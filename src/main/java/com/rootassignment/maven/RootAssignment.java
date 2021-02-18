package com.rootassignment.maven;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RootAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the input file: (e.g. Input.txt)");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        RootAssignment ra = new RootAssignment();
        ra.run(file);
    }

	public void run(File file) {
        List<Driver> allDrivers = new ArrayList<Driver>();
        try (Scanner inputScanner = new Scanner(file)){
            while (inputScanner.hasNextLine()) {
                String command = inputScanner.nextLine();
                String[] info = command.split(" ");
                if (command.contains("Driver")) {
                    allDrivers = executeDriverCommand(info, allDrivers);
                } else if (command.contains("Trip")) {
                    allDrivers= executeTripCommand(info, allDrivers);
                } else {
                    System.out.println("Command Error.");
                }
            }
        } catch (Exception e){
            System.out.println("No file found.");
            e.printStackTrace();
        }
        allDrivers = sortDriversByMiles(allDrivers);
        generateOutput(allDrivers);
    }

	public List<Driver> executeDriverCommand(String[] info, List<Driver> allDrivers){
        String name = info[1];
        Driver driver = new Driver(name);
        allDrivers.add(driver);
        return allDrivers;
    }

    public List<Driver> executeTripCommand(String[] info, List<Driver>allDrivers){
        String name = info[1];
        String startTime = info[2];
        String endTime = info[3];
        double milesDriven = Double.parseDouble(info[4]);
        double avgSpeed = findAvgSpeed(startTime, endTime, milesDriven);
        if(avgSpeed>5 && avgSpeed<100){
            for (Driver currentDriver: allDrivers){
                String currentDriverName = currentDriver.getName();
                if(currentDriverName.equals(name)){
                    double driverMiles = currentDriver.getTotalMiles() + milesDriven;
                    int totalMiles = (int) Math.round(driverMiles);
                    currentDriver.setTotalMiles(totalMiles);

                    ArrayList<Double> allTripSpeeds = currentDriver.getTripSpeeds();
                    allTripSpeeds.add(avgSpeed);
                    currentDriver.setTripSpeeds(allTripSpeeds);

                    int i=0;
                    for(double tripSpeed : allTripSpeeds){
                        i+=tripSpeed;
                    }
                    double size = allTripSpeeds.size();
                    int totalAvgSpeed = (int) Math.round(i/size);
                    currentDriver.setAvgSpeed(totalAvgSpeed);
                }
            }
        }
        return allDrivers;
    }

    public double findAvgSpeed(String startTime, String endTime, double milesDriven){
        int startTotalMins = determineTimeElapsed(startTime);
        int endTotalMins = determineTimeElapsed(endTime);
        double totalMins = endTotalMins - startTotalMins;
        double totalHours = totalMins/60;
        return milesDriven/totalHours;
    }

    public int determineTimeElapsed(String time){
        String[] timeSplit = time.split(":");
        String hoursString = timeSplit[0];
        String minsString = timeSplit[1];
        int startHours = Integer.parseInt(hoursString)*60;
        int startMins = Integer.parseInt(minsString);
        return startHours+startMins;
    }

    public List<Driver> sortDriversByMiles(List<Driver> allDrivers){
        List<Integer> allMiles = new ArrayList<Integer>();
        for(Driver currentDriver: allDrivers){
            allMiles.add(currentDriver.getTotalMiles());
        }
        Collections.sort(allMiles);
        Collections.reverse(allMiles);
        List<Driver> sortedDrivers = new ArrayList<Driver>();
        for(Integer currentInt: allMiles){
            for(Driver currentDriver: allDrivers){
                if(currentDriver.getTotalMiles()==currentInt && !sortedDrivers.contains(currentDriver)){
                    sortedDrivers.add(currentDriver);
                }
            }
        }
        return sortedDrivers;
    }

    public void generateOutput(List<Driver> sortedDrivers){
        List<Driver> previouslyReported = new ArrayList<Driver>();
        File report = new File("Report.txt");
        String info;
        try {
            FileWriter writer = new FileWriter(report);
            for(Driver currentDriver:sortedDrivers){
                if(!previouslyReported.contains(currentDriver)){
                    info = currentDriver.toString();
                    writer.write(info + "\n");
                    previouslyReported.add(currentDriver);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}