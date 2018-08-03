<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <c:forEach items="${products}" var="product">
                <div class="col-md-4 col-xs-6">
                    <div class="product">
                        <div class="product-img">
                        <img alt="" height="200" width="200" src="data:image/jpg;base64, ${product.base64Picture}" />
                        </div>
                        <div class="product-body">
                            <c:forEach items="${product.translations}" var="translation">
                                <h3 class="product-name"><a href="/product/${product.id}"><c:out value="${translation.translation}"/></a></h3>
                            </c:forEach>
                            <h4 class="product-price"><c:out value="${product.price}"/>€</h4>
                        </div>
                        <div class="add-to-cart">
                            <button class="add-to-cart-btn" onclick="addProductToCart(${product.id})"><i class="fa fa-shopping-cart"></i> <spring:message code="label.addToCart"/></button>
                        </div>
                    </div>
                </div>
    </c:forEach>
</body>
</html>