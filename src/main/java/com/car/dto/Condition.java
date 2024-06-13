package com.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {
    @Schema(description = "车辆品牌")
    private String carName;
    @Schema(description = "车辆使用状态")
    private int carState;
    @Schema(description = "车牌号")
    private String carIdentity;
    @Schema(description = "车辆日租金（begin）")
    private Double startPrice;
    @Schema(description = "车辆日租金（end）")
    private Double endPrice;
    @Schema(description = "取车地点")
    private String address;
    @Schema(description = "车座")
    private int carSeat;
    @Schema(description = "车挡位（只有手动挡 和 自动挡  《挡》）")
    private String gears;
}
