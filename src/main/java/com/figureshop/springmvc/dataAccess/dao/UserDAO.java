package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.User;

public interface UserDAO {

    void registerUser(User user);

    User getUserByUsername(String username);

    User getUserByMail(String mail);
}
