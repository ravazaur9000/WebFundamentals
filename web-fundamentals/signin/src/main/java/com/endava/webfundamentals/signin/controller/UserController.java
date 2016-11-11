package com.endava.webfundamentals.signin.controller;

import com.endava.webfundamentals.signin.dto.UserCredentialDTO;
import com.endava.webfundamentals.signin.dto.UserRegisterDTO;
import com.endava.webfundamentals.signin.model.User;
import com.endava.webfundamentals.signin.repository.UserRepository;
import com.endava.webfundamentals.signin.service.AuthenticationService;
import com.endava.webfundamentals.signin.validator.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Iterator;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * Created by fstancu on 7/22/2016.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegisterValidator userRegisterValidator;

    @Resource(name = "${spring.bean.authentication}")
    private AuthenticationService authenticationService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute UserRegisterDTO userRegisterDTO,
                             BindingResult result, Model model) {
        System.out.println(userRegisterDTO);

        userRegisterValidator.validate(userRegisterDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("message", result.getFieldError().getDefaultMessage());
            return "register";
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        userRepository.save(user);

        return "register";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Object doLogin(@ModelAttribute UserCredentialDTO userCredentialDTO, Model model,
                          HttpServletRequest request, HttpServletResponse response) {
        if (authenticationService.login(userCredentialDTO, request, response)) {
            return new RedirectView("/dashboard");
        } else {
            model.addAttribute("message", "Wrong user or password!");
            response.setStatus(SC_UNAUTHORIZED);
        }
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return new RedirectView("/");
    }

}
