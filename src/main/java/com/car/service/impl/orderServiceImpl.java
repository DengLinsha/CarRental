package com.car.service.impl;

import com.car.dto.Condition;
import com.car.dto.OrderCar;
import com.car.entity.*;
import com.car.mapper.bookingMapper;
import com.car.mapper.carMapper;
import com.car.mapper.orderMapper;
import com.car.mapper.userMapper;
import com.car.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class orderServiceImpl implements orderService {

    @Autowired
    private orderMapper orderMapper;
    @Autowired
    private userMapper userMapper;
    @Autowired
    private carMapper carMapper;
    @Autowired
    private bookingMapper bookingMapper;

//    根据orders 获取
    public List<OrderCar> getOrderCar(List<Order> orders){
        List<OrderCar> orderCars = new ArrayList<>();
        for (Order order : orders) {
//            设置车辆信息
            OrderCar orderCar = carMapper.getOrderCarMsgByCarId(order.getCarId());
//            设置用户信息
            User user = userMapper.getUserMsgByUserId(order.getUserId());
            orderCar.setNickname(user.getNickname());
            orderCar.setDailyday(order.getDailyday());
            orderCar.setPayState(order.getPayState());
//            设置订单信息
            orderCar.setBackDate(order.getBackDate());
            orderCar.setBorrowDate(order.getBorrowDate());
            orderCar.setCost(order.getCost());
            orderCar.setCostAll(order.getCost());
//            添加订单车辆信息
            orderCars.add(orderCar);
        }
        return orderCars;
    }

    @Override
    public Result getAll() {
        List<Order> orders = orderMapper.getAll();
        if(orders!=null && orders.isEmpty()){
            return Result.success(orders);
        }
        return Result.error("暂无订单");

    }


//    获取个人订单信息
    @Override
    public Result getOrderByUsername(String username) {
        List<Order> orders = orderMapper.getOrderByUsername(username);
//        获取所有orderCar信息
        List<OrderCar> orderCars = getOrderCar(orders);
//        List<OrderCar> orderCars = new ArrayList<>();
//        for (Order order : orderMapper.getOrderByUsername(username)) {
////            设置车辆信息
//            OrderCar orderCar = carMapper.getOrderCarMsgByCarId(order.getCarId());
////            设置用户信息
//            User user = userMapper.getUserMsgByUserId(order.getUserId());
//            orderCar.setNickname(user.getNickname());
//            orderCar.setDailyday(order.getDailyday());
//            orderCar.setPayState(order.getPayState());
////            设置订单信息
//            orderCar.setBackDate(order.getBackDate());
//            orderCar.setBorrowDate(order.getBorrowDate());
//            orderCar.setCost(order.getCost());
//            orderCar.setCostAll(order.getCost());
////            添加订单车辆信息
//            orderCars.add(orderCar);
//        }
        if(orderCars == null || orderCars.isEmpty()){
            return Result.error("暂无订单信息");
        }
        return Result.success(orderCars);
    }


//    创建订单
    @Transactional
    @Override
    public Result createOrder(Order order) {
//        设置创建日期
        order.setBorrowDate(new Date());
        order.setPayState(1);
//        查询用户余额、查询车辆状态信息
        User user = userMapper.getUserMsgByUserId(order.getUserId());
        if(user!=null && user.getMoney()<order.getCost()){
            return Result.error("账户余额不足，请及时充值");
        }
        Car car = carMapper.getCarMsgByCarId(order.getCarId());
        if (car!=null && car.getCarState() !=0){
            return Result.error("车辆正在被使用");
        }
//          开始创建订单、修改用户信息、修改车辆信息
        int count = orderMapper.createOrder(order);
        if(count == 0){
            return Result.error("创建订单失败、请联系管理员");
        }
//        修改车辆使用信息
        int car_count = carMapper.updateUseState(order.getCarId(),1);
        if(car_count == 0){
            return Result.error("创建订单失败、请联系管理员");
        }
//        修改用户信息
        User temp = new User();
        temp.setUserId(user.getUserId());
        temp.setMoney(user.getMoney() - order.getCost());
        temp.setUsingCar(order.getCarId());
        temp.setUseState(1);

        int count1 = userMapper.UpdateMsg(temp);
        if (count1 == 0)
            return Result.error("创建订单失败、请联系管理员");
        return Result.success("订购成功，请及时取车");
    }

    @Transactional
    @Override
    public Result backCar(Order order) {
//        orderID，carID，userID
//        还车
        order.setBackDate(new Date());
        int count = orderMapper.BackCar(order);
        if(count<=0){
            return Result.error("归还失败，请联系管理员");
        }
//        修改车辆状态
        int car_state = carMapper.updateUseState(order.getCarId(), 0);
        if(car_state<=0){
            return Result.error("归还失败，请联系管理员");
        }
//        修改用户状态
        int user_state = userMapper.updateUseState(order.getUserId(),0,-1);
        if(user_state<=0){
            return Result.error("归还失败，请联系管理员");
        }
//        将预定表中的数据转移到订单中
        List<Booking> bookings = bookingMapper.getAllByUserId(order.getUserId());
        bookingMapper.deleteAllByUserId(order.getUserId());
        for (Booking booking:bookings) {
            Order temp = new Order();
            temp.setCarId(booking.getCarId());
            temp.setUserId(booking.getUserId());
            temp.setCost(booking.getCost());
            temp.setPayState(0);
            temp.setDailyday(booking.getDailyday());
            orderMapper.addOrder(temp);
        }
        return Result.success("归还成功、期待下次光临");
    }

    @Override
    public Result getAllByUserId(int userId) {
        int count = orderMapper.getOrderByuserId(userId);
        if(count<=0){
            return Result.error("暂无订单");
        }
        return Result.success("获取成功");
    }

    @Override
    public Result addOrder(Order order) {
        order.setPayState(0);
        int count = orderMapper.addOrder(order);
        if(count<=0)return Result.error("加入购物车失败、请联系管理员");
        return Result.success("加入购物车成功、请及时支付哟");
    }

    @Transactional
    @Override
    public Result PayOrder(Order order) {
        //        设置创建日期
        order.setBorrowDate(new Date());
        order.setPayState(1);
//        查询用户余额、查询车辆状态信息
        User user = userMapper.getUserMsgByUserId(order.getUserId());
        if(user!=null && user.getMoney()<order.getCost()){
            return Result.error("账户余额不足，请及时充值");
        }
        Car car = carMapper.getCarMsgByCarId(order.getCarId());
        if (car!=null && car.getCarState() !=0){
            return Result.error("车辆正在被使用");
        }
//          开始创建订单、修改用户信息、修改车辆信息
        int count = orderMapper.ModifyOrder(order);
        if(count == 0){
            return Result.error("创建订单失败、请联系管理员");
        }
//        修改车辆使用信息
        int car_count = carMapper.updateUseState(order.getCarId(),1);
        if(car_count == 0){
            return Result.error("创建订单失败、请联系管理员");
        }
//        修改用户信息
        User temp = new User();
        temp.setUserId(user.getUserId());
        temp.setMoney(user.getMoney() - order.getCost());
        temp.setUsingCar(order.getCarId());
        temp.setUseState(1);

        int count1 = userMapper.UpdateMsg(temp);
        if (count1 == 0)
            return Result.error("创建订单失败、请联系管理员");
        return Result.success("订购成功，请及时取车");
    }

    @Override
    public Result removeOrder(int orderId) {
        int count = orderMapper.removerOrder(orderId);
        if(count<=0)return Result.error("移除订单失败、请联系管理员");
        return Result.success("移出订单成功");
    }

    @Override
    public Result getOrderByCondition(int userId, String carIdentity, String carName) {
        // 查询符合条件的车辆 carId
        List<Car> msg = carMapper.findCarMsgByCondition(carIdentity,carName);
//        查询订单
        List<Order> orders = new ArrayList<>();
        for (Car car:msg) {
            List<Order> order1 = orderMapper.findOrdersByCondition(car.getCarId(),userId);
//            查询非空、加入到订单中
            if(order1!=null && !order1.isEmpty()){
//                批量加入
                orders.addAll(order1);
            }
        }
        if(orders.isEmpty()){
            return Result.success("未找到任何订单信息");
        }

//        联立得到 ordercar列表
        List<OrderCar> orderCars = getOrderCar(orders);
        if(orderCars == null || orderCars.isEmpty()){
            return Result.error("未找到任何信息");
        }
        return Result.success(orderCars);
    }

    @Override
    public Result getBackOrderByUserId(int userId) {
//        获取
        List<Order> orders = orderMapper.getOrderByuserIdAndPayState(userId,1);
//        删除正在使用的车辆的订单信息，从后往前删除
        User user = userMapper.getUserMsgByUserId(userId);
        if(user.getUseState() == 1)
            for(int i=orders.size()-1;i>=0;i--){
                if(user.getUsingCar() == orders.get(i).getCarId() && orders.get(i).getPayState() == 1){
                    orders.remove(i);
                    break;
                }
            }

        List<OrderCar> orderCars = getOrderCar(orders);
        if(orderCars == null || orderCars.isEmpty()){
            return Result.error("未找到任何信息");
        }
        return Result.success(orderCars);
    }
}
