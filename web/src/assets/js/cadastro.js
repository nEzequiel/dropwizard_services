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
    
    if(cidadeID!=''){
        putJSON("/cidade",form,cidadeID,carregaPainelCidades)    
    }else{
        postJSON("/cidade",form,carregaPainelCidades)
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
    
    if(pontoID!=''){
        putJSON("/pontoturistico",form,pontoID,carregaPainelPontos)  
            .then(carregaPainelPontos)  
    }else{
        postJSON("/pontoturistico",form,carregaPainelPontos)
            .then(carregaPainelPontos)  
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