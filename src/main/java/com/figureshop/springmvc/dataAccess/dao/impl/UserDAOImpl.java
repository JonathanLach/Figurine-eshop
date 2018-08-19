package com.figureshop.springmvc.dataAccess.dao.impl;

import com.figureshop.springmvc.dataAccess.dao.UserDAO;
import com.figureshop.springmvc.dataAccess.entity.UserEntity;
import com.figureshop.springmvc.dataAccess.repository.UserRepository;
import com.figureshop.springmvc.dataAccess.util.EntityMapper;
import com.figureshop.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public void registerUser(User user) {
        UserEntity userToSave = entityMapper.convertUserModelToEntity(user);
        userToSave.setAccountNonExpired(true);
        userToSave.setAccountNonLocked(true);
        userToSave.setAuthorities("ROLE_USER");
        userToSave.setEnabled(true);
        userToSave.setCredentialsNonExpired(true);
        userRepository.save(userToSave);
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity userFound = userRepository.findByUsername(username);
        return entityMapper.convertUserEntityToModel(userFound);
    }

    @Override
    public User getUserByMail(String mail) {
        UserEntity userFound = userRepository.findByMail(mail);
        return entityMapper.convertUserEntityToModel(userFound);
    }
}
