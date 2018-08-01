package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.business.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class WelcomeController {

    @Autowired
    private ProductManager productManager;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("products", productManager.getAllProducts());
        return "welcome";
    }
}
