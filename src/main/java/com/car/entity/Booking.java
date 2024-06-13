package com.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Schema(description = "预定ID")
    private int bookingId;
    @Schema(description = "用户ID")
    private int userId;
    @Schema(description = "车辆ID")
    private int carId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "预定时间")
    private Date bookingDate;
    @Schema(description = "预定天数")
    private int dailyday;
    @Schema(description = "花费金额")
    private double cost;
}
