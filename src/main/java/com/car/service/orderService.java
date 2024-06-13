package com.car.service;

import com.car.entity.Order;
import com.car.entity.Result;

public interface orderService {
    Result getAll();

    Result getOrderByUsername(String username);

    Result createOrder(Order order);

    Result backCar(Order order);

    Result getAllByUserId(int userId);

    Result addOrder(Order order);

    Result PayOrder(Order order);

    Result removeOrder(int orderId);

    Result getOrderByCondition(int userId, String carIdentity, String carName);

    Result getBackOrderByUserId(int userId);
}
