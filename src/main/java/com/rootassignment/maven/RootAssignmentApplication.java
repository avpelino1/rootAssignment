package com.rootassignment.maven;

import org.springframework.boot.SpringApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RootAssignmentApplication {

    //keep track of our driver objects, so we can print them in the report later
    private List<Driver> allDrivers = new ArrayList<Driver>();

    //this method is the main: the program starts here
    public static void main(String[] args) throws FileNotFoundException {
	    RootAssignmentApplication raa = new RootAssignmentApplication();
		raa.run();
	}

    //input file and follow commands, then generate report
	public void run() throws FileNotFoundException {
        File inputFile = new File("C:\\Users\\avpel\\workspace\\root-assignment\\Input.txt");
        try (Scanner inputScanner = new Scanner(inputFile)){
            while (inputScanner.hasNextLine()) {
                String command = inputScanner.nextLine();
                String[] info = command.split(" ");
                if (command.contains("Driver")) {
                    executeDriverCommand(info);
                } else if (command.contains("Trip")) {
                    executeTripCommand(info);
                } else {
                    System.out.println("ERROR: unknown command");
                }
            }
            inputScanner.close();
            System.out.println(allDrivers.toString());
        } catch (Exception e){
            System.out.println("No file found.");
            e.printStackTrace();
        }

        printReportFile();

    }

	//create a driver object + add it to the list
	public void executeDriverCommand(String[] info){
        String name = info[1];
        Driver driver = new Driver(name);
        allDrivers.add(driver);
    }

    public void executeTripCommand(String[] info){
        String name = info[1];
        String startTime = info[2];
        String endTime = info[3];
        double milesDriven = Double.parseDouble(info[4]);

        int avgSpeed = findAvgSpeed(startTime, endTime, milesDriven);
        if(avgSpeed>5 && avgSpeed<100){
            int roundedMilesDriven = (int) milesDriven;
            for (Driver currentDriver: allDrivers){
                String currentDriverName = currentDriver.getName();

                if(currentDriverName.equals(name)){
                    int driverMiles = currentDriver.getTotalMiles() + roundedMilesDriven;
                    currentDriver.setTotalMiles(driverMiles);

                    ArrayList<Integer> allTripSpeeds = currentDriver.getTripSpeeds();
                    allTripSpeeds.add(avgSpeed);
                    currentDriver.setTripSpeeds(allTripSpeeds);
                    int i=0;
                    for(int tripSpeed : allTripSpeeds){
                        i+=tripSpeed;
                    }
                    int totalAvgSpeed = i/allTripSpeeds.size();
                    currentDriver.setAvgSpeed(totalAvgSpeed);
                    currentDriver.toString();
                }
            }
        }
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
        int avgSpeed = (int)milesPerHour;

        return avgSpeed;
    }

    public void printReportFile(){

    }

}
