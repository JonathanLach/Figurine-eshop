package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProductsList(Model model, @RequestParam(required = false) String name) {
        List<Product> products = new ArrayList<>();
        if (name == null || StringUtils.isEmpty(name)) {
            products = productService.getAllProducts();
        }
        else  {
            products = productService.searchProductsByName(name);
        }
        model.addAttribute("products", products);
        return "integrated:welcome";
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public String getProductDetails(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductDetails(id));
        return "integrated:productDetails";
    }
}
