import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/*
 *
 * This program opens the "VendingMachineCars.txt"
 * file and creates a 2D array of sizes specified
 * by the file. It calls displayCars() from the 
 * VendingMachine class to display the contents
 * of the car tower. It then creates a vehicle
 * report for each car in the the car tower and 
 * saves these reports in an array list. It uses
 * Collections.sort() to sort the array list,
 * inventory, by price. It is able to use 
 * Collections.sort() because the vehicle report 
 * class implemented the Comparator interface and 
 * compared the vehicle reports on price. After
 * sorting inventory, it calls the implemented 
 * print() method to print all of the vehicle
 * reports.
 *
 */

public class Carvana {

	public static void main(String[] args) throws FileNotFoundException {
		
		//open file for reading
		final String FILE_NAME = "VendingMachineCars.txt";
		File fileName = new File(FILE_NAME);
		Scanner carFile = new Scanner (fileName);
		
		//get number of columns and rows
		int numberSlots = carFile.nextInt();
		int numberColumns = carFile.nextInt();
		
		//create vending machine
		VendingMachine vendingMachine = new VendingMachine (numberSlots, numberColumns);
		
		//populate array with cars from file
		while (carFile.hasNext()) {
			int columnNumber = carFile.nextInt();
			int slotNumber = carFile.nextInt();
			double price = carFile.nextDouble();
			int year = carFile.nextInt();
			String manufacturer = carFile.next();
			String model = carFile.nextLine();
			Car car = new Car (price, year, manufacturer, model);
			vendingMachine.addCarToTower(slotNumber, columnNumber, car);
		}
		
		carFile.close();
		
		//Display the loaded vending machine
		System.out.println("Loading cars into vending machine...\n");
		vendingMachine.displayCars();
		
		//print inventory report
		printInventory(vendingMachine);
		
	}//main

	public static void printInventory(VendingMachine vendingMachine) {
		
		//create list of vehicle reports
		ArrayList<VehicleReport> inventory = new ArrayList<>();
		
		

		//create a vehicle report for each car
		for (int i = 1; i < vendingMachine.getNumberSlots(); i++) {	
				for (int j = 1; j < vendingMachine.getNumberColumns(); j++) {
					
					Car aCar = vendingMachine.getCar(i,j);
					if (aCar != null) {
						double price = aCar.getPrice();
						int year = aCar.getYear();
						String manufacturer = aCar.getManufacturer();
						String model = aCar.getModel();
						VehicleReport vehicleReport = new VehicleReport(i, j, price, year, manufacturer, model);
						inventory.add(vehicleReport);
					}
				}
		}
		
		//sort vehicle reports by price
		Collections.sort(inventory);
		
		//print all vehicle reports in inventory
		System.out.println("\n\n\n*****************************************************************");
		System.out.println("\t\t     VENDING MACHINE INVENTORY");
		System.out.println("\t\t     (From Low to High Price)");
		System.out.println("*****************************************************************");
		System.out.println("Column\tSlot\tYear\tManufacturer\t Model\t\tPrice");
		System.out.println("-----------------------------------------------------------------");
		
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).print());
		}		
		
	}//print inventory method
	
}//Carvana


interface Printable {

	String print();
	
}//Printable interface


class VendingMachine {
	
	private int numberSlots;
	private int numberColumns;
	private Car[][] carTower;
	
	//getters for numSlots and numColumns
	public int getNumberSlots() {
		return numberSlots;
	}
	
	public int getNumberColumns() {
		return numberColumns;
	}

	//vending machine constructor
	public VendingMachine (int numberSlots, int numberColumns) {
		this.numberSlots = numberSlots;
		this.numberColumns = numberColumns;
		carTower = new Car [numberSlots][numberColumns];
	}
	
	//vending machine methods
	public void addCarToTower (int slotNumber, int columnNumber, Car car) {
		carTower[slotNumber][columnNumber] = car;
	}
	
	public void displayCars() {
		System.out.println("\t\tColumn 1\tColumn 2\tColumn 3\tColumn 4\n");
		for (int i = 1; i < numberSlots; i++) {
			System.out.print("Slot " + i + "\t\t");
			for (int j = 1; j < numberColumns; j++) {
				
				if (carTower[i][j] == null) {
					System.out.print("----");
				}
				else {
				System.out.print(carTower[i][j].getManufacturer());
				}
				System.out.print("\t\t");
			}
		System.out.println(" ");
		}
	}
	
	public Car getCar(int slotNumber, int columnNumber) {
		return carTower[slotNumber][columnNumber];
	}

}//VendingMachine


class Car {
	
	private double price;
	private int year;
	private String manufacturer;
	private String model;
	
	//car constructor
	public Car (double price, int year, String manufacturer, String model) {
		this.price = price;
		this.year = year;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	//getters for all the variables
	public double getPrice () {
		return price;
	}
	
	public int getYear () {
		return year;
	}
	
	public String getManufacturer () {
		return manufacturer;
	}
	
	public String getModel () {
		return model;
	}
}//Car


class VehicleReport implements Printable, Comparable<VehicleReport> {
	
	private int slotNumber;
	private int columnNumber;
	private double price;
	private int year;
	private String manufacturer;
	private String model;
	
	//VehicleReport constructor
	public VehicleReport (int slotNumber, int columnNumber, double price, int year, String manufacturer, String model) {
		this.slotNumber = slotNumber;
		this.columnNumber = columnNumber;
		this.price = price;
		this.year = year;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	//overriding Comparable
	@Override
	public int compareTo(VehicleReport otherReport) {
		
		if (this.price > otherReport.price) {
			return 1;
		}
		else if (this.price < otherReport.price) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	//implementing Printable print()
	@Override
	public String print() {
		return String.format("%d\t%d\t%-4d\t%-10s\t%-15s\t%-1.2f", 
				+  columnNumber, slotNumber, year, manufacturer, model, price);
	}
	
}//VehicleReport