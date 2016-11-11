package com.endava.webfundamentals.signin.controller;

import com.endava.webfundamentals.signin.model.User;
import com.endava.webfundamentals.signin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fstancu on 8/1/2016.
 */

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
