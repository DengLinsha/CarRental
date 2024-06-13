package com.car;

import com.car.service.bookingService;
import com.car.service.carService;
import com.car.service.orderService;
import com.car.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Car {

    @Autowired
    private carService carService;
    @Autowired
    private bookingService bookingService;
    @Autowired
    private orderService orderService;
    @Autowired
    private userService userService;

    @Test
    void CarTest() {
        for (com.car.entity.Car car : carService.getAll()) {
            System.out.println(car);
        }

    }
}