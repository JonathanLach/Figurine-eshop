<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<table class="table-shopping-cart">
    <tr class="table-head">
        <th class="delete-btn"></th>
        <th class="column-1"></th>
        <th class="column-2">Product</th>
        <th class="column-3">Price</th>
        <th class="column-4 p-l-70">Quantity</th>
        <th class="column-5">Total</th>
    </tr>

    <c:forEach items="${cart}" var="cartElement">
        <tr class="table-row" id="product-${cartElement.value.product.id}">
            <td class="delete-btn">
                <button class="delete" onclick="deleteProductFromCart(${cartElement.value.product.id})"><i class="fa fa-close"></i></button>
            </td>
            <td class="column-1">
                <div class="cart-img-product b-rad-4 o-f-hidden">
                    <img src="data:image/jpg;base64, ${cartElement.value.product.base64Picture}" alt="">
                </div>
            </td>
            <td class="column-2">
                <c:forEach items="${cartElement.value.product.translations}" var="translation">
                    <h3 class="product-name"><a href="/product/${cartElement.value.product.id}"><c:out value="${translation.translation}"/></a></h3>
                </c:forEach></td>
            <td class="column-3"><c:out value="${cartElement.value.product.price}"/>€</td>
            <td class="column-4">
                <div>
                    <input min="1" onchange="updateCart(${cartElement.value.product.id})" type="number" id="qty-${cartElement.value.product.id}" name="num-product2" value="${cartElement.value.quantity}">
                </div>
            </td>
            <td class="column-5"><div id="subTotal-${cartElement.value.product.id}"><c:out value="${cartElement.value.subTotal}"/>€</div></td>
        </tr>
    </c:forEach>


</table>

<div class="row">
    <div class="col-md-4">
        Total: <div id="cartTotalPrice"><c:out value="${totalPrice}"/>€</div>
    </div>
    <div class="col-md-4">
        <button>Update Cart</button>
    </div>
    <div class="col-md-4">
        <button>Checkout</button>
    </div>
</div>
</body>
</html>