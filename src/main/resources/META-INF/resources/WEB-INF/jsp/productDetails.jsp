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
                <img height="100" width="50" src="data:image/jpg;base64, ${product.base64Picture}" />
            </div>
        </div>
    </div>
    <!-- /Product main img -->

    <!-- Product details -->
    <div class="col-md-5">
        <div class="product-details">
            <h2 class="product-name">
                <c:forEach items="${product.translations}" var="translation">
                <p>Translation: <c:out value="${translation.translation}"/></p>
                </c:forEach>
            </h2>
        <div>
        <div>
            <h3 class="product-price"><c:out value="${product.price}"/>€</h3>
        </div>
            <p><c:out value="${product.description}"/></p>

            <div class="add-to-cart">
                <div class="qty-label">
                    Qty
                    <div class="input-number">
                        <input type="number" value="1">
                        <span class="qty-up">+</span>
                        <span class="qty-down">-</span>
                    </div>
                </div>
                <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i><spring:message code="label.addToCart"/></button>
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
                            <p><c:out value="${product.description}"/></p>
                        </div>
                    </div>
                </div>
                <!-- /tab1  -->
            </div>
        </div>
    </div>
</body>
</html>



