package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.service.UserService;
import com.figureshop.springmvc.service.impl.UserServiceImpl;
import com.figureshop.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "integrated:register";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute(value = "user") User user, final BindingResult errors) {
        if (errors.hasErrors()) {
            return "integrated:register";
        }
        userService.registerUser(user);
        return "integrated:welcome";
    }
}
