package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.Movie;
import com.example.restapimongodb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping("/movies")
    public ResponseEntity getMovies(){
        var customResponse = new CustomizedResponse("A list of movies", service.getMovies());
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMoviesByID(@PathVariable("id") int id){
        CustomizedResponse customResponse = null;
        try {
            customResponse = new CustomizedResponse("Movie with id " + id, Collections.singletonList(service.getMovieByID(id)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

    @GetMapping("/movies/title")
    public ResponseEntity getTitleMovies(@RequestParam(value = "title") String title){
        var customResponse = new CustomizedResponse("A list movies titles", service.getTitleMovies(title));
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/feature")
    public ResponseEntity getFeatureMovies(@RequestParam(value = "feature") Boolean feature){
        var customResponse = new CustomizedResponse("A list feature movies", service.getFeatureMovies(feature));
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
        service.addMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PutMapping(value = "/movies/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateMovie(@PathVariable("id") int id, @RequestBody Movie newMovie) {
        CustomizedResponse customResponse = null;
        try {
            customResponse = new CustomizedResponse("Update a  movie with ID " + id, Collections.singletonList(service.updateMovie(id, newMovie)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse, HttpStatus.OK);


    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteMovie(@PathVariable("id") int id){
        CustomizedResponse customResponse = null;

        try {
            customResponse = new CustomizedResponse("Movie with id "+ id, Collections.singletonList(service.deleteMovie(id)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
