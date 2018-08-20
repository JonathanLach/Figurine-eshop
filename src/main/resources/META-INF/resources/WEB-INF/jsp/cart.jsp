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
        <th class="column-2"><spring:message code="label.product"/></th>
        <th class="column-3"><spring:message code="label.price"/></th>
        <th class="column-4 p-l-70"><spring:message code="label.quantity"/></th>
        <th class="column-5"><spring:message code="label.total"/></th>
    </tr>

    <c:forEach items="${cart}" var="cartElement">
        <tr class="table-row" id="product-${cartElement.value.translation.product.id}">
            <td class="delete-btn">
                <button class="delete" onclick="deleteProductFromCart(${cartElement.value.translation.product.id})"><i class="fa fa-close"></i></button>
            </td>
            <td class="column-1">
                <div class="cart-img-product b-rad-4 o-f-hidden">
                    <img src="data:image/jpg;base64, ${cartElement.value.translation.product.base64Picture}" alt="">
                </div>
            </td>
            <td class="column-2">
                <h3 class="product-name"><a href="/product/${cartElement.value.translation.product.id}"><c:out value="${cartElement.value.translation.productName}"/></a></h3>
            </td>
            <td class="column-3"><c:out value="${cartElement.value.translation.product.price}"/>€</td>
            <td class="column-4">
                <div>
                    <input min="1" onchange="updateCart(${cartElement.value.translation.product.id})" type="number" id="qty-${cartElement.value.translation.product.id}" name="num-product2" value="${cartElement.value.quantity}">
                </div>
            </td>
            <td class="column-5"><div id="subTotal-${cartElement.value.translation.product.id}"><c:out value="${cartElement.value.subTotal}"/>€</div></td>
        </tr>
    </c:forEach>
</table>


<tr class="table-row" id="product-${cartElement.value.product.id}">
            <td class="column-1"></td>
            <td class="column-2"></td>
            <td class="column-3"></td>
            <td class="column-4"></td>
            <td class="column-5"><spring:message code="label.total"/>: <div id="cartTotalPrice"><c:out value="${totalPrice}"/>€</div></td>
</tr>

<tr class="table-row" id="product-${cartElement.value.product.id}">
            <td class="column-1"></td>
            <td class="column-2"></td>
            <td class="column-3"></td>
            <td class="column-4"></td>
            <td class="column-5"><spring:message code="label.totalWithVat"/>: <div id="cartTotalPriceTVA"><c:out value="${totalPriceTVA}"/>€</div>
</div></td>
</tr>

<div class="row">
    <div class="col-md-12">
        <sec:authorize access="isAuthenticated()">
             <div id="paypal-button"></div>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <p><spring:message code="error.mustBeLogged"/></p>
        </sec:authorize>

        <script src="https://www.paypalobjects.com/api/checkout.js"></script>
        <div id="paypalScript">
        <script>
            paypal.Button.render({
              // Configure environment
              env: 'sandbox',
              client: {
                sandbox: 'ASeCfX0P9EnVfLmmjjE1v26R6VYdN_xL70cwE10lO5Xtvyq3zOOJIwfBJ4RSgp5Si731-IW3WoRPpuQo',
              },
              locale: '<c:out value="${language}"/>',
              // Customize button (optional)
              style: {
                size: 'large',
                color: 'silver',
                shape: 'rect',
                label: 'checkout',
                tagline: 'true'
              },
              // Set up a payment
              payment: function (data, actions) {
                return actions.payment.create({
                    "intent": "sale",
                    "payer": {
                        "payment_method": "paypal"
                    },
                  transactions: [{
                    amount: {
                      total: '<c:out value="${totalPriceTVA}"/>',
                      currency: 'EUR',
                      details: {
                        subtotal: '<c:out value="${totalPrice}"/>',
                        tax: '<c:out value="${tvaAmount}"/>',
                        shipping: '0.00'
                      }
                    },
                    description: 'Imagination Dream',
                    soft_descriptor: 'Imagination Dream',
                    "item_list": {
                                    "items":[
                                        {
                                            "quantity":"1",
                                            "name":"Total",
                                            "price":'<c:out value="${totalPrice}"/>',
                                            "sku":"product12345",
                                            "currency":"EUR"
                                        }
                                    ]
                                }
                  }]
                });
              },
              // Execute the payment
              onAuthorize: function (data, actions) {
                return actions.payment.execute()
                  .then(function () {
                    // Show a confirmation message to the buyer
                    $.post( "/checkout", function(data) {
                        window.alert('<spring:message code="message.thanksForPurchase"/>');
                        window.location.href = "/";
                        });
                  });
              }
            }, '#paypal-button');
        </script>
        </div>
    </div>
</div>

</body>
</html>