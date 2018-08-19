package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.exception.MailAlreadyExistsException;
import com.figureshop.springmvc.exception.UserAlreadyExistsException;
import com.figureshop.springmvc.service.UserService;
import com.figureshop.springmvc.service.impl.UserServiceImpl;
import com.figureshop.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(value="/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "integrated:register";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute(value = "user") User user, final BindingResult errors, Locale locale) {
        if(!user.getPassword().equals(user.getPasswordConfirmation())) {
            FieldError error = new FieldError("user", "passwordConfirmation", "Passwords not match");
            errors.addError(error);
        }
        if (errors.hasErrors()) {
            return "integrated:register";
        }
        try {
            userService.registerUser(user);
        }
        catch (UserAlreadyExistsException e) {
            FieldError fieldError = new FieldError("user", "username", "Username already exists");
            errors.addError(fieldError);
            return "integrated:register";
        }
        catch (MailAlreadyExistsException e)
        {
            FieldError fieldError = new FieldError("user", "mail", "Mail already exists");
            errors.addError(fieldError);
            return "integrated:register";
        }
        return "integrated:welcome";
    }
}
