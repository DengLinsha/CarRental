package com.car.controller;
import com.car.dto.OrderCar;
import com.car.entity.Order;
import com.car.entity.Result;
import com.car.service.orderService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "订单接口")
@CrossOrigin
@RestController
@RequestMapping("/order")
public class orderController {

    @Autowired
    private orderService orderService;

    @Hidden
    @GetMapping(value = "/getAll")
    public Result getOrders(){
        return orderService.getAll();
    }
    @Operation(summary = "获取个人历史订单")
    @GetMapping(value = "/getAllByUserId")
    public Result getOrdersByUserId(@RequestParam("userId")int userId){
        return orderService.getAllByUserId(userId);
    }

    @Operation(summary = "获取个人历史订单")
    @GetMapping("/getOrderByUsername")
    public Result getOrderByUsername(@RequestParam("username")String username){
        return orderService.getOrderByUsername(username);
    }


    @Operation(summary = "立即使用、支付界面")
    @PostMapping("/creatOrder")
    public Result creatOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @Operation(summary = "加入到购物车")
    @PostMapping("/addtOrder")
    public Result AddOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @Operation(summary = "支付订单")
    @PostMapping("/payOrder")
    public Result PayOrder(@RequestBody Order order){
        return orderService.PayOrder(order);
    }


    @Operation(summary = "还车")
    @PostMapping("/backCar")
    public Result backCar(@RequestBody Order order){
        return orderService.backCar(order);
    }

    @Operation(summary = "移出到购物车")
    @GetMapping("/removeOrder")
    public Result removeOrder(@Param("orderId")int orderId){
        return orderService.removeOrder(orderId);
    }


    @Operation(summary = "按条件查询订单")
    @GetMapping("/getOrderByCondition")
    public Result getOrderByCondition(
                                @Param("userId")int userId,
                                @Param("carIdentity")String carIdentity,
                                @Param("carName")String carName){
        return orderService.getOrderByCondition(userId,carIdentity,carName);
    }


    @Operation(summary = "还车订单接口")
    @GetMapping("/getBackOrderByUserId")
    public Result getBackOrderByUserId(
            @Param("userId")int userId){
        return orderService.getBackOrderByUserId(userId);
    }

}
