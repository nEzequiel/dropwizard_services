


function atualizaCidadesPesquisa(event){
    $(".search-cidades").html(" ")
    let url;
    let pesquisa=$(".txtpesquisa").val()
    
    if(event) event.preventDefault() 
    
    if(pesquisa==""){
        url="/cidade/catalog/0/default"
    }else{
        url="/cidade/catalog/0/"+pesquisa
    }
    
    getJSON(url)
        .then(cidades=>{            
            cidades.forEach(cidade => {
                $(".search-cidades")
                    .append(`<article id="${cidade.id}" class='cidade-item'>
                        <div>
                            <h3>${cidade.nome}</h3>
                            <p>${cidade.estado.nome},${cidade.pais.nome}</p>
                            <p>População: ${cidade.populacao}</p>
                            <h4></h4>
                        </div>
                        <button>
                            +
                        </button>
                    </article>`
                    )
            });
        })
}

$(".btn-pesquisar").on("click",atualizaCidadesPesquisa)

atualizaCidadesPesquisa()
