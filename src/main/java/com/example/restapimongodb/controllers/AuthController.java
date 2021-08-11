package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.UserModel;
import com.example.restapimongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody UserModel user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPasword()));
            var customResponse = new CustomizedResponse("login success ", Collections.singletonList(service.getUserByUsername(user.getUsername())));
            return new ResponseEntity(customResponse, HttpStatus.OK);
        }catch(BadCredentialsException ex){
            var customResponse = new CustomizedResponse("Wrong username or password", null);
            return new ResponseEntity(customResponse, HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            var customResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customResponse, HttpStatus.NOT_FOUND);
        }

    }
}
