package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService serObj;

    @PostMapping("/add-movie")//to add movie in database
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String response=serObj.addMovieLogic(movie);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
    @PostMapping("/add-director")//to add director in database
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        String response=serObj.addDirectorLogic(director);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
    @PutMapping("/add-movie-director-pair")//to add movies and director as a pair
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName")String movieName,
                                                       @RequestParam("directorName") String directorName){
        String response=serObj.addMovieDirectorPairLogic(movieName, directorName);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movie-by-name/{name}")//to get movie when name of the movie is provided
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name")String name){
        Movie movie=serObj.getMovieFromDBLogic(name);
        return new ResponseEntity<>(movie,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-director-by-name/{name}")//to get director name of the director is provided
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name")String name){
        Director director=serObj.getDirectorFromDBLogic(name);
        return new ResponseEntity<>(director,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-movies-by-director-name/{director}")//get list of movies by particular director
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director")String name){
        List<String> movieList=serObj.getMovieListByDirectorLogic(name);
        return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-all-movies")//to get list of movies
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movieList=serObj.getAllMoviesLogic();
        return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String>  deleteDirectorByName(@RequestParam("name")String name){
       String response=serObj.deleteMoviesByDirector(name);
       return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String>  deleteAllDirectors(){
        String response=serObj.deleteAllDirectorsAndMovies();
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }



}
