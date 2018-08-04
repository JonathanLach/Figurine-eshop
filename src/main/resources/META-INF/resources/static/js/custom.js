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
    });
}

function updateCart(productId) {
    $.ajax({
        url: '/cart/update/' + productId + "?qty=" + $( "#qty-" + productId ).val(),
        type: 'PUT',
        success: function(data) {
            $( "#cartTotalPrice" ).html(data.totalPrice + "€");
            $( "#subTotal-" + productId ).html(data.subTotal + "€");
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
        }
    });
}

function changeLanguage(language) {
    window.location.href += '?lang=' + language;
}