package com.rootassignment.maven;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RootAssignmentTests {

	RootAssignmentApplication raa = new RootAssignmentApplication();

	@Test
	public void testDriverCommandExecution(){
		String[] info = new String[2];
		info[0] = "Driver";
		info[1] = "Kumi";
		Driver actual = raa.executeDriverCommand(info);
		Driver expected = new Driver("Kumi");
		assertEquals(actual.getName(), expected.getName());
	}

	@Test
	public void testAvgSpeedFinder(){
		String startTime="03:00";
		String endTime="03:30";
		double milesDriven=15;
		assertEquals(30, raa.findAvgSpeed(startTime, endTime, milesDriven));
	}

	@Test
	public void testTripCommandExecution(){
		List<Driver> allDrivers = new ArrayList<Driver>();
		Driver driver = new Driver("Kumi");
		allDrivers.add(driver);
		String[] info = new String[5];
		info[0] = "Trip";
		info[1] = "Kumi";
		info[2] = "03:00";
		info[3] = "03:30";
		info[4] = "15";
		List<Driver> actual = raa.executeTripCommand(info, allDrivers);
		assertEquals(actual.get(0).getTotalMiles(), 15);
	}

}

