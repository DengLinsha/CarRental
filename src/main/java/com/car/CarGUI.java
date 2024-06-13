package com.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@MapperScan("com.car.mapper")
public class CarGUI {

    public static void main(String[] args) {
        SpringApplication.run(CarGUI.class, args);
    }

}
