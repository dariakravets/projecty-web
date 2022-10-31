package com.example.projectyweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.projectyweb.ResourceNotFoundException;
import com.example.projectyweb.UserAlreadyExistException;
import com.example.projectyweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.projectyweb.models.User;
import com.example.projectyweb.models.Project;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8084")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String email) {
        List<User> users = new ArrayList<User>();

        if (email == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByEmail(email).forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getPosition()));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }*/

    @GetMapping("/users/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam("fName") String fName, @RequestParam("lName") String lName, @RequestParam("position") String position, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("matchpassword") String matchpassword) {
        User _user = userRepository.save(new User(fName, lName, position, email, password, matchpassword));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setPosition(user.getPosition());

        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    /*public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid User user,
                                            HttpServletRequest request, Errors errors) {
        try {
            User registered = userService.registerNewUserAccount(user);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("successRegister", "user", user);
    }*/

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