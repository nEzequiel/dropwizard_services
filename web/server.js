const express=require("express")
const body=require("body-parser")
const port=8888
const host="localhost"

let app=express()


app.use(express.static("site"))
app.use(body.urlencoded({extended:true}))


app.listen(host,port,()=>{
    console.log(`WebServer working!!! ${host}:${port}`)
})
