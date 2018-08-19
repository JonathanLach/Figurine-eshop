<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <c:forEach items="${translations}" var="translation">
                <div class="col-md-4 col-xs-6">
                    <div class="product">
                        <div class="product-img">
                        <img alt="" width="100" height="300" src="data:image/jpg;base64, ${translation.product.base64Picture}" />
                        </div>
                        <div class="product-body">
                            <h3 class="product-name"><a href="/product/${translation.product.id}"><c:out value="${translation.productName}"/></a></h3>
                            <h4 class="product-price"><c:out value="${translation.product.price}"/>€</h4>
                        </div>
                        <div class="add-to-cart">
                            <button class="add-to-cart-btn" onclick="addProductToCart(${translation.product.id})"><i class="fa fa-shopping-cart"></i> <spring:message code="label.addToCart"/></button>
                        </div>
                    </div>
                </div>
    </c:forEach>
</body>
</html>