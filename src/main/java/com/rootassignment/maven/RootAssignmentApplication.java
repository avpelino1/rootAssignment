package com.rootassignment.maven;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class RootAssignmentApplication {

    //this method is the main: the program starts here
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.println("Error, usage: java ClassName inputfile");
            System.exit(1);
        }
        File file = new File (args[0]);
	    RootAssignmentApplication raa = new RootAssignmentApplication();
		raa.run(file);
	}

    //input file and follow commands, then generate report
	public void run(File file) throws FileNotFoundException {
        List<Driver> allDrivers = new ArrayList<Driver>();
        try (Scanner inputScanner = new Scanner(file)){
            while (inputScanner.hasNextLine()) {
                String command = inputScanner.nextLine();
                String[] info = command.split(" ");
                if (command.contains("Driver")) {
                    Driver driver = executeDriverCommand(info);
                    allDrivers.add(driver);
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

        TreeSet<Integer>sortedMiles = sortDriversByMiles(allDrivers);
        generateOutput(sortedMiles, allDrivers);
    }

	//create a driver object + add it to the list
	public Driver executeDriverCommand(String[] info){
        String name = info[1];
        return new Driver(name);
    }

    //log trip information and store it in the driver object
    public List<Driver> executeTripCommand(String[] info, List<Driver>allDrivers){
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

    public TreeSet<Integer> sortDriversByMiles(List<Driver> allDrivers){
        TreeSet<Integer> miles = new TreeSet<Integer>();
        for(Driver currentDriver: allDrivers){
            miles.add(currentDriver.getTotalMiles());
        }
        return (TreeSet<Integer>)miles.descendingSet();
    }

    //generate output file based on the driver objects
    public void generateOutput(TreeSet<Integer>sortedMiles, List<Driver> allDrivers){
        List<Driver> previouslyReported = new ArrayList<Driver>();
        for(Integer driversMiles:sortedMiles){
            for(Driver currentDriver:allDrivers){
                if(currentDriver.getTotalMiles()==driversMiles&& !previouslyReported.contains(currentDriver)){
                    currentDriver.print();
                    previouslyReported.add(currentDriver);
                }
            }

        }

    }

}
