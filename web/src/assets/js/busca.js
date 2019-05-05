let service="http://192.168.0.25:8080"


function atualizaCidadesPesquisa(event){
    $(".search-cidades").html(" ")
    let url;
    let pesquisa=$(".txtpesquisa").val()
    
    if(event!=undefined) event.preventDefault() 
    
    if(pesquisa==""){
        url=service+"/cidade/catalog/0/a"
    }else{
        url=service+"/cidade/catalog/0/"+pesquisa
    }
    
    fetch(url)
        .then(resp=>resp.json())
            .then(json=>{
                json.forEach(cidade => {
                    $(".search-cidades")
                        .append(`<article id="${cidade.id}" class='cidade-item'>
                            <h3>${cidade.nome}</h3>
                            <p>${cidade.estado.nome},${cidade.pais.nome}</p>
                            <p>População: ${cidade.populacao}</p>
                            <h4></h4>
                        </article>`)
                });
            })
    
}

$(".btn-pesquisar").on("click",atualizaCidadesPesquisa)

atualizaCidadesPesquisa()