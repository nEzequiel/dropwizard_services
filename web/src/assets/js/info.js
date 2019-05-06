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