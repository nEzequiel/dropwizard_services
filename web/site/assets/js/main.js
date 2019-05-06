
let service="http://localhost:8080"


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

    $(".infoCidades").attr("id",$(cidade).attr("id"))
    $(".cidade").html(cidade.firstElementChild.innerHTML)
    $(".info-subtitle").html(cidade.children[1].innerHTML)
    
    fetch(url)
        .then(resp=>resp.json())
            .then(json=>{
                json.forEach(ponto => {
                    let abertura=new Date("T"+ponto.abertura)
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
$(document).on("click",".cidade-item",(e)=>{
    $("body").prepend(infoCidades)
    carregaCidadePosts(e)
})
$(document).on("click",".btn-closeinfo",(e)=>{
    $(".info").remove()
})
$(document).on("click",".informacao",carregaPonto)
function carregaPonto(e){
    e.preventDefault()
    $(".tabela-pontos").remove()
    let elem=$(e.target).parent().parent()
    let id=$(e.target).parent().attr("id")
    let url=service+`/pontoturistico/${id}`
    
    fetch(url)
        .then(resp=>resp.json())
            .then(ponto=>{
                $(".infoCidades").append(`
                    <article class="ponto "id="${ponto.id}">
                        <h2>${ponto.nome}</h2>
                        <h3>Endereço: ${ponto.rua}, ${ponto.numero}, ${ponto.bairro},${ponto.cep}</h3>
                        
                    </article>
                    <article class="comentarios">
                        <form class="post-comentario">
                            <textarea class="comentario" name="comentario" placeholder="Deixe seu comentário..."> </textarea>
                            <div class="botoes-comentarios">
                                <input type="submit" class="enviar" value="Enviar"/>
                                <input type="reset" class="cancelar" value="Cancelar"/>
                            </div>
                        </form>
                        <div class="list-comentarios">
                        
                        </div>
                    </article>
                `)
            })
    url=service+`/comentario/getAll/${id}`
    
    fetch(url)
        .then(resp=>resp.json())
            .then(comentarios=>{
                comentarios.forEach(comentario => {
                    $(".list-comentarios").append(`
                        <article class="box-comentario" id="${comentario.id}">
                            <h6>Usuario</h6>
                            <p>${comentario.texto}</p>
                        </article>
                    `)
                });
            })
                
}


function postarComentario(e){
    let url=service+`/comentario`
    e.preventDefault()
    let comentario=$(".comentario").val()
    let usuario=1
    let ponto=$(".ponto").attr("id")
    let dataPost=new Date()
    
    var data=JSON.stringify({
        "data":dataPost,
        "texto":comentario,
        "usuario":usuario,
        "ponto":parseInt(ponto)
    })
        
    fetch(url,{
        method:"post",
        body:data,
        headers:{'Content-Type': 'application/json'}
    }).then(resp=>{
        if(resp.ok){
            console.log("Comentario feito com sucesso")
        }
    })
        
    
}


$(document).on("click", ".enviar",postarComentario)