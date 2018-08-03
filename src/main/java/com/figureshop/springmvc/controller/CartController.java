package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public Integer addToCart(@PathVariable Long id, ProductItem productItem, HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long,ProductItem>)session.getAttribute("cart");
        if(cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        cart.put(id, productItem);
        session.setAttribute("cart", cart);
        return cart.size();
    }

}
