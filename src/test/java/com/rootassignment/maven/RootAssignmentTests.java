package com.rootassignment.maven;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RootAssignmentTests {

	RootAssignment ra = new RootAssignment();

	@Test
	public void testDriverCommandExecution(){
		List<Driver> allDrivers = new ArrayList<Driver>();
		String[] info = new String[2];
		info[0] = "Driver";
		info[1] = "Kumi";
		List<Driver> actualList = ra.executeDriverCommand(info, allDrivers);
		assertEquals(info[1], actualList.get(0).getName());
	}

	@Test
	public void testAvgSpeedWholeNums(){
		String startTime="03:00";
		String endTime="03:30";
		double milesDriven=15;
		assertEquals(30, ra.findAvgSpeed(startTime, endTime, milesDriven));
	}

	@Test
	public void testAvgSpeedDecimals(){
		String startTime="03:00";
		String endTime="03:23";
		double milesDriven=14;
		assertEquals(36, ra.findAvgSpeed(startTime, endTime, milesDriven));
	}

	@Test public void testAvgSpeedDiscarding(){
		List<Driver> allDrivers = new ArrayList<Driver>();
		Driver driver = new Driver("Kumi");
		allDrivers.add(driver);
		String[] info = new String[5];
		info[0] = "Trip";
		info[1] = "Kumi";
		info[2] = "03:00";
		info[3] = "03:05";
		info[4] = "75";
		List<Driver> actualList = ra.executeTripCommand(info, allDrivers);
		int actual = actualList.get(0).getTotalMiles();
		assertEquals(0, actual);
	}

	@Test
	public void testTripCommandExecution(){
		List<Driver> allDrivers = new ArrayList<Driver>();
		Driver driver = new Driver("Kumi");
		Driver driver2 = new Driver("Jane");
		allDrivers.add(driver);
		allDrivers.add(driver2);
		String[] info = new String[5];
		info[0] = "Trip";
		info[1] = "Kumi";
		info[2] = "03:00";
		info[3] = "03:30";
		info[4] = "15";
		List<Driver> actual = ra.executeTripCommand(info, allDrivers);
		assertEquals(15, actual.get(0).getTotalMiles());
	}

	@Test
	public void testSortingDriversByMiles() {
		List<Driver> allDrivers = new ArrayList<Driver>();
		Driver driver = new Driver("Kumi");
		driver.setTotalMiles(15);
		Driver driver2 = new Driver("Jane");
		driver2.setTotalMiles(45);
		allDrivers.add(driver);
		allDrivers.add(driver2);

		List<Driver> actualSortedDrivers = ra.sortDriversByMiles(allDrivers);

		Integer actual = actualSortedDrivers.get(0).getTotalMiles();
		Integer expected = 45;
		assertEquals(expected, actual);
	}

	@Test
	public void testSortingDriversWithSameMiles() {
		List<Driver> allDrivers = new ArrayList<Driver>();
		Driver driver = new Driver("Kumi");
		driver.setTotalMiles(20);
		Driver driver2 = new Driver("Jane");
		driver2.setTotalMiles(20);
		allDrivers.add(driver);
		allDrivers.add(driver2);

		List<Driver> actualSortedDrivers = ra.sortDriversByMiles(allDrivers);

		String firstDriver = actualSortedDrivers.get(0).getName();
		String secondDriver = actualSortedDrivers.get(1).getName();
		assertNotSame(firstDriver, secondDriver);
	}
}

