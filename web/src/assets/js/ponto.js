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