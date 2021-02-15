Outline:

1) INPUT FILE:
    --space delimited
    --execute each command as it is read into the program (only read once)

2) DRIVER command:
    --create a new driver object
    --contain driver's name and totalMiles = 0;
    --contain empty property for average speed
    --add driver object to master list of all drivers
    --eventually used to generate a Driver Report

3) TRIP command:
    --find average speed of trip
    --discard any trip <5mph or >100mph
    --add miles and average speed to the corresponding driver object

4) Generate report:
    --create output file "report"
    --contain driver's name, total miles, and avg speed
    --be ordered by most to least miles driven

5) TESTS:
    --file reading
        --driver command and trip command
        --incorrect commands
    --driver command
        --driver object creation (name and totalMiles=0)
        --master list of driver objects
    --trip command
        --avgSpeed
        --discard certain trips
        --add miles and avgSpeed to correct driver objects
    --report generation
        --output file creation
        --info generated by report (correct info, in the right order)
    --other methods
        --every other unmentioned methods