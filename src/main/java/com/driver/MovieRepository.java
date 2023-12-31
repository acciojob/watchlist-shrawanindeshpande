package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    List<Movie> movieList=new ArrayList<>();
    List<Director> directorList=new ArrayList<>();
    Map<Director,List<Movie>> movieDB=new HashMap<>();

    public void addMovieToDB(Movie movie){
        movieList.add(movie);
        return;
    }
    public void addDirectorToDB(Director director){
        directorList.add(director);
        return;
    }
    public boolean addMovieDirectorPairToDB(String movieName,String directorName){
        Movie movieToAdd=null;
        Director directorToAdd = null;
        for(Movie movie:movieList){
            if(movieName.equals(movie.getName())){
                movieToAdd=movie;
                break;
            }
        }
        for(Director director:directorList){
            if(directorName.equals(director.getName())){
                directorToAdd=director;
                break;
            }
        }
        if(directorToAdd==null || movieToAdd==null){
            return false;
        }
        for(Director director:movieDB.keySet()){
            if(director==directorToAdd){
                movieDB.get(director).add(movieToAdd);
            }
            else{
                movieDB.put(directorToAdd,new ArrayList<Movie>());
                movieDB.get(directorToAdd).add(movieToAdd);
            }
        }
        return true;

    }
    public Movie getMovieFromDB(String name){
        for(Movie movie:movieList){
            if(name.equals(movie.getName())){
                return movie;
            }
        }
        return null;
    }
    public Director getDirectorFromDB(String name){
        for(Director director:directorList){
            if(name.equals(director.getName())){
                return director;
            }
        }
        return null;
    }
    public List<Movie> getMovieListByDirector(String name){
        for(Director director:movieDB.keySet()){
            if(name.equals(director.getName())){
                return movieDB.get(director);
            }
        }
        return null;
    }
    public List<Movie> getAllMovies(){
        return movieList;
    }
    public void deleteMovieByDirectorFromDB(String name){
        for(Director director:movieDB.keySet()){
            if(name.equals(director.getName())){
                for(Movie movie:movieDB.get(director)){
                    movieList.remove(movie);
                }
                movieDB.remove(director);
            }
        }
        return;
    }
    public void deleteALlDirectorsAndMovies(){
        for(Director director:directorList){
            deleteMovieByDirectorFromDB(director.getName());
            directorList.remove(director);
        }
    }
}
