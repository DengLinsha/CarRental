package com.car.service;

import com.car.dto.Condition;
import com.car.entity.Car;
import com.car.entity.Result;

import java.util.List;

public interface carService {
    List<Car> getAll();

    List<Car> getCarMsgByIdentity(String identity);

    List<Car> getCarMsgBycarName(String carName);

    List<Car> getCarMsgByGears(String gears);

    List<Car> getAllByMoney(int flag);

    List<Car> getAllBySeats(int flag);

    List<Car> getCarMsgByCarSeat(int carSeat);

    List<Car> getCarMsgByPrice(Double startPrice, Double endPrice);

    List<Car> getCarMsgByAddress(String address);


    List<Car> getCarMsgByCarState(int carState);

    List<Car> getCarMsgByBookingState(int bookinsState);

    Result getCarMsgByContitions(Condition condition);
}
