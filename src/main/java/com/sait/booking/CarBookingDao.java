package com.sait.booking;

import java.util.Arrays;
import java.util.UUID;

public class CarBookingDao {
    private CarBooking [] bookings = new CarBooking[0];

    public CarBooking [] getBookings (){
        return bookings;
    }

    public CarBooking findBookingById (UUID bookingId)   {
        for (CarBooking booking : bookings ){
            if (booking.getId().equals(bookingId)){
                return booking;
            }
        }
        return null;
    }

    public void saveBooking (CarBooking booking){
        bookings = Arrays.copyOf(bookings,bookings.length +1);
        bookings [bookings.length -1] =booking;
    }
    public void deleteBooking(UUID bookingId) {
        int indexToRemove = -1;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getId().equals(bookingId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Booking not found: " + bookingId);
            return;
        }

        CarBooking[] updated = new CarBooking[bookings.length - 1];
        int j = 0;
        for (int i = 0; i < bookings.length; i++) {
            if (i != indexToRemove) {
                updated[j] = bookings[i];
                j++;
            }
        }
        bookings = updated;
    }

}



