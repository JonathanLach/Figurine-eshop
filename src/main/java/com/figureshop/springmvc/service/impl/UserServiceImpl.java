package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.dataAccess.dao.UserDAO;
import com.figureshop.springmvc.exception.MailAlreadyExistsException;
import com.figureshop.springmvc.exception.UserAlreadyExistsException;
import com.figureshop.springmvc.model.User;
import com.figureshop.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(User user) throws UserAlreadyExistsException, MailAlreadyExistsException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userDAO.getUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }

        if(userDAO.getUserByMail(user.getMail()) != null) {
            throw new MailAlreadyExistsException();
        }

        userDAO.registerUser(user);
    }

}
