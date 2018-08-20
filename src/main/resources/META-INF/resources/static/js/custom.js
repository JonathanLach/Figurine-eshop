$( document ).ready(function() {
    $.get( "/cart/size", function(data) {
          $( "#nbInCart" ).html(data);
    });

    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });
});

function addProductToCart(productId){
    $.post( "/cart/add/"+productId, function( data ) {
          $( "#nbInCart" ).html( data );
           window.scrollTo(0, 0);
    });
}

function addQuantityOfProductInCart(productId) {
    $.post( "/cart/add/"+productId+"?qty=" + $("#qty-add-cart").val(), function(data) {
          $( "#nbInCart" ).html(data);
           window.scrollTo(0, 0);
    });
}

function updateCart(productId) {
    $.ajax({
        url: '/cart/update/' + productId + "?qty=" + $( "#qty-" + productId ).val(),
        type: 'PUT',
        success: function(data) {
            $( "#cartTotalPrice" ).html(data.totalPrice + "€");
            $( "#cartTotalPriceTVA" ).html(data.totalPriceTVA + "€");
            $( "#subTotal-" + productId ).html(data.subTotal + "€");
            document.location.reload();
        }
    });
}

function deleteProductFromCart(productId) {
    $.ajax({
        url: '/cart/delete/' + productId,
        type: 'DELETE',
        success: function(data) {
            $("#product-" + productId).remove();
            $( "#nbInCart").html(data.size);
            $( "#cartTotalPrice" ).html(data.totalPrice + "€");
            $( "#cartTotalPriceTVA" ).html(data.totalPriceTVA + "€");
            document.location.reload();
        }
    });
}

function changeLanguage(language) {
    window.location.href += '?lang=' + language;
}