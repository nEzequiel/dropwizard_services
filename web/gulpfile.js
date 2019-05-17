var gulp=require("gulp")
var sass=require("gulp-sass")
var concat=require("gulp-concat")

var minify=require("gulp-cssmin")
var browserSync=require("browser-sync").create()

require("./tasks/app")
require("./tasks/servidor")



gulp.task("default",gulp.series("serv"))

gulp.task("build",gulp.series("html","js","css","img"))
