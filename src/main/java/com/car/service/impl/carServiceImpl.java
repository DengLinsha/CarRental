package com.car.service.impl;

import com.car.dto.Condition;
import com.car.entity.Car;
import com.car.entity.Result;
import com.car.mapper.carMapper;
import com.car.service.carService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carServiceImpl implements carService {

    @Autowired
    private carMapper carMapper;
    @Override
    public List<Car> getAll() {
        return carMapper.getAll();
    }

    @Override
    public List<Car> getCarMsgByIdentity(String identity) {
        return carMapper.getCarMsgByIdentity(identity);
    }

    @Override
    public List<Car> getCarMsgBycarName(String carName) {
        return carMapper.getCarMsgByBranddName(carName);
    }

    @Override
    public List<Car> getCarMsgByGears(String gears) {
        return carMapper.getCarMsgByGears(gears);
    }

    @Override
    public List<Car> getAllByMoney(int flag) {
//        升序，从小到达
        if (flag == 0){
            return carMapper.getAllByMoneyASC();
        }
//        降序，从大到小
        return carMapper.getAllByMoneyDESC();
    }

    @Override
    public List<Car> getAllBySeats(int flag) {
        //        升序，从小到达
        if (flag == 0){
            return carMapper.getAllBySeatsASC();
        }
//        降序，从大到小
        return carMapper.getAllBySeatsDESC();
    }

    @Override
    public List<Car> getCarMsgByCarSeat(int carSeat) {
        return carMapper.getCarMsgBySeat(carSeat);
    }

    @Override
    public List<Car> getCarMsgByPrice(Double startPrice, Double endPrice) {
        // 执行查询
        return carMapper.getCarMsgByPrice(startPrice, endPrice);
    }

    @Override
    public List<Car> getCarMsgByAddress(String address) {
        return carMapper.getCarMsgByAddress(address);
    }

    @Override
    public List<Car> getCarMsgByCarState(int carState) {
        return carMapper.getCarMsgBycarState(carState);
    }

    @Override
    public List<Car> getCarMsgByBookingState(int bookinsState) {
        return carMapper.getCarMsgByBookgingState(bookinsState);
    }

    @Override
    public Result getCarMsgByContitions(Condition condition) {
        List<Car> cars = carMapper.findCarMsgByConditions(condition);
        if (cars == null || cars.isEmpty())
            return Result.error("未找到符合条件的车辆");
        return Result.success(cars);
    }

    public String findCarMsgByConditions(Condition condition) {
        return new SQL() {{
            SELECT("*");
            FROM("car");

            if (condition.getCarName() != null && !condition.getCarName().isEmpty()) {
                WHERE("car_name like concat('%',#{carName},'%')");
            }
//            if (condition.getCarState() != 0) {
                WHERE("car_state = #{carState}");
//            }
            if (condition.getCarIdentity() != null && !condition.getCarIdentity().isEmpty()) {
                WHERE("car_identity like concat('%',#{carIdentity},'%')");
            }
            if (condition.getStartPrice() != null) {
                WHERE("price >= #{startPrice}");
            }
            if (condition.getEndPrice()!=null && condition.getEndPrice() != 0.0) {
                WHERE("price <= #{endPrice}");
            }
            if (condition.getAddress() != null && !condition.getAddress().isEmpty()) {
                WHERE("address like concat(#{address},'%')");
            }
            if (condition.getCarSeat() != 0) {
                WHERE("car_seat = #{carSeat}");
            }
            if (condition.getGears() != null && !condition.getGears().isEmpty()) {
                WHERE("gears = #{gears}");
            }
        }}.toString();
    }

}
