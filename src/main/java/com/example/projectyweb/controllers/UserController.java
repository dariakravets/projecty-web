package com.example.projectyweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.projectyweb.ResourceNotFoundException;
import com.example.projectyweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectyweb.models.User;
import com.example.projectyweb.models.Project;

import javax.persistence.criteria.CriteriaBuilder;

@CrossOrigin(origins = "http://localhost:8084")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        List<User> users = new ArrayList<User>();

        if (name == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByName(name).forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getPosition()));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }*/

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam String fName, @RequestParam String lName, @RequestParam String position, @RequestParam String password, @RequestParam String matchpassword, @RequestParam String email) {
        User _user = userRepository.save(new User(fName, lName, position, password, email));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setPosition(user.getPosition());

        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@GetMapping("/user/position")
    public ResponseEntity<List<User>> findByPosition(@PathVariable("position") String position) {
        List<User> users = userRepository.findByPosition(position);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }*/

}