const express=require("express")
const body=require("body-parser")
const port=8000
const host="localhost"

let app=express()


app.use(express.static("site"))
app.use(body.urlencoded({extended:true}))


app.listen(port,()=>{
    console.log(`WebServer working!!! ${host}:${port}`)
})
