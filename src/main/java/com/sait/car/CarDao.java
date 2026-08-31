package com.sait.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {

    private static final Car[] cars;

    static {
        cars = new Car[]{
            new Car(UUID.fromString("e22ff9d1-06b1-4e46-a870-6e803046aded"), "TE-001", new BigDecimal("29.99"), Brand.TESLA, true),
            new Car(UUID.fromString("30f645be-554b-4994-bf37-4bea61bb3765"), "AU-002", new BigDecimal("24.99"), Brand.AUDI, false),
            new Car(UUID.fromString("914d4224-b489-476f-a289-9c50adb84f06"), "ME-003", new BigDecimal("34.99"), Brand.MERCEDES, false),
            new Car(UUID.fromString("8a9a10dc-751c-4e0b-b726-eee171097f7f"), "TO-004", new BigDecimal("19.99"), Brand.TOYOTA, false),
            new Car(UUID.fromString("9e3777c8-7bdb-48f1-846d-fac4bf5d8b8a"), "TE-005", new BigDecimal("39.99"), Brand.TESLA, true)
        };
    }

    public Car[] getCars() {
        return cars;
    }

    public Car findCarById(UUID carId) {
        for (Car car : cars) {
            if (car.getId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
}
