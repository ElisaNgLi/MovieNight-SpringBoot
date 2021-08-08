package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.UserModel;
import com.example.restapimongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity getUser(){
        var customResponse = new CustomizedResponse("A list of all users", service.getUser());
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserByID(@PathVariable("id") int id){
        CustomizedResponse customResponse = null;
        try {
            customResponse = new CustomizedResponse("User with id " + id, Collections.singletonList(service.getUserById(id)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUser(@Valid @RequestBody UserModel user){
        CustomizedResponse customResponse = null;
        if(user.getEmail() == null || user.getPasword() == null || user.getUsername() == null || user.getFirstName() == null || user.getLastName() == null){
            customResponse = new CustomizedResponse("Missing fields", null);
            return new ResponseEntity(customResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            customResponse = new CustomizedResponse("Create a new user ", Collections.singletonList(service.createUser(user)));
        } catch (Exception e) {
            customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customResponse, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomizedResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var customResponse = new CustomizedResponse(ex.getMessage(), null);
        return customResponse;
    }
}
