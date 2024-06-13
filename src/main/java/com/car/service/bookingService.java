package com.car.service;

import com.car.entity.Booking;
import com.car.entity.Result;

import java.util.List;

public interface bookingService {
    List<Booking> getAll();

    Result getAllByUserId(int userId);

    Result CreateBooking(Booking booking);

    Result deleteCar(int bookingId);
}
