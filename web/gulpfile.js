var gulp=require("gulp")
var sass=require("gulp-sass")
var concat=require("gulp-concat")

var minify=require("gulp-cssmin")
var browserSync=require("browser-sync").create()

require("./tasks/app")
require("./tasks/servidor")


gulp.task("bs",(done)=>{
    browserSync.init({
        port:3010,
        proxy:'http://localhost:8888'
    })
    gulp.watch("./src/assets/scss/*.scss",gulp.series("css","img"))
    gulp.watch("./src/assets/js/*.js",gulp.series("js"))
    gulp.watch("./src/assets/imgs/*.*",gulp.series("img"))
    gulp.watch("./src/**/*.html",gulp.series("html"))
})

gulp.task("default",gulp.series("serv"))

gulp.task("build",gulp.series("html","js","css","img"))
