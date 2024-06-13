package com.car.mapper;

import com.car.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface userMapper {
    @Select("select * from user where username = #{username}")
    User isExist(@Param("username")String username);

    @Insert("""
    insert into user (nickname,username,password,sex,avator,
                    money,user_address,telphone,email) 
    values (#{nickname},#{username},#{password},#{sex},
            #{avator},#{money},#{userAddress},#{telphone},#{email})
            """)
    int addUser(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User isExistUser(@Param("username") String username,
                     @Param("password")String password);

    @Select("select password from user where username = #{username}")
    String findPassWordByUsername(String username);

    @Update("""
    update user set nickname = #{nickname},sex=#{sex},
     password = #{password},
    avator = #{avator},
    money= #{money},user_address=  #{userAddress},telphone = #{telphone},
    email = #{email} where user_id = #{userId};""")
    void modifyUserMsg(User user);

    @Delete("delete from user where username=#{username}")
    int deleteUser(@Param("username") String username);

    @Select("select * from user where username = #{username}")
    User getUserMsg(String username);

    void addUser(String username, String password);

    @Update("update user set password = #{password} where username = #{username}")
    int modifyUserPassword(User user);

    @Select("select * from user where user_id = #{userId}")
    User getUserMsgByUserId(@Param("userId") int userId);

    @Update("update user set use_state = #{useState},money=#{money},using_car = #{usingCar} where user_id = #{userId};")
    int UpdateMsg(User user);

    @Update("update user set use_state = #{useState},using_car = #{usingCar} where user_id = #{userId};")
    int updateUseState(@Param("userId") int userId, @Param("useState") int useState, @Param("usingCar") int usingCar);
}



