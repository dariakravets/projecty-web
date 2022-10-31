package com.example.projectyweb.registration;

import com.example.projectyweb.models.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public interface IUserService {
    SecurityProperties.User registerNewUserAccount(User userDto);
}