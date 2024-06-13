package com.car.service;

import com.car.entity.Result;
import com.car.entity.User;

public interface userService {
    User findUser(String username);

    void addUser(String username, String password);

    User findUser(String username, String password);

    String findPassWordByUsername(String username);

    void modifyUserMsg(User user);

    boolean deleteUser(String username);

    User getUserMsg(String username);

    boolean addUser(User user);

    Result modifyUserPassword(User user);
}
