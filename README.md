# Carvana-Vending-Machine
This creates a 2D array that is modeled after a Carvana Vending Machine. This program shows my experience
with basic Object Oriented Programming concepts, such as inheritance and interfaces.

SPECS:
 This program opens the "VendingMachineCars.txt"
 file and creates a 2D array of sizes specified
 by the file. It calls displayCars() from the 
 VendingMachine class to display the contents
 of the car tower. It then creates a vehicle
 report for each car in the the car tower and 
 saves these reports in an array list. It uses
 Collections.sort() to sort the array list,
 inventory, by price. It is able to use 
 Collections.sort() because the vehicle report 
 class implemented the Comparator interface and 
 compared the vehicle reports on price. After
 sorting inventory, it calls the implemented 
 print() method to print all of the vehicle
 reports.

![screenshot](https://github.com/bridgidrankin/Carvana-Vending-Machine/blob/master/carvana_screenshot.JPG)
