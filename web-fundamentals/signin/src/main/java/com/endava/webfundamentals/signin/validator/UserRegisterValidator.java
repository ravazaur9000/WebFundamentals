package com.endava.webfundamentals.signin.validator;

import com.endava.webfundamentals.signin.dto.UserRegisterDTO;
import com.endava.webfundamentals.signin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by fstancu on 8/1/2016.
 */

@Component
public class UserRegisterValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserRegisterDTO userRegisterDTO = (UserRegisterDTO) object;
        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            errors.rejectValue("username", "",userRegisterDTO.getUsername() + " e folosit");
        }

        if (userRegisterDTO.getPassword() != null &&
                !userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword())) {
            errors.rejectValue("password", "Parolele nu coincid");
        }
    }
}
