package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
import com.figureshop.springmvc.model.Purchase;
import com.figureshop.springmvc.model.User;
import com.figureshop.springmvc.service.PurchaseService;
import ma.glasnost.orika.MapEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(method = RequestMethod.POST)
    public String purchaseCartContent(HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long, ProductItem>) session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        Purchase purchase = new Purchase();
        if (cart != null) {
            cart.forEach((k, v) -> {
                purchase.getProductItems().add(v);
            });
            purchase.setPurchaseDate(new Date());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            purchaseService.savePurchase(purchase, username);
            cart = new HashMap<>();
            totalPrice = new BigDecimal(0.00);
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("cart",cart);
        }
        return "integrated:welcome";
    }
}
