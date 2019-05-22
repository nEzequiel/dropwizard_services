let service="http://192.168.0.25:8080"

function getJSON(path){
    let url=service+path
    return new Promise((resolve,reject)=>{
        fetch(url)
            .then(resp=>resp.json())
                .then(json=>{
                    json ? resolve(json): reject()
                })
    })
}

function postJSON(path,dados,painel,message=true){
    let url=service+path
    fetch(url,{
        method:"post",
        body:JSON.stringify(dados),
        headers:{"Content-Type":"application/json"}
    })
        .then(resp=>{
            if(resp.ok){
                if(message)
                  successMessage("Cadastrado com sucesso!!!")
                
                painel()
            } else{
                if(message)
                    errorMessage("Erro ao Cadastrar!!!")
            } 
        })
}

function putJSON(path,dados,id,painel,message){
    let url=service+path+`/${id}`
    fetch(url,{
        method:"put",
        body:JSON.stringify(dados),
        headers:{"Content-Type":"application/json"}
    })
        .then(resp=>{
            if(resp.ok){
                if(message)
                    successMessage("Alterado com sucesso!!!")
                painel()
            } else{
                if(message)
                    errorMessage("Erro ao Alterar!!!")
            } 
        })
}

function deleteItem(path,id,painel,message){
    let url=service+path+`/${id}`
    fetch(url,{
        method:"delete",
    })
        .then(resp=>{
            if(resp.ok){
                if(message)
                    successMessage("Excluido com sucesso!!!")
                painel()
            } else{
                if(message)
                    errorMessage("Erro ao excluir")
            } 
        })
}

function getHTML(path){
    let url=path;
    return new Promise((resolve,reject)=>{
        fetch(url)
            .then(resp=>resp.text())
                .then(html=>{
                    html ? resolve(html): reject()
                })
    })
}

function getFormAsJSON(form){
    let formData=new FormData(form)
    let dadosJSON={};
    for([key,value] of formData){
        if(!isNaN(value)){
            value=parseInt(value)
        }
        if(value=="" || value==0)
            return false
        dadosJSON[key]=value
    }
    return dadosJSON
}


let message=`<section class='info'>
                <div class="info-box box-message">
                    <h1></h1>
                    <p>Escolha uma das ações abaixo</p>
                    <button class="botao-padrao btn-abre-painel">Painel</button>
                </div>      
            </section>
            `

function successMessage(titulo){
    $(".info").remove()
    $("body").prepend(message)
    $(".info-box h1").html(titulo)
}

function errorMessage(titulo){
    $(".info").remove()
    $("body").prepend(message)
    $(".info-box h1").html(titulo)
}

$(document).on("click",".btn-abre-painel",(e)=>{
    $(".info").remove()
})




function atualizaCidadesPesquisa(event){
    $(".search-cidades").html(" ")
    let url;
    let pesquisa=$(".txtpesquisa").val()
    let param=document.querySelector("input[type=radio]:checked").value || "nome"
    
    if(event) event.preventDefault() 
    
    url="/cidade/catalog/0?param="+param+"&value="+pesquisa
    
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

if(location.pathname=="/index.html") {
    atualizaCidadesPesquisa()
}

const formCadastro=`
    <section class="sec-cadastro info">
    <button class="btn-closeinfo">X</button>
        <div class="info-box box-form">
            
        </div>
    </section>
`;

function cadastrarCidade(){
    carregaFormCidade()
            
}

function alterarCidade(e){
    e.preventDefault()
    //a<td<tr
    let itemCidade=e.target.parentNode.parentNode
    let id=parseInt($(itemCidade).attr("id"))
    carregaFormCidade()
    setCidade(id)

}

function setCidade(id){
    getJSON(`/cidade/${id}`)
    .then(cidade=>{
        formCidade.id.value=cidade.id
        formCidade.nome.value=cidade.nome
        formCidade.populacao.value=cidade.populacao
        getPaises()
            .then(()=>{
                formCidade.pais.value=cidade.pais.id
                getEstados()
                    .then(()=>{
                        formCidade.estado.value=cidade.estado.id
                    })
            })
    })
}
function deleteCidade(e){
    e.preventDefault()
    //a<td<tr
    let itemCidade=e.target.parentNode.parentNode
    let id=$(itemCidade).attr("id")
    //mensagem de confirmação de remoção
    deleteItem("/cidade",id,carregaPainelCidades)
}



function carregaFormCidade(){
    getHTML("/admin/cidade.html")
        .then(html=>{
            $("body").prepend(formCadastro)
            $(".info-box").html(html)
        }).then(getPaises)
}

function getPaises(){
    return new Promise((resolve,reject)=>{
        getJSON("/cidade/paises")
            .then(paises=>{
                $(".select-pais").append(`<option value=0>País</option>`)
                paises.forEach(pais=> {
                    $(".select-pais").append(`<option value=${pais.id}>${pais.nome}</option>`)              
                });  
                resolve()                       
            })    
    })
}

function getEstados(){
    let idPais=formCidade.pais.value
    return new Promise((resolve,reject)=>{
        getJSON(`/cidade/estados/${idPais}`)
            .then(estados=>{
                $(".select-estado").html("")
                $(".select-estado").append(`<option value=0>Estado</option>`)
                estados.forEach(estado=> {
                    $(".select-estado").append(`<option value=${estado.id}>${estado.nome}</option>`)
                });  
                resolve()                                     
            })
    })
}


function GravarCidade(e){
    e.preventDefault()
    let form=getFormAsJSON(formCidade)
    let cidadeID=formCidade.id.value
    
    if(form){
        if(cidadeID!=''){
            putJSON("/cidade",form,cidadeID,carregaPainelCidades)    
        }else{
            postJSON("/cidade",form,carregaPainelCidades)
        }
    }else{
        $(".validation").remove()
        $(".box-form").append("<p class='validation'>Todos os campos são de preenchimento obrigatorio!!!</p>")
    }

}

function cadastrarPonto(){
    carregaFormPonto()
}

function alterarPonto(e){
    e.preventDefault()
    let itemPonto=e.target.parentNode.parentNode
    let id=parseInt($(itemPonto).attr("id"))
    carregaFormPonto()
    setPonto(id)
}

function setPonto(id){
    getJSON(`/pontoturistico/${id}`)
        .then(ponto=>{
            formPonto.id.value=ponto.id
            formPonto.nome.value=ponto.nome
            formPonto.rua.value=ponto.rua
            formPonto.numero.value=ponto.numero
            formPonto.bairro.value=ponto.bairro
            formPonto.cep.value=ponto.cep
            formPonto.abertura.value=ponto.abertura
            formPonto.fechamento.value=ponto.fechamento
            getCidades()
                .then(()=>{
                    formPonto.cidade.value=ponto.cidade.id
                })
        })
}
function GravarPonto(e){
    e.preventDefault()
    let form=getFormAsJSON(formPonto)
    let pontoID=formPonto.id.value

    if(form){
        if(pontoID!=''){
            putJSON("/pontoturistico",form,pontoID,carregaPainelPontos)   
        }else{
            postJSON("/pontoturistico",form,carregaPainelPontos)
        }
    }else{
        $(".validation").remove()
        $(".box-form").append("<p class='validation'>Todos os campos são de preenchimento obrigatorio!!!</p>")
    }
}
function deletePonto(e){
    e.preventDefault()
    let itemPonto=e.target.parentNode.parentNode
    let id=$(itemPonto).attr("id")
    //mensagem de confirmação de remoção
    deleteItem("/pontoturistico",id,carregaPainelPontos)
}

function carregaFormPonto(){
    getHTML("/admin/pontoturistico.html")
        .then(html=>{
            $("body").prepend(formCadastro)
            $(".info-box").html(html)
        })
            .then(getCidades)
                .then(()=>{
                    $(".abertura").mask("00:00:00")
                    $(".fechamento").mask("00:00:00")
                    $(".cep").mask("00000-000")
                })
        
}

function getCidades(){
    return new Promise((resolve,reject)=>{
        getJSON('/cidade')
            .then(cidades=>{
                $(".select-cidade").html("")
                $(".select-cidade").append(`<option value=0>Cidade</option>`)
                cidades.forEach(cidade=> {
                    $(".select-cidade").append(`<option value=${cidade.id}>${cidade.nome}</option>`)
                });  
                resolve()                                     
            })
    })
}

$(document).on("click",".btn-add-cidade",cadastrarCidade)
$(document).on("click",".btn-alterar-cidade",alterarCidade)
$(document).on("change",".select-pais",getEstados)
$(document).on("click",".btn-salvar-cidade",GravarCidade)
$(document).on("click",".btn-excluir-cidade",deleteCidade)


$(document).on("click",".btn-add-ponto",cadastrarPonto)
$(document).on("click",".btn-alterar-ponto",alterarPonto)
$(document).on("click",".btn-salvar-ponto",GravarPonto)
$(document).on("click",".btn-excluir-ponto",deletePonto)
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
function carregaPonto(e){
    e.preventDefault()
    $(".tabela-pontos").remove()
    let id=$(e.target).parent().attr("id")
    let url=service+`/pontoturistico/${id}`
    
    fetch(url)
        .then(resp=>resp.json())
            .then(ponto=>{
                $(".info-box").append(`
                    <article class="ponto" id="${ponto.id}">
                        <h2>${ponto.nome}</h2>
                        <div>
                            <h3>Endereço:</h3> 
                            <h5>Rua:</h5><p>${ponto.rua}</p> 
                            <h5>Nº:</h5><p>${ponto.numero}</p> 
                            <h5>Bairro:</h5> <p>${ponto.bairro}</p>
                            <h5>CEP:</h5> <p>${ponto.cep}</p>
                        </div>
                    </article>
                    <article class="comentarios">
                        <form name="formComentario"class="post-comentario">
                            <textarea class="comentario" name="comentario"> </textarea>
                            <div class="botoes-comentarios">
                                <input type="submit" class="botao-padrao enviar-comentario" value="Enviar"/>
                                <input type="reset" class="botao-padrao cancelar" value="Cancelar"/>
                            </div>
                        </form>
                        <div class="list-comentarios">
                        
                        </div>
                    </article>
                `)
            }).then(()=>{
                carregaComentarios()  
            })
}

function carregaComentarios(){
    let id=$(".ponto").attr("id")
    
    $(".list-comentarios").html("")
    getJSON(`/comentario/getAll/${id}`)
        .then(comentarios=>{
                comentarios.forEach(comentario => {
                    $(".list-comentarios").append(`
                        <article class="box-comentario" id="${comentario.id}">
                            <div>
                                <h6>Usuario</h6>
                                <p>${comentario.texto}</p>
                            </div>
                            <a class="btn-del-comentario">X</a>
                        </article>
                    `)
                });
            })
}

function postarComentario(e){
    e.preventDefault()

    let comentario=formComentario.comentario.value
    let usuario=1
    let ponto=$(".ponto").attr("id")
    
    let dados={
        "data":new Date(),
        "texto":comentario,
        "usuario":usuario,
        "ponto":parseInt(ponto)
    }
    postJSON("/comentario",dados,carregaComentarios,false)
    formComentario.comentario.value=""
}

function excluirComentario(e){
    let comentarioId=$(".box-comentario").attr("id")
    deleteItem("/comentario",comentarioId,carregaComentarios,false)
}

$(document).on("click",".btn-del-comentario",excluirComentario)
$(document).on("click", ".enviar-comentario",postarComentario)