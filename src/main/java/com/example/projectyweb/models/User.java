package com.example.projectyweb.models;
import com.example.projectyweb.ValidEmail;
import com.example.projectyweb.registration.PasswordMatches;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@PasswordMatches
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "position")
    private String position;

    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;
    @Column(name = "matchingPassword")
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;
    public User() { }
    public User(String firstName, String lastName, String position, String email, String password, String matchingPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.password = password;
        this.email = email;
        this.matchingPassword = matchingPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}