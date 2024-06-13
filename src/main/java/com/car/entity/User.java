package com.car.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description= "用户信息")
public class User implements Serializable {
    @Schema(description = "主键ID")
    private int userId;
    @Schema(description = "网名")
    private String nickname;
    @Schema(description = "性别")
    private String sex;
    @Schema(description = "账号")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "头像")
    private String avator;
    @Schema(description = "是否正在使用车辆的状态")
    private int useState;
    @Schema(description = "余额")
    private double money;
    @Schema(description = "正在使用的车辆id")
    private int usingCar;
    @Schema(description = "地址")
    private String userAddress;
    @Schema(description = "电话")
    private String telphone;
    @Schema(description = "邮箱")
    private String email;


}
