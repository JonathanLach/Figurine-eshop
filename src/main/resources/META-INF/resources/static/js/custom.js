function addProductToCart(productId){
    $.post( "/cart/add/"+productId, function( data ) {
      $( "#nbInCart" ).html( data );
      //Afficher une notification success
    });
}

function changeLanguage(language) {
    window.location.href += '?lang=' + language;
}