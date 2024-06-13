package com.car;

import com.car.entity.Car;
import com.car.service.bookingService;
import com.car.service.carService;
import com.car.service.orderService;
import com.car.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class User {

    @Autowired
    private carService carService;
    @Autowired
    private bookingService bookingService;
    @Autowired
    private orderService orderService;
    @Autowired
    private userService userService;

    @Test
    void UserTest() {
        String proxiamo = userService.findPassWordByUsername("asd");
        System.out.println(proxiamo);
    }


}
