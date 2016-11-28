var gulp = require('gulp');
javac = require('gulp-javac');
 
gulp.task('example', function() {
  return gulp.src('./src/**/*.java')
    .pipe(javac('example.jar')
            .addLibraries('./lib/**/*.jar'))
            //.addLibraries('./external/**/*.jar'))
    .pipe(gulp.dest('out'));
});


gulp.task('default', function() {
  // place code for your default task here
});