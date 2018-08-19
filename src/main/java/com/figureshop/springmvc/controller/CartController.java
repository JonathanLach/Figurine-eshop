package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.constants.PricingConstants;
import com.figureshop.springmvc.model.*;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request, HttpSession session, Locale locale) {
        HashMap<Long, ProductItem> cart = (HashMap<Long, ProductItem>) session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");

        if(cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        if(totalPrice == null ) {
            totalPrice = new BigDecimal(0).setScale(PricingConstants.PRICE_SCALE, BigDecimal.ROUND_UP);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("language", locale);
        session.setAttribute("totalPrice", totalPrice);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("tvaAmount", productService.getVATAmount(totalPrice));
        totalPrice = productService.getTotalPriceWithTVA(totalPrice);
        model.addAttribute("totalPriceTVA", totalPrice);
        return "integrated:cart";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CartUpdateInfo updateCartProductItem(@PathVariable Long id, @RequestParam(name = "qty") int qty,
                                 HttpServletRequest request,  HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long, ProductItem>) session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        ProductItem productItem = cart.get(id);
        CartUpdateInfo cartUpdateInfo = new CartUpdateInfo();
        if (productItem != null) {
            totalPrice = productService.updateTotalPrice(productItem, qty, totalPrice);
            productItem.setQuantity(qty);
            cartUpdateInfo.setSubTotal(productItem.getSubTotal());
            session.setAttribute("totalPrice", totalPrice);
            cartUpdateInfo.setTotalPrice(totalPrice);
            cartUpdateInfo.setTotalPriceTVA(productService.getTotalPriceWithTVA(totalPrice));
        }
        return cartUpdateInfo;
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    @ResponseBody
    public Integer cartSize(HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long, ProductItem>) session.getAttribute("cart");
        int result = 0;
        if (cart != null) {
            result = cart.size();
        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CartInfo deleteFromCart(@PathVariable Long id, HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long,ProductItem>)session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        if(cart != null) {
            ProductItem productItem = cart.get(id);
            if(productItem != null) {
                totalPrice = productService.updateTotalPriceDown(totalPrice, productItem.getSubTotal());
                cart.remove(id);
            }
        }
        else {
            cart = new HashMap<>();
        }

        CartInfo cartInfo = new CartInfo();
        cartInfo.setSize(cart.size());
        session.setAttribute("totalPrice", totalPrice);
        cartInfo.setTotalPrice(totalPrice);
        cartInfo.setTotalPriceTVA(productService.getTotalPriceWithTVA(totalPrice));
        return cartInfo;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Integer addToCart(@PathVariable Long id,
                             @RequestParam(name = "qty",required = false, defaultValue = "1") int qty,
                             HttpServletRequest request, HttpSession session, Locale locale) {
        HashMap<Long, ProductItem> cart = (HashMap<Long,ProductItem>)session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        if(cart == null) {
            cart = new HashMap<>();
        }
        if(totalPrice == null ) {
            totalPrice = new BigDecimal(0).setScale(PricingConstants.PRICE_SCALE, BigDecimal.ROUND_UP);
        }
        ProductItem item = cart.get(id);
        BigDecimal formerSubTotal = new BigDecimal(0.00);
        if(item == null){
            item = new ProductItem();
            Translation translation = productService.getProductDetails(id, locale.getLanguage());
            item.setTranslation(translation);
            item.setQuantity(qty);
            item.setSubTotal(productService.getSubTotal(item.getTranslation().getProduct().getPrice(), qty));
        } else {
            item.setQuantity(item.getQuantity() + qty);
            formerSubTotal = item.getSubTotal();
            item.setSubTotal(productService.getSubTotal(item.getTranslation().getProduct().getPrice(), item.getQuantity()));
        }
        totalPrice = productService.updateTotalPriceUp(totalPrice, item.getSubTotal(), formerSubTotal);
        cart.put(id, item);
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("cart", cart);
        return cart.size();
    }

}
