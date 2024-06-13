package com.car;

import com.car.service.bookingService;
import com.car.service.carService;
import com.car.service.orderService;
import com.car.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@SpringBootTest
class Order {

    @Autowired
    private carService carService;
    @Autowired
    private bookingService bookingService;
    @Autowired
    private orderService orderService;
    @Autowired
    private userService userService;


    @Test
    void OrderTest() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
    }

}
