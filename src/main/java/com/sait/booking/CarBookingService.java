package com.sait.booking;

import com.sait.car.Car;
import com.sait.car.CarService;
import com.sait.user.User;
import com.sait.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDao carBookingDao = new CarBookingDao();
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("User not found: " + userId);
            return null;
        }

        Car car = carService.findCarById(carId);
        if (car == null) {
            System.out.println("Car not found: " + carId);
            return null;
        }

        if (startDate.isBefore(LocalDate.now())) {
            System.out.println("Start date cannot be in the past");
            return null;
        }
        if (!endDate.isAfter(startDate)) {
            System.out.println("End date must be after start date");
            return null;
        }

        for (CarBooking existing : carBookingDao.getBookings()) {
            if (existing.getStatus() == BookingStatus.ACTIVE && existing.getCar().getId().equals(carId)) {
                System.out.println("Car is not available: " + carId);
                return null;
            }
        }

        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal price = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(numberOfDays));

        CarBooking booking = new CarBooking(
                UUID.randomUUID(), user, car, startDate, endDate, price,
                BookingStatus.ACTIVE, LocalDateTime.now()
        );

        carBookingDao.saveBooking(booking);
        return booking;
    }

    public void deleteBooking(UUID bookingId) {
        carBookingDao.deleteBooking(bookingId);
    }

    public CarBooking[] getBookings() {
        return carBookingDao.getBookings();
    }

    public CarBooking[] getBookingsByUserId(UUID userId) {
        CarBooking[] all = carBookingDao.getBookings();
        int count = 0;
        for (CarBooking booking : all) {
            if (booking.getUser().getId().equals(userId)) {
                count++;
            }
        }

        CarBooking[] result = new CarBooking[count];
        int index = 0;
        for (CarBooking booking : all) {
            if (booking.getUser().getId().equals(userId)) {
                result[index] = booking;
                index++;
            }
        }
        return result;
    }

    public Car[] getAvailableCars() {
        Car[] allCars = carService.getAllCars();
        CarBooking[] allBookings = carBookingDao.getBookings();

        int count = 0;
        for (Car car : allCars) {
            if (isAvailable(car, allBookings)) {
                count++;
            }
        }

        Car[] result = new Car[count];
        int index = 0;
        for (Car car : allCars) {
            if (isAvailable(car, allBookings)) {
                result[index] = car;
                index++;
            }
        }
        return result;
    }

    public Car[] getAvailableElectricCars() {
        Car[] availableCars = getAvailableCars();
        int count = 0;
        for (Car car : availableCars) {
            if (car.isElectric()) {
                count++;
            }
        }

        Car[] result = new Car[count];
        int index = 0;
        for (Car car : availableCars) {
            if (car.isElectric()) {
                result[index] = car;
                index++;
            }
        }
        return result;
    }

    private boolean isAvailable(Car car, CarBooking[] bookings) {
        for (CarBooking booking : bookings) {
            if (booking.getStatus() == BookingStatus.ACTIVE && booking.getCar().getId().equals(car.getId())) {
                return false;
            }
        }
        return true;
    }
}
