package com.car.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//业务状态码
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(data)
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    public static <T> Result<T> success(T data,String message) {
        return new Result<>(200, message, data);
    }
    public static Result success() {
        return new Result(200, "Success", null);
    }
    public static Result error(String message) {
        return new Result(400, message, null);
    }
}
