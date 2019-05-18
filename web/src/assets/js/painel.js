function carregaPainelCidadeTable(e){
    if(e) e.preventDefault() 
    fetch("/admin/painelcidade.html")
        .then(resp=>resp.text())
            .then(html=>{
                $(".list-painel").html(html)
            }).then(carregaPainelCidades)
}

function carregaPainelCidades(e){
    if(e) e.preventDefault() 
    
    let url;
    let pesquisa=$(".txtpainelcidades").val()
    
    url=service+"/cidade/catalog/0?param=nome&value="+pesquisa

    fetch(url)
        .then(resp=>resp.json())
            .then(cidades=>{
                $("tbody").html("")
                cidades.forEach(cidade=> {
                    $("tbody").append(`
                        <tr  class="painel-row" id="${cidade.id}">
                            <td>${cidade.nome}</td>
                            <td>${cidade.estado.nome}</td>
                            <td>${cidade.pais.nome}</td>
                            <td>${cidade.populacao}</td>
                            <td><a class="btn-alterar-cidade" href="">Alterar</a>&nbsp;<a class="btn-excluir-cidade" href="">Excluir</a></td>
                        </tr>
                    `)
                });
            })
}


function carregaPainelPontoTable(e){
    if(e) e.preventDefault() 
    fetch("/admin/painelpontoturistico.html")
        .then(resp=>resp.text())
            .then(html=>{
                $(".list-painel").html(html)
            }).then(carregaPainelPontos)
}

function carregaPainelPontos(e){
    if(e) e.preventDefault() 
    
    let url;
    let pesquisa=$(".txtpainelponto").val()
    
    if(pesquisa==""){
        url=service+"/pontoturistico/catalog/0/default"
    }else{
        url=service+"/pontoturistico/catalog/0/"+pesquisa
    }
    
    fetch(url)
        .then(resp=>resp.json())
            .then(pontos=>{
                $("tbody").html("")
                pontos.forEach(ponto=> {
                    $("tbody").append(`
                        <tr  class="painel-row" id="${ponto.id}">
                            <td>${ponto.nome}</td>
                            <td>${ponto.rua}</td>
                            <td>${ponto.numero}</td>
                            <td>${ponto.cidade.nome}</td>
                            <td><a class="btn-alterar-ponto"href="">Alterar</a>&nbsp;<a class="btn-excluir-ponto" href="">Excluir</a></td>
                        </tr>
                    `)
                });
            })
}


$(document).on("click",".btn-ponto",carregaPainelPontoTable)
$(document).on("click",".btn-painelPontos",carregaPainelPontos)

$(document).on("click",".btn-cidade",carregaPainelCidadeTable)
$(document).on("click",".btn-painelCidades",carregaPainelCidades)