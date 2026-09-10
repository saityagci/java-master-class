package com.sait.car;


import java.util.UUID;

public class CarService {

    private final CarDao carDao = new CarDao();

    public Car[] getAllCars() {
        return carDao.getCars();
    }

    public Car findCarById(UUID carId) {
        return carDao.findCarById(carId);
    }
}
