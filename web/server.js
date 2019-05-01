const express=require("express")
const body=require("body-parser")

let app=express()


app.use(express.static("site"))
app.use(body.urlencoded({extended:true}))


app.listen(8888,()=>{
    console.log("work")
})