package com.sait;

import com.sait.booking.CarBooking;
import com.sait.booking.CarBookingService;
import com.sait.car.Car;
import com.sait.car.CarService;
import com.sait.user.User;
import com.sait.user.UserService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarBookingService carBookingService = new CarBookingService();
        UserService userService = new UserService();
        CarService carService = new CarService();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    bookCar(scanner, carBookingService, userService, carService);
                    break;
                case 2:
                    deleteBooking(scanner, carBookingService);
                    break;
                case 3:
                    viewUserBookings(scanner, carBookingService);
                    break;
                case 4:
                    viewAllBookings(carBookingService);
                    break;
                case 5:
                    viewAvailableCars(carBookingService);
                    break;
                case 6:
                    viewAvailableElectricCars(carBookingService);
                    break;
                case 7:
                    viewAllUsers(userService);
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1 - Book Car");
        System.out.println("2 - Delete Booking");
        System.out.println("3 - View All User Booked Cars");
        System.out.println("4 - View All Bookings");
        System.out.println("5 - View Available Cars");
        System.out.println("6 - View Available Electric Cars");
        System.out.println("7 - View All Users");
        System.out.println("8 - Exit");
        System.out.print("Select an option: ");
    }

    private static void bookCar(Scanner scanner, CarBookingService carBookingService,
                                 UserService userService, CarService carService) {
        System.out.println();
        System.out.println("Users:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        System.out.print("Enter user ID: ");
        UUID userId = readUuid(scanner);
        if (userId == null) return;

        System.out.println();
        System.out.println("Cars:");
        for (Car car : carService.getAllCars()) {
            System.out.println(car);
        }

        System.out.print("Enter car ID: ");
        UUID carId = readUuid(scanner);
        if (carId == null) return;

        System.out.print("Enter start date (yyyy-MM-dd): ");
        LocalDate startDate = readDate(scanner);
        if (startDate == null) return;

        System.out.print("Enter end date (yyyy-MM-dd): ");
        LocalDate endDate = readDate(scanner);
        if (endDate == null) return;

        CarBooking booking = carBookingService.bookCar(userId, carId, startDate, endDate);
        if (booking != null) {
            System.out.println("Booking created: " + booking);
        }
    }

    private static void deleteBooking(Scanner scanner, CarBookingService carBookingService) {
        System.out.print("Enter booking ID to delete: ");
        UUID bookingId = readUuid(scanner);
        if (bookingId == null) return;

        carBookingService.deleteBooking(bookingId);
        System.out.println("Booking deleted (if it existed).");
    }

    private static void viewUserBookings(Scanner scanner, CarBookingService carBookingService) {
        System.out.print("Enter user ID: ");
        UUID userId = readUuid(scanner);
        if (userId == null) return;

        CarBooking[] bookings = carBookingService.getBookingsByUserId(userId);
        if (bookings.length == 0) {
            System.out.println("No bookings found for this user.");
            return;
        }
        for (CarBooking booking : bookings) {
            System.out.println(booking);
        }
    }

    private static void viewAllBookings(CarBookingService carBookingService) {
        CarBooking[] bookings = carBookingService.getBookings();
        if (bookings.length == 0) {
            System.out.println("No bookings found.");
            return;
        }
        for (CarBooking booking : bookings) {
            System.out.println(booking);
        }
    }

    private static void viewAvailableCars(CarBookingService carBookingService) {
        Car[] cars = carBookingService.getAvailableCars();
        if (cars.length == 0) {
            System.out.println("No available cars.");
            return;
        }
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static void viewAvailableElectricCars(CarBookingService carBookingService) {
        Car[] cars = carBookingService.getAvailableElectricCars();
        if (cars.length == 0) {
            System.out.println("No available electric cars.");
            return;
        }
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static void viewAllUsers(UserService userService) {
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }

    private static int readInt(Scanner scanner) {
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static UUID readUuid(Scanner scanner) {
        String line = scanner.nextLine().trim();
        try {
            return UUID.fromString(line);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID: " + line);
            return null;
        }
    }

    private static LocalDate readDate(Scanner scanner) {
        String line = scanner.nextLine().trim();
        try {
            return LocalDate.parse(line);
        } catch (Exception e) {
            System.out.println("Invalid date: " + line);
            return null;
        }
    }
}
