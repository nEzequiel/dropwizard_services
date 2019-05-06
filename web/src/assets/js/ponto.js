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