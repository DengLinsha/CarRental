package com.car.controller;
import com.car.entity.Booking;
import com.car.entity.Result;
import com.car.service.bookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "预定订单接口")
@CrossOrigin
@RestController
@RequestMapping("/booking")
public class bookingController {

    @Autowired
    private bookingService bookingService;

    @Operation(summary = "获取所有预约历史订单")
    @GetMapping(value = "/getBookingList")
    public Result getBookingList(){
        List<Booking> bookingList =  bookingService.getAll();
        return Result.success(bookingList);
    }
    @Operation(summary = "获取个人预约历史订单")
    @GetMapping(value = "/getBookingListByUserId")
    public Result getBookingListByUserId(@Param("userId")int userId){
        return bookingService.getAllByUserId(userId);
    }
    @Operation(summary = "创建预约订单")
    @PostMapping(value = "/createBookingCar")
    public Result CreateBookingOrder(@RequestBody Booking booking){
        return bookingService.CreateBooking(booking);
    }
    @Operation(summary = "取消预约订单")
    @PostMapping(value = "/deleteBookingCar")
    public Result CreateBookingOrder(@Param("bookingId")int bookingId){
        return bookingService.deleteCar(bookingId);
    }
}
