







//
$(".cidade-item").on("click",(e)=>{
    $("body").prepend(infoCidades)
})
$(document).on("click",".btn-closeinfo",(e)=>{
    $(".info").remove()
})