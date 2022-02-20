package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CarsDao;
import entity.Cars;

public class Menu {
	
	private CarsDao carsDao = new CarsDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Show all cars",
			"Add a car",
			"Update car price",
			"Delete a car");
	
	public void start() {
		String selection = "";
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayCars();
				} else if (selection.contentEquals("2")) {
					createCar();
				} else if (selection.contentEquals("3")) {
					updatePrice();
				} else if (selection.contentEquals("4")) {
					deleteCar();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue or -1 to stop");
			scanner.nextLine();
			
		} while (!selection.contentEquals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Enter your selection: \n----------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println((i + 1) + ". " + options.get(i));
		}
	}
	
	private void displayCars() throws SQLException {
		List<Cars> allCars = carsDao.getAllCars();
		for (Cars car : allCars) {
			System.out.println(car.getId() + ": " + car.getName());
		}
	}
	
	private void createCar() throws SQLException {
		System.out.print("What is the id of the car? ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("What is the name of the car?");
		String name = scanner.nextLine();
		System.out.print("What is the price of the car?");
		Double price = Double.parseDouble(scanner.nextLine());
		carsDao.createNewCar(id, name, price);
	}
	
	private void updatePrice() throws SQLException {
		System.out.print("Enter the car name: ");
		String name = scanner.nextLine();
		System.out.print("Enter the updated price: ");
		Double price = Double.parseDouble(scanner.nextLine());
		carsDao.updatePrice(name, price);
	}
	
	private void deleteCar() throws SQLException {
		System.out.print("Enter the car id: ");
		int id = Integer.parseInt(scanner.nextLine());
		carsDao.deleteCar(id);
	}
			

}
