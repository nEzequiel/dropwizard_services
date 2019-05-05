var gulp=require("gulp")
var sass=require("gulp-sass")
var concat=require("gulp-concat")

var minify=require("gulp-cssmin")

gulp.task("css",()=>{
    return gulp.src(["./src/assets/scss/index.scss"])
        .pipe(sass().on('error', sass.logError))
        .pipe(concat("style.css"))
        .pipe(gulp.dest("./site/assets/css"))

})

gulp.task("js",()=>{
    return gulp.src(["./src/assets/js/*.js"])
        .pipe(concat("main.js"))
        .pipe(gulp.dest("./site/assets/js"))

})


gulp.task("html",()=>{
    return gulp.src(["./src/**/*.html"])
        .pipe(gulp.dest("./site"))

})

gulp.task("img",()=>{
    return gulp.src(["./src/assets/img/*.*"])
        .pipe(gulp.dest("./site/assets/img"))

})

