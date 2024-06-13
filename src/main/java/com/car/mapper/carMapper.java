package com.car.mapper;

import com.car.dto.Condition;
import com.car.dto.OrderCar;
import com.car.entity.Car;
import com.car.service.impl.carServiceImpl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface carMapper {

    @Select("select * from car")
    List<Car> getAll();

    @Select("select * from car where car_identity like CONCAT('%',#{identity},'%')")
    List<Car> getCarMsgByIdentity(String identity);

    @Select("select * from car where car_name like CONCAT('%',#{carName},'%')")
    List<Car> getCarMsgByBranddName(String carName);

    @Select("select * from car where gears = #{gears}")
    List<Car> getCarMsgByGears(String gears);

    @Select("select * from car where address  like CONCAT(#{address},'%')")
    List<Car> getCarMsgByAddress(String address);

    @Select("select * from car where car_state = #{carState}")
    List<Car> getCarMsgBycarState(int carState);


    @Select("select * from car where booking_state = #{bookingState}")
    List<Car> getCarMsgByBookgingState(int bookingState);

    @Select("SELECT * from car order by price desc ")
    List<Car> getAllByMoneyDESC();

    @Select("SELECT * from car order by price asc")
    List<Car> getAllByMoneyASC();

    @Select("SELECT * from car order by car_seat asc")
    List<Car> getAllBySeatsASC();

    @Select("SELECT * from car order by price desc ")
    List<Car> getAllBySeatsDESC();

    @Select("select * from car where car_seat = #{carSeat}")
    List<Car> getCarMsgBySeat(int carSeat);

    @Select("select * from car where price between #{startPrice} and #{endPrice} ORDER BY price DESC")
    List<Car> getCarMsgByPrice(@Param("startPrice") double startPrice,
                               @Param("endPrice") double endPrice);

    @Select("select * from car where car_id = #{carId}")
    Car getCarMsgByCarId(@Param("carId") int carId);

    @Update("update car set car_state = #{carState} where car_id =  #{carId};")
    int updateUseState(@Param("carId") int carId, @Param("carState") int carState);

    @Select("select * from car where car_id = #{carId}")
    OrderCar getOrderCarMsgByCarId(@Param("carId") int carId);


    @SelectProvider(type = carServiceImpl.class, method = "findCarMsgByConditions")
    List<Car> findCarMsgByConditions(Condition condition);


    @Select("""
SELECT *
FROM car
WHERE COALESCE(car_identity, '') LIKE concat('%',#{carIdentity},'%')
  AND COALESCE(car_name, '') LIKE concat('%',#{carName},'%');
""")
    List<Car> findCarMsgByCondition(@Param("carIdentity") String carIdentity, @Param("carName") String carName);
}
