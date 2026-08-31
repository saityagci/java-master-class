package com.sait.booking;

import com.sait.car.Car;
import com.sait.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CarBooking {
    private final UUID id;
    private final User user;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal price;
    private final BookingStatus status;
    private final LocalDateTime bookedAt;

    public CarBooking(UUID id, User user, Car car, LocalDate startDate, LocalDate endDate, BigDecimal price, BookingStatus status, LocalDateTime bookedAt) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = status;
        this.bookedAt = bookedAt;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", status=" + status +
                ", bookedAt=" + bookedAt +
                '}';
    }
}
