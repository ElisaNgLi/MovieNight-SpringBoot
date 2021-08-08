package com.example.restapimongodb.services;

import com.example.restapimongodb.models.Movie;
import com.example.restapimongodb.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getMovies(){
        return repository.findAll();
    }

    public Optional<Movie> getMovieByID(int id) throws Exception {
        Optional<Movie> movie = repository.findById(id);
        if(!movie.isPresent()){
            throw new Exception("Movie " + id + " not found");
        }
        return movie;
    }

    public List<Movie> getTitleMovies(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getFeatureMovies(Boolean feature){

        Query query = new Query();
        query.addCriteria(Criteria.where("feature").is(true));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public void addMovies(Movie movie){
        repository.insert(movie);
    }

    public Movie updateMovie(int id, Movie newMovie) throws Exception {
        Optional<Movie> movie = repository.findById(id);

        if(!movie.isPresent()){
            throw new Exception("Movie " + id + " not found");
        }

        movie.get().setBuy(newMovie.getBuy());
        movie.get().setBackdrop_path(newMovie.getBackdrop_path());
        movie.get().setFeature(newMovie.getFeature());
        movie.get().setGenre(newMovie.getGenre());
        movie.get().setOverview(newMovie.getOverview());
        movie.get().setPoster_path(newMovie.getPoster_path());
        movie.get().setRating(newMovie.getRating());
        movie.get().setRelease_date(newMovie.getRelease_date());
        movie.get().setTitle(newMovie.getTitle());
        movie.get().setType(newMovie.getType());
        movie.get().setRent(newMovie.getRent());

        Movie updatedMovie = repository.save(movie.get());
        return updatedMovie;
    }

    public Optional<Movie> deleteMovie(int id) throws Exception{
        Optional<Movie> movie = repository.findById(id);
        if(!movie.isPresent()){
            throw new Exception("Movie " + id + " not found");
        }

        repository.deleteById(id);
        return movie;
    }
}
