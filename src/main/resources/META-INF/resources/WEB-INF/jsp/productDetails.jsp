<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <!-- Product main img -->
    <div class="col-md-5 col-md-push-2">
        <div id="product-main-img">
            <div class="product-preview">
                <img width="150" height="300" src="data:image/jpg;base64, ${translation.product.base64Picture}" />
            </div>
        </div>
    </div>
    <!-- /Product main img -->

    <!-- Product details -->
    <div class="col-md-5" id="details-container">
        <div class="product-details">
            <h2 class="product-name">
                <p><c:out value="${translation.productName}"/></p>
            </h2>
        <div>
        <div>
            <h3 class="product-price"><c:out value="${translation.product.price}"/>€</h3>
        </div>
            <div class="add-to-cart">
                <div class="qty-label">
                    <spring:message code="label.qty"/>
                    <div class="input-number">
                        <input type="number" id="qty-add-cart" value="1">
                        <span class="qty-up">+</span>
                        <span class="qty-down">-</span>
                    </div>
                </div>
                <button class="add-to-cart-btn" onclick="addQuantityOfProductInCart(${translation.product.id})"><i class="fa fa-shopping-cart"></i><spring:message code="label.addToCart"/></button>
            </div>
        </div>
    </div>
    <!-- /Product details -->

    <!-- Product tab -->
    <div class="col-md-12">
        <div id="product-tab">
            <!-- product tab nav -->
            <ul class="tab-nav">
                <li class="active"><a data-toggle="tab" href="#tab1"><spring:message code="label.description"/></a></li>
            </ul>
            <!-- /product tab nav -->

            <!-- product tab content -->
            <div class="tab-content">
                <!-- tab1  -->
                <div id="tab1" class="tab-pane fade in active">
                    <div class="row">
                        <div class="col-md-12">
                            <p><c:out value="${translation.productDescription}"/></p>
                        </div>
                    </div>
                </div>
                <!-- /tab1  -->
            </div>
        </div>
    </div>
</body>
</html>



