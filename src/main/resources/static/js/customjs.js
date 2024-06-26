$(document).ready(function(){
    const parent = document.querySelector.bind(document);
    const parentAll = document.querySelectorAll.bind(document);

    const tabs = parentAll(".tab-item");
    const panes = parentAll(".tab-pane");

    const tabActive = parent(".tab-item.active");
    const line = parent(".tabs .line");

    requestIdleCallback(function () {
        line.style.left = tabActive.offsetLeft + "px";
        line.style.width = tabActive.offsetWidth + "px";
    });

    tabs.forEach((tab, index) => {
        const pane = panes[index];

        tab.onclick = function () {
            parent(".tab-item.active").classList.remove("active");
            parent(".tab-pane.active").classList.remove("active");

            line.style.left = this.offsetLeft + "px";
            line.style.width = this.offsetWidth + "px";

            this.classList.add("active");
            pane.classList.add("active");
        };
    });
    $(".btn-love").click(function (){
        var idMusic = $(this).closest(".featured-artist-content").find(".data-id-song-detail").text();

        $.ajax({
            url : "/api/user/v1/addsongtouser",
            type : "put",
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
            url : "/api/user/v1/addsingertouser",
            type : "put",
            data : {
                idSinger : idSinger,
            },
            success : function (value){
                alert(value)
            }
        })
    })

    // jQuery for user home
    $(".btn-dislike").click(function (){
        var parentDislike = $(this).closest(".featured-artist-content");
        var idSong = parentDislike.find(".data-id-song-detail-user").text();
        alert(idSong);
        $.ajax({
            url : "/api/user/v1/removesongfromuser",
            type : "delete",
            data : {
                idSong : idSong,
            },
            success : function (value){
                alert(value)
                parentDislike.remove();
            }
        })
    })

    // jQuery for user update info
    $('#btn-userUpdateInfo').click(function (){
        var fullname = document.getElementById('fullname').value;
        var email = document.getElementById('email').value;
        var phone = document.getElementById('phone').value;
        var address = document.getElementById('address').value;
        var dataIdSpan = document.getElementById('data-id');
        var username = dataIdSpan.innerText;
        alert(fullname + username);
        $.ajax({
            url : "/api/user/v1/updateinfo/",
            type : "put",
            data : {
                username : username,
                fullname : fullname,
                email : email,
                phone : phone,
                address : address
            },
            success : function (value){
                if(value == "true"){
                    linkCurrent = window.location.href;
                    linkReplace = linkCurrent.replace("/user/updateinfo/"+username,"/user/" + username);
                    alert("/user/" + username);
                    window.location = linkReplace;
                }
            }
        })
    })

    // jquery for top hit
    $('#btn-tophit-get-song').click(function (){
        var idPage = parseInt($(this).attr("data-page"));
        $.ajax({
            url : "/api/user/v1/tophit/loadmoresongs",
            type : 'get',
            data : {
                idPage : idPage + 1,
                // idCategory : valueCategoryId,
            },
            success : function (value){
                // alert(value)
                $("#data-song-in-tophit-page").append(value);
                $("#btn-tophit-get-song").attr("data-page",idPage + 1);
            }
        })
    })
    $('#btn-tophit-get-album').click(function (){
        var idPage = parseInt($(this).attr("data-page"));
        $.ajax({
            url : "/api/user/v1/tophit/loadmorealbums",
            type : 'get',
            data : {
                idPage : idPage + 1,
            },
            success : function (value){
                // alert(value)
                $("#data-album-in-tophit-page").append(value);
                $("#btn-tophit-get-album").attr("data-page",idPage + 1);
            }
        })
    })
    $('#btn-tophit-get-category').click(function (){
        var idPage = parseInt($(this).attr("data-page"));
        $.ajax({
            url : "/api/user/v1/tophit/loadmorecategorys",
            type : 'get',
            data : {
                idPage : idPage + 1,
            },
            success : function (value){
                $("#data-category-in-tophit-page").append(value);
                $("#btn-tophit-get-category").attr("data-page",idPage + 1);
            }
        })
    })

    // jQuery for singer detail

    $('#btn-singer-detail-get-song').click(function (){
        var valueSingerId = $("#value-singer-id-in-singer-detail").text();
        var idPage = parseInt($(this).attr("data-page"));
        alert(valueSingerId + " " + idPage)
        $.ajax({
            url : "/api/user/v1/singerdetail/loadmoresongs",
            type : 'get',
            data : {
                idPage : idPage + 1,
                idSinger : valueSingerId,
            },
            success : function (value){
                // alert(value)
                $("#data-song-in-singer-detail-page").append(value);
                $("#btn-singer-detail-get-song").attr("data-page",idPage + 1);
            }
        })
    })
    $('#btn-singer-detail-get-album').click(function (){
        var valueSingerId = $("#value-singer-id-in-singer-detail").text();
        var idPage = parseInt($(this).attr("data-page"));
        alert(valueSingerId + " " + idPage)
        $.ajax({
            url : "/api/user/v1/singerdetail/loadmorealbums",
            type : 'get',
            data : {
                idPage : idPage + 1,
                idSinger : valueSingerId,
            },
            success : function (value){
                // alert(value)
                $("#data-album-in-singer-detail-page").append(value);
                $("#btn-singer-detail-get-album").attr("data-page",idPage + 1);
            }
        })
    })

    // jQuery for song detail
    $('#btn-song-detail-get-album').click(function (){
        var valueSingerId = $("#value-singer-id").text();
        var idPage = parseInt($(this).attr("data-page"));
        alert(valueSingerId + " " + idPage)
        $.ajax({
            url : "/api/user/v1/songdetail/loadmorealbums",
            type : 'get',
            data : {
                idPage : idPage + 1,
                idSinger : valueSingerId,
            },
            success : function (value){
                // alert(value)
                $("#data-album-in-song-detail-page").append(value);
                $("#btn-song-detail-get-album").attr("data-page",idPage + 1);
            }
        })
    })
    $('#btn-song-detail-get-song').click(function (){
        var valueSingerId = $("#value-singer-id").text();
        var idPage = parseInt($(this).attr("data-page"));
        alert(valueSingerId + " " + idPage)
        $.ajax({
            url : "/api/user/v1/songdetail/loadmoresongs",
            type : 'get',
            data : {
                idPage : idPage + 1,
                idSinger : valueSingerId,
            },
            success : function (value){
                // alert(value)
                $("#data-song-in-song-detail-page").append(value);
                $("#btn-song-detail-get-song").attr("data-page",idPage + 1);
            }
        })
    })

});
