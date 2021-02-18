RootAssignment: Driver Tracking
    This application is designed to follow commands from an input file,
    and use that information to generate an output file. Written in java,
    this application utilizes OOP and unit testing to ensure a smooth process.

Installation:
    --Run this application from your cmd line (java RootAssignment.java)
    --When prompted, enter the name of your Input file into the console.

Usage:
    --Imports an input file from the cmd line.
    --Command "Driver" creates a driver object and adds it to a list of Drivers.
    --Command "Trip" finds average speed and updates corresponding driver's total miles and total average speed (mph).
    --Creates an output file "Report" which lists drivers and their statistics in descending order of miles driven.

Thought Process:
    When this application takes in a Driver Command, it creates a Driver Object.
    This was chosen because it allows the application to keep track of each Driver
    and their corresponding variables (name, total miles, average speed, etc).
    Each Driver object is added to an ArrayList, which is accessed and edited
    numerous times as the application processes new information from the input file.

    When the application takes in a Trip Command, the corresponding driver object
    is updated appropriately. This ensures that we maintain the list of drivers
    and their variables.

    Once every command is processed, the list of all Drivers is sorted
    in descending order of total miles driven. This sorted list is used to generate
    the output file, which displays all the information from each Driver object.

    Finally, this application makes use of unit testing to ensure that all methods
    are working correctly. This method of testing was chosen because if the application
    was ever to be edited or updated in any way, the tests would reflect which specific
    parts of the application would be functional, and which parts would break. This
    allows for longevity and reusability in the code.