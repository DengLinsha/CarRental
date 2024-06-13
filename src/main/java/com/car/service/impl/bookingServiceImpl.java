package com.car.service.impl;

import com.car.entity.Booking;
import com.car.entity.Result;
import com.car.mapper.bookingMapper;
import com.car.service.bookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class bookingServiceImpl implements bookingService {

    @Autowired
    private bookingMapper bookingMapper;
    @Override
    public List<Booking> getAll() {
        return bookingMapper.getAll();
    }

    @Override
    public Result getAllByUserId(int userId) {
        List<Booking> allByUserId = bookingMapper.getAllByUserId(userId);
        if(allByUserId == null || allByUserId.isEmpty()){
            return Result.error("暂无预约车辆信息、预约单可能已经移到订单中了，快去支付提车吧");
        }
        return Result.success(allByUserId);
    }

    @Override
    public Result CreateBooking(Booking booking) {
        booking.setBookingDate(new Date());
        int count = bookingMapper.AddBookingCar(booking);
        if(count<=0)return Result.error("创建预订单失败、请联系管理员");
        return Result.success("创建预订单成功，待该车主使用完后，便可使用啦");
    }

    @Override
    public Result deleteCar(int bookingId) {
        int count = bookingMapper.deleteCarByBookingId(bookingId);
        if(count<=0)return Result.error("移除预订单失败、请联系管理员");
        return Result.success("移出预订单成功");
    }
}
