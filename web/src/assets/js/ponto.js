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
                        <h3>Endereço: ${ponto.rua}, ${ponto.numero}, ${ponto.bairro},${ponto.cep}</h3>
                        
                    </article>
                    <article class="comentarios">
                        <form name="formComentario"class="post-comentario">
                            <textarea class="comentario" name="comentario" placeholder="Deixe seu comentário..."> </textarea>
                            <div class="botoes-comentarios">
                                <input type="submit" class="enviar-comentario" value="Enviar"/>
                                <input type="reset" class="cancelar" value="Cancelar"/>
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
                            <h6>Usuario</h6>
                            <p>${comentario.texto}</p>
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
    postJSON("/comentario",dados)
        .then(()=>{
            carregaComentarios()
        })   
}


$(document).on("click", ".enviar-comentario",postarComentario)