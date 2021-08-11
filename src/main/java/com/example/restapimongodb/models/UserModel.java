package com.example.restapimongodb.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserModel {

    @Id
    private String id;

    @NotBlank(message = "Enter a last name")
    private String lastName;

    @NotBlank(message = "Enter a first name")
    private String firstName;

    @Email
    @NotBlank(message = "Enter an email")
    private String email;

    @NotBlank(message = "Enter a username")
    private String username;

    @NotBlank()
    @Min(8)
    private String pasword;

    private String role;

    public UserModel(){}

    public UserModel(String id, String lastName, String firstName, String email, String username, String pasword) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.pasword = pasword;
        this.role = "user";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = "user";
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", pasword='" + pasword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
