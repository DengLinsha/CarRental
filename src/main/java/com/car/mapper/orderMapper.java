package com.car.mapper;

import com.car.entity.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface orderMapper {

    @Select("select * from `order`")
    List<Order> getAll();

    @Select("SELECT * FROM `order` WHERE `order`.user_id = (SELECT user_id FROM user WHERE username = #{username})")
    List<Order> getOrderByUsername(String username);

    @Insert("""  
    INSERT INTO `order` (user_id, car_id, borrow_date,cost,dailyday,pay_state)  
    VALUES (#{userId}, #{carId}, #{borrowDate}, #{cost},#{dailyday},#{payState});  
""")
    int createOrder(Order order);

    @Update("update `order` set back_date = #{backDate} where order_id = #{orderId};")
    int BackCar(Order order);

    @Select("SELECT * FROM `order` WHERE user_id = #{userId}")
    int getOrderByuserId(@Param("userId") int userId);

    @Insert("""  
    INSERT INTO `order` (user_id, car_id,cost,dailyday,pay_state)  
    VALUES (#{userId}, #{carId},#{cost},#{dailyday},#{payState});  
""")
    int addOrder(Order order);

    @Update("update `order` set borrow_date = #{borrowDate},pay_state = #{payState} where order_id = #{orderId};")
    int ModifyOrder(Order order);

    @Delete("delete from `order` where order_id = #{orderId}")
    int removerOrder(@Param("orderId") int orderId);

    @Select("select * from `order` where car_id = #{carId} and user_id = #{userId}")
    List<Order> findOrdersByCondition(@Param("carId") int carId, @Param("userId") int userId);

    @Select("select * from `order` where pay_state = #{payState} and user_id = #{userId}")
    List<Order> getOrderByuserIdAndPayState(@Param("userId") int userId, @Param("payState") int payState);
}
