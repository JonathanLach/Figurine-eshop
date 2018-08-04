package com.figureshop.springmvc.controller;

import com.figureshop.springmvc.model.CartInfo;
import com.figureshop.springmvc.model.CartUpdateInfo;
import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long, ProductItem>) session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        if(cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        if(totalPrice == null ) {
            totalPrice = new BigDecimal(0);
        }
        for(Map.Entry<Long, ProductItem> cartElement : cart.entrySet()) {
            totalPrice = totalPrice.add(cartElement.getValue().getSubTotal());
        }
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        session.setAttribute("totalPrice", totalPrice);
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
            int qtyDifference = productItem.getQuantity() - qty;
            if(qtyDifference > 0) {
                BigDecimal valueToSubtract = productItem.getProduct().getPrice().multiply(new BigDecimal(qtyDifference));
                productItem.setSubTotal(productItem.getSubTotal()
                        .subtract(valueToSubtract));
                totalPrice = totalPrice.subtract(valueToSubtract);
            }
            else {
                qtyDifference = Math.abs(qtyDifference);
                BigDecimal valueToAdd = productItem.getProduct().getPrice().multiply(new BigDecimal(qtyDifference));
                productItem.setSubTotal(productItem.getSubTotal()
                        .add(valueToAdd));
                totalPrice = totalPrice.add(valueToAdd);
            }
            productItem.setQuantity(qty);
            cartUpdateInfo.setSubTotal(productItem.getSubTotal());
            cartUpdateInfo.setTotalPrice(totalPrice);
            session.setAttribute("totalPrice", totalPrice);
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
                totalPrice = totalPrice.subtract(productItem.getSubTotal());
                cart.remove(id);
            }
        }
        else {
            cart = new HashMap<>();
        }

        CartInfo cartInfo = new CartInfo();
        cartInfo.setSize(cart.size());
        cartInfo.setTotalPrice(totalPrice);
        session.setAttribute("totalPrice", totalPrice);
        return cartInfo;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Integer addToCart(@PathVariable Long id,
                             @RequestParam(name = "qty",required = false, defaultValue = "1") int qty,
                             HttpServletRequest request, HttpSession session) {
        HashMap<Long, ProductItem> cart = (HashMap<Long,ProductItem>)session.getAttribute("cart");
        if(cart == null) {
            cart = new HashMap<>();
        }
        ProductItem item = cart.get(id);
        if(item == null){
            item = new ProductItem();
            Product product = productService.getProductDetails(id);
            item.setProduct(product);
            item.setQuantity(qty);
            item.setSubTotal(product.getPrice().multiply(new BigDecimal(qty)));
        } else {
            item.setQuantity(item.getQuantity() + qty);
            item.setSubTotal(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        cart.put(id, item);
        session.setAttribute("cart", cart);
        return cart.size();
    }

}
