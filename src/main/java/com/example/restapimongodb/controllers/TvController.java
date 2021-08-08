package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.Tv;
import com.example.restapimongodb.services.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class TvController {

    @Autowired
    private TvService service;

    @GetMapping("/tv")
    private ResponseEntity getTv(){
        var customResponse = new CustomizedResponse("A list of tv shows", service.getTv());
        return new ResponseEntity(customResponse, HttpStatus.OK);

    }

    @GetMapping("/tv/{id}")
    public ResponseEntity getTvByID(@PathVariable("id") int id){
        CustomizedResponse customResponse = null;
        try {
            customResponse = new CustomizedResponse("Tv Show with id " + id, Collections.singletonList(service.getTvByID(id)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

    @GetMapping("/tv/title")
    public ResponseEntity getTitleTv(@RequestParam(value = "title") String title){
        var customResponse = new CustomizedResponse("A list of tv shows titles", service.getTitleTv(title));
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @GetMapping("/tv/feature")
    public ResponseEntity getFeatureTv(@RequestParam(value = "feature") Boolean feature){
        var customResponse = new CustomizedResponse("A list of feature tv shows", service.getFeatureTv(feature));
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/tv", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addTv(@RequestBody Tv tv){
        service.addTv(tv);
        return new ResponseEntity(tv, HttpStatus.OK);
    }

    @PutMapping(value = "/tv/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateTvShow(@PathVariable("id") int id,@RequestBody Tv newTv)
    {
        CustomizedResponse customResponse = null;
        if(newTv.getFeature() == null || newTv.getTitle() == null || newTv.getBackdrop_path() == null || newTv.getBuy() == 0 || newTv.getGenre() == null || newTv.getRating() == 0 || newTv.getRent() == 0 || newTv.getType() == null || newTv.getPoster_path() == null || newTv.getOverview() == null || newTv.getRelease_date() == null){
            customResponse = new CustomizedResponse("Missing fields", null);
            return new ResponseEntity(customResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            customResponse = new CustomizedResponse("Tv Show with id " + id + " updated", Collections.singletonList(service.updateTv(id, newTv)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse,HttpStatus.OK);

    }

    @DeleteMapping("/tv/{id}")
    public ResponseEntity deleteTvShow(@PathVariable("id") int id){
        CustomizedResponse customResponse = null;

        try {
            customResponse = new CustomizedResponse("Tv Show with id "+ id + " deleted", Collections.singletonList(service.deleteTv(id)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomizedResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var customResponse = new CustomizedResponse(ex.getMessage(), null);
        return customResponse;
    }

}
