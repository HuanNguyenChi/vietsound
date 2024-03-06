$(document).ready(function(){
    $('.load-more-btn').click(function (){

    })
    // $('.quantity').change(function (){
    //     var parent = $(this).closest('.represent');
    //     var quantitynew = parent.find(".inputQuantity").val();
    //     var price = parent.find('.price').attr('data-price');
    //     var productId = parent.find('.product').attr('data-product-id');
    //     var colorId = parent.find('.product').attr('data-color-id');
    //     var sizeId = parent.find('.product').attr('data-size-id');
    //     var totalPrice1 = parseInt(quantitynew)  * parseInt(price);
    //     parent.find('.price').html('$ ' + totalPrice1 );
    //     $.ajax({
    //         url : "/api/addtocart",
    //         type : "get",
    //         data : {
    //             productId : productId,
    //             sizeId : sizeId,
    //             colorId : colorId,
    //             quantity : quantitynew
    //         },
    //         success : function (value){
    //             tinhTien();
    //         }
    //     });
    //
    // });
    // $('.delete').click(function (){
    //     var parent = $(this).closest('.represent');
    //     var productId = parent.find('.product').attr('data-product-id');
    //     var colorId = parent.find('.product').attr('data-color-id');
    //     var sizeId = parent.find('.product').attr('data-size-id');
    //     $.ajax({
    //         url : "/api/deletecart",
    //         type : "get",
    //         data : {
    //             productId : productId,
    //             sizeId : sizeId,
    //             colorId : colorId,
    //         },
    //         success : function (value){
    //             parent.remove();
    //             tinhTien();
    //             $(".quantityItems").html(value);
    //         }
    //     });
    // });

});