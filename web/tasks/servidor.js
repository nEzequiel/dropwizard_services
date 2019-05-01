const gulp=require("gulp")

const webserver=require("gulp-webserver")


//--- Assistir os arquivos, aguardar por mudanças e 'recompila-los'.
gulp.task("monitora",gulp.series((fim)=>{
    gulp.watch("./src/assets/scss/*.scss",gulp.series("css","img"))
    gulp.watch("./src/assets/js/*.js",gulp.series("js"))
    gulp.watch("./src/assets/imgs/*.*",gulp.series("img"))
    gulp.watch("./src/**/*.html",gulp.series("html"))
    fim()
}))

//--- Iniciar o servidor de desenvolvimento.
gulp.task("serv",gulp.series("monitora",(fim)=>{
    gulp.src("site")
        .pipe(webserver({livereload:true,port:5555,open:true}))
    fim()
}))
