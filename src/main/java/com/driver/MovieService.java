package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repoObj;
    public String addMovieLogic(Movie movie){
        repoObj.addMovieToDB(movie);
        return "New movie is added successfully!!";
    }
    public String addDirectorLogic(Director director){
        repoObj.addDirectorToDB(director);
        return "New director is added successfully!!";
    }
    public String addMovieDirectorPairLogic(String movieName,String directorName){
        boolean result=repoObj.addMovieDirectorPairToDB(movieName,directorName);
        if(result){
            return "Movie and director pair is added into database Sucessfully!!!";
        }
        return "Movie or director does not exist in data base";
    }
    public Movie getMovieFromDBLogic(String name){
        return repoObj.getMovieFromDB(name);
    }
    public Director getDirectorFromDBLogic(String name){
        return repoObj.getDirectorFromDB(name);
    }
    public List<String> getMovieListByDirectorLogic(String name){
        List<Movie> movieList=repoObj.getMovieListByDirector(name);
        List<String> movieName=new ArrayList<>();
        for(Movie movie:movieList){
            movieName.add(movie.getName());
        }
        return movieName;
    }
    public List<String> getAllMoviesLogic(){
        List<Movie> movieList=repoObj.getAllMovies();
        List<String> movieName=new ArrayList<>();
        for(Movie movie:movieList){
            movieName.add(movie.getName());
        }
        return movieName;
    }
    public String deleteMoviesByDirector(String name){
        repoObj.deleteMovieByDirectorFromDB(name);
        return "Movies by "+name+" are deleted successfully!!";
    }
    public String deleteAllDirectorsAndMovies(){
        repoObj.deleteALlDirectorsAndMovies();
        return "All Directors are deleted successfully!!";
    }

}
