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
public class Order {
    @Schema(description = "订单ID")
    private int orderId;
    @Schema(description = "用户ID")
    private int userId;
    @Schema(description = "车辆ID")
    private int carId;
    @Schema(description = "租用日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowDate;
    @Schema(description = "归还日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backDate;
    @Schema(description = "花费金额")
    private double cost;
    @Schema(description = "租用天数")
    private int dailyday;
    @Schema(description = "支付状态")
    private int payState;
}
