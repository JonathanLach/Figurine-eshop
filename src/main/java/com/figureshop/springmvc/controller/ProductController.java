package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping(method = RequestMethod.GET)
    public String getProductsList(Model model, @RequestParam(required = false) String name, Locale locale) {
        List<Translation> translations = new ArrayList<>();
        if (name == null || StringUtils.isEmpty(name)) {
            translations = productService.getAllProducts(locale.getLanguage());
        }
        else  {
            translations = productService.searchProductsByName(name, locale.getLanguage());
        }
        model.addAttribute("translations", translations);
        return "integrated:welcome";
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public String getProductDetails(@PathVariable Long id, Model model, Locale locale) {
        model.addAttribute("translation", productService.getProductDetails(id, locale.getLanguage()));
        return "integrated:productDetails";
    }
}
