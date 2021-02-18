package com.rootassignment.maven;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RootAssignment {

    public static void main(String[] args) {
//        if(args.length < 1) {
//            System.out.println("Error, usage: java RootAssignment.java inputfile");
//            System.exit(1);
//        }
//        File file = new File("Input.txt");

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        File file = new File(fileName);
        scanner.close();
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

        int avgSpeed = findAvgSpeed(startTime, endTime, milesDriven);
        if(avgSpeed>5 && avgSpeed<100){
            for (Driver currentDriver: allDrivers){
                String currentDriverName = currentDriver.getName();

                if(currentDriverName.equals(name)){
                    double driverMiles = currentDriver.getTotalMiles() + milesDriven;
                    int totalMiles = (int)driverMiles;
                    currentDriver.setTotalMiles(totalMiles);

                    ArrayList<Integer> allTripSpeeds = currentDriver.getTripSpeeds();
                    allTripSpeeds.add(avgSpeed);
                    currentDriver.setTripSpeeds(allTripSpeeds);
                    int i=0;
                    for(int tripSpeed : allTripSpeeds){
                        i+=tripSpeed;
                    }
                    int totalAvgSpeed = i/allTripSpeeds.size();
                    currentDriver.setAvgSpeed(totalAvgSpeed);
                }
            }
        }
        return allDrivers;
    }

    public int findAvgSpeed(String startTime, String endTime, double milesDriven){
        String[] startSplit = startTime.split(":");
        String startHoursString = startSplit[0];
        String startMinsString = startSplit[1];
        int startHours = Integer.parseInt(startHoursString)*60;
        int startMins = Integer.parseInt(startMinsString);
        int startTotalMins = startHours+startMins;

        String[] endSplit = endTime.split(":");
        String endHoursString = endSplit[0];
        String endMinsString = endSplit[1];
        int endHours = Integer.parseInt(endHoursString)*60;
        int endMins = Integer.parseInt(endMinsString);
        int endTotalMins = endHours+endMins;

        double totalMins = endTotalMins - startTotalMins;
        double totalHours = totalMins/60;

        double milesPerHour = milesDriven/totalHours;
        return (int)milesPerHour;
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
