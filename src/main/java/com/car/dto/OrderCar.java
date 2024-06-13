package com.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCar {
//  用户
    @Schema(description = "网名")
    private String nickname;
//  订单信息
    @Schema(description = "租用日期")
    private Date borrowDate;
    @Schema(description = "租用天数")
    private int dailyday;
    @Schema(description = "归还日期")
    private Date backDate;
    @Schema(description = "花费金额")
    private double cost;
//  订单信息
    @Schema(description = "车辆ID")
    private int carId;
    @Schema(description = "车辆品牌")
    private String carName;
    @Schema(description = "车辆使用状态")
    private int carState;
    @Schema(description = "车牌号")
    private String carIdentity;
    @Schema(description = "车辆图片")
    private String picUrl;
    @Schema(description = "车辆日租金")
    private double price;
    @Schema(description = "取车地点")
    private String address;
    @Schema(description = "车座")
    private int carSeat;
    @Schema(description = "车挡位（只有手动挡 和 自动挡  《挡》）")
    private String gears;
    @Schema(description = "预定状态")
    private int bookingState;
    @Schema(description = "评价")
    private String comments;
    @Schema(description = "总花费")
    private double costAll;
    @Schema(description = "存储量")
    private String storage;
    @Schema(description = "支付状态")
    private int payState;
}
