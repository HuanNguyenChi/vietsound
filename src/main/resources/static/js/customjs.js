
$(document).ready(function(){
    $(".btn-love").click(function (){
        var idMusic = $(this).closest(".featured-artist-content").find(".data-id-song-detail").text();

        $.ajax({
            url : "/api/user/addsongtouser",
            type : "get",
            data : {
                idMusic : idMusic,
            },
            success : function (value){
                alert(value)

            }
        })
    })
    $(".btn-follow").click(function (){
        var idSinger = $(this).closest(".container").find(".data-id-singer-detail").text();
        $.ajax({
            url : "/api/user/addsingertouser",
            type : "get",
            data : {
                idSinger : idSinger,
            },
            success : function (value){
                alert(value)
            }
        })
    })
    $("span.icon-trash.btn-dislike").click(function (){
        alert("check")
        // var parent = $(this).closest(".featured-artist-content");
        // var idSong = parent.find(".data-id-song-detail-user").text();
        // alert(idSong)
    })
});
