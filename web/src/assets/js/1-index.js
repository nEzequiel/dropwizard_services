let service="http://localhost:8080"

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
