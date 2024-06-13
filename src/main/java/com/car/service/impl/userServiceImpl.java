package com.car.service.impl;

import com.car.entity.Result;
import com.car.entity.User;
import com.car.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private com.car.mapper.userMapper userMapper;


    @Override
    public User findUser(String username) {
        return userMapper.isExist(username);
    }

    @Override
    public void addUser(String username, String password) {
        userMapper.addUser(username,password);
    }

    @Override
    public User findUser(String username, String password) {
        return userMapper.isExistUser(username,password);
    }

    @Override
    public String findPassWordByUsername(String username) {
        return userMapper.findPassWordByUsername(username);
    }

    @Override
    public void modifyUserMsg(User user) {
        userMapper.modifyUserMsg(user);
    }

    @Override
    public boolean deleteUser(String username) {
        int count =  userMapper.deleteUser(username);
        if (count>0)return true;
        return false;
    }

    @Override
    public User getUserMsg(String username) {
       return userMapper.getUserMsg(username);
    }

    @Override
    public boolean addUser(User user) {
        user.setUsingCar(-1);
        user.setUseState(0);
        user.setMoney(5000);
        int count = userMapper.addUser(user);
        if (count >0){
            return true;
        }
        return false;
    }

    @Override
    public Result modifyUserPassword(User user) {
        int count = userMapper.modifyUserPassword(user);
        if(count>0){
            return Result.success("密码修改成功");
        }
        return Result.success("密码修改失败");
    }
}
