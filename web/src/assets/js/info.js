let info;
setInfo()

function setInfo(){
    getHTML("admin/info.html")
        .then(html=>{
            info=html
        }) 
}

function carregaCidadePosts(event){
    let cidade=event.target.parentNode;
    let tableBody=$(".pontos-add")

    $(".info").attr("id",$(cidade).attr("id"))
    $(".cidade").html(cidade.firstElementChild.firstElementChild.innerHTML)
    $(".info-subtitle").html(cidade.firstElementChild.children[1].innerHTML)
    
    let path="/pontoturistico/cidade/"+$(cidade).attr("id")
    
    getJSON(path)
        .then(pontos=>{
            pontos.forEach(ponto => {
            let fechamento=new Date("T"+ponto.fechamento)
            let agora=new Date()
            let situacao=agora.getHours()<fechamento.getHours() ? "Aberto" : `Abre às ${ponto.abertura}`

            tableBody.append(`<tr id=${ponto.id}>
                            <td>${ponto.nome}</td>
                            <td>${situacao}</td>
                            <td id=${ponto.id}><a class="informacao" href="#">Infomações </a></td>
                        </tr>`)
                })
            })                    
}
//
$(document).on("click",".cidade-item button",(e)=>{
    $("body").prepend(info)
    carregaCidadePosts(e)
})
$(document).on("click",".btn-closeinfo",(e)=>{
    $(".info").remove()
})
$(document).on("click",".informacao",carregaPonto)