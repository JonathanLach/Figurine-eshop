package com.figureshop.springmvc.service;

import com.figureshop.springmvc.exception.MailAlreadyExistsException;
import com.figureshop.springmvc.exception.UserAlreadyExistsException;
import com.figureshop.springmvc.model.User;

public interface UserService {
    void registerUser(User user) throws UserAlreadyExistsException, MailAlreadyExistsException;
}
