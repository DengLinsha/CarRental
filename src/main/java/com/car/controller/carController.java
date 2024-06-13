package com.car.controller;
import com.car.dto.Condition;
import com.car.entity.Car;
import com.car.entity.Result;
import com.car.service.carService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "车辆信息接口")
@CrossOrigin
@RestController
@RequestMapping("/car")
public class carController {

    @Autowired
    private carService carService;

    @Operation(summary = "获取所有汽车信息")
    @GetMapping("/getAll")
    public Result getCarList(){
        List<Car> carList =  carService.getAll();
        return Result.success(carList);
    }


    @Operation(summary = "获取所有汽车信息（条件）")
    @PostMapping("/getCarByConditions")
    public Result getCarByConditions(@RequestBody Condition condition){
        return carService.getCarMsgByContitions(condition);
    }

    @Operation(summary = "获取所有汽车信息（按价格升降）")
    @GetMapping("/getAllOrderByPrice")
    public Result getCarListByMoney(@RequestParam("flag") int flag){
//        flag 1 降序 ； 0 升序
        List<Car> carList =  carService.getAllByMoney(flag);
        return Result.success(carList);
    }
    @Operation(summary = "获取所有汽车信息（按车座升降）")
    @GetMapping("/getAllOrderByCarSeat")
    public Result getCarListByCarSeat(@RequestParam("flag") int flag){
//        flag 1 降序 ； 0 升序
        List<Car> carList =  carService.getAllBySeats(flag);
        return Result.success(carList);
    }


    @GetMapping("/getCarByIdentity")
    public Result getCarMsgByIdentity(@RequestParam("carIdentity") String carIdentity){
        List<Car> carList =  carService.getCarMsgByIdentity(carIdentity);
        if (carList == null){
            return Result.error("无符合条件的车辆");
        }
        return Result.success(carList);
    }
    @GetMapping("/getCarByPrice")
    public Result getCarMsgByPrice(@RequestParam("startPrice") Double startPrice,
                                   @RequestParam("endPrice") Double endPrice){
        List<Car> carList =  carService.getCarMsgByPrice(startPrice,endPrice);
        if (carList == null || carList.isEmpty()){
            return Result.error("该价格区间内、无符合条件的车辆");
        }
        return Result.success(carList);
    }
    @GetMapping("/getCarByAddress")
    public Result getCarMsgByPrice(@RequestParam("address") String address){
        List<Car> carList =  carService.getCarMsgByAddress(address);
        if (carList == null || carList.isEmpty()){
            return Result.error("该价格区间内、无符合条件的车辆");
        }
        return Result.success(carList);
    }
    @GetMapping("/getCarByCarState")
    public Result getCarMsgByCarState(@RequestParam("carState") int carState){
        List<Car> carList =  carService.getCarMsgByCarState(carState);
        if (carList == null || carList.isEmpty()){
            return Result.error("该价格区间内、无符合条件的车辆");
        }
        return Result.success(carList);
    }
    @GetMapping("/getCarByBookingState")
    public Result getCarMsgByBookingState(@RequestParam("bookingState") int bookinsState){
        List<Car> carList =  carService.getCarMsgByBookingState(bookinsState);
        if (carList == null || carList.isEmpty()){
            return Result.error("该价格区间内、无符合条件的车辆");
        }
        return Result.success(carList);
    }

    @GetMapping("/getCarBySeats")
    public Result getCarMsgBySeats(@RequestParam("carSeat")
                                      int carSeat){
        List<Car> carList =  carService.getCarMsgByCarSeat(carSeat);
        if (carList == null){
            return Result.error("无符合条件的车辆");
        }
        return Result.success(carList);
    }

    @GetMapping("/getCarByBrandName")
    public Result getCarMsgByBrandName(@RequestParam("carName")
                                      String carName){
        List<Car> carList =  carService.getCarMsgBycarName(carName);
        if (carList == null){
            return Result.error("无符合条件的车辆");
        }
        return Result.success(carList);
    }

    @GetMapping("/getCarByGears")
    public Result getCarMsgByGears(@RequestParam("gears")
                                       String gears){
        List<Car> carList =  carService.getCarMsgByGears(gears);
        if (carList == null){
            return Result.error("无符合条件的车辆");
        }
        return Result.success(carList);
    }


}
