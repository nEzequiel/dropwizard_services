
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








const infoCidades=`
<section class="info"> 
    <button class="btn-closeinfo">X</button>
    <div class="infoCidades">  
        <header>
            <h1 class="cidade">Cidade 1</h1>
            <div class="info-subtitle">
                <h3>Estado, Pais</h3>
            </div>
        </header>
        <article class="avaliacao">
            <h2>Avaliação</h2>
            <div>
            </div>
        </article>
        <article class="tabela-pontos">
            <h2>Pontos Turisticos</h2>
            <table class="table-pontos table table-striped">
                <theader>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Horarios</th>
                        <th scope="col">Opções</th>
                    </tr>
                </theader>
                <tbody class="pontos-add">
                </tbody>
                <tfooter>
                </tfooter>
            </table>
        </article>
    </div>
</section>
`;

const infoPonto=`

`;
function carregaCidadePosts(event){
    
    let cidade=event.target
    let url=service+"/pontoturistico/cidade/"+$(cidade).attr("id")
    let tableBody=$(".pontos-add")


    $(".cidade").html(cidade.firstElementChild.innerHTML)
    $(".info-subtitle").html(cidade.children[1].innerHTML)
    
    fetch(url)
        .then(resp=>resp.json())
            .then(json=>{
                json.forEach(ponto => {
                    console.log(ponto)
                    tableBody.append(`<tr>
                            <td>${ponto.nome}</td>
                            <td>Abre: ${ponto.abertura},Fecha: ${ponto.fechamento}</td>
                            <td><a href="#">Infomações </a></td>
                        </tr>`)

                })
            })                    
}
//
$(document).on("click",".cidade-item",(e)=>{
    $("body").prepend(infoCidades)
    carregaCidadePosts(e)
})
$(document).on("click",".btn-closeinfo",(e)=>{
    $(".info").remove()
})