var gulp = require('gulp');
javac = require('gulp-javac');
gf_loc = "/usr/local/Cellar/glassfish/4.1.1/libexec/glassfish/domains/domain1/applications/";
web_root = "restful/";

gulp.task('compile', function() {
  return gulp.src('./src/**/*.java')
    .pipe(javac.javac({
      javaVersion: "1.8"
      }).addLibraries('./lib/**/*.jar'))
    .pipe(gulp.dest('out/classes'));
});

gulp.task('web', ['compile'], function(){
  gulp.src("./web/**/*")
    .pipe(gulp.dest(gf_loc + web_root))
});

gulp.task('build', ['web'], function(){
  gulp.src("./out/classes/*.class")
    .pipe(gulp.dest(gf_loc + web_root + "WEB-INF/classes/"))
  return gulp.src("./lib/**/*.jar")
    .pipe(gulp.dest(gf_loc + web_root + "WEB-INF/lib/"))
});


gulp.task('default', function() {
  // place code for your default task here
});