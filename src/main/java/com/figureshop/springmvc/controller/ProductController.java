package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProductsList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "integrated:welcome";
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public String getProductDetails(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductDetails(id));
        return "integrated:productDetails";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchProductsByName(@RequestParam(required = false) String name) {
        ModelAndView modelAndView = new ModelAndView();
        if (name == null || StringUtils.isEmpty(name)) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        List<Translation> translations = productService.searchProductsByName(name);
        modelAndView.setViewName("integrated:result");
        modelAndView.addObject("translations", translations);
        return modelAndView;
    }

}
