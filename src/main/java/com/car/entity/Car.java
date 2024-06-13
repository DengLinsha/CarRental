package com.car.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

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
    @Schema(description = "取值地点")
    private String address;
    @Schema(description = "车座")
    private int carSeat;
    @Schema(description = "车挡位（只有手动挡 和 自动挡  《挡》）")
    private String gears;
    @Schema(description = "预定状态")
    private int bookingState;
    @Schema(description = "评价")
    private String comments;
    @Schema(description = "存储量")
    private String storage;
}
