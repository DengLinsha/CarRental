package com.car.mapper;

import com.car.entity.Booking;
import com.car.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface bookingMapper {


    @Select("select * from booking")
    List<Booking> getAll();

    @Select("select * from booking where user_id = #{userId}")
    List<Booking> getAllByUserId(@Param("userId") int userId);

    @Delete("delete from booking where user_id = #{userId}")
    void deleteAllByUserId(@Param("userId") int userId);

    @Insert("""
        insert into booking (user_id,car_id,booking_date,dailyday,cost) 
        values (#{userId},#{carId},#{bookingDate},#{dailyday},#{cost})
    """)
    int AddBookingCar(Booking booking);

    @Delete("delete from booking where booking_id = #{bookingId}")
    int deleteCarByBookingId(@Param("bookingId") int bookingId);
}
