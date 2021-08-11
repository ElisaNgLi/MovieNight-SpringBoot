package com.example.restapimongodb.services;

import com.example.restapimongodb.models.UserModel;
import com.example.restapimongodb.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<UserModel> getUser(){
        return repository.findAll();
    }

    public Optional<UserModel> getUserById(String id) throws Exception{
        Optional<UserModel> user = repository.findById(id);
        if(!user.isPresent()){
            throw new Exception("User " + id + " not found");
        }
        return user;
    }

    public Optional<UserModel> getUserByUsername(String username)
    {
        Optional<UserModel> findName = repository.findByUsername(username);
        return findName;
    }

    public UserModel createUser(UserModel user){
        String encodedPw = encoder.encode(user.getPasword());
        user.setPasword(encodedPw);
        UserModel createUser = repository.insert(user);
        return createUser;
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Optional<UserModel> findUser =repository.findByUsername(user);
        if(!findUser.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        }
        String userName = findUser.get().getUsername();
        String pw = findUser.get().getPasword();

        return new User(userName, pw, new ArrayList<>());
    }
}
