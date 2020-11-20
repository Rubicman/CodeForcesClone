package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserCredentialsRegistration;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsRegistrationValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserCredentialsRegistration.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentialsRegistration registrationForm = (UserCredentialsRegistration) target;
            if (userService.findByLogin(registrationForm.getLogin()) != null) {
                errors.reject("login.login-is-already-used", "login is already used");
            }
            if (!registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation())) {
                errors.reject("passwordConfirmation.passwords-must-be-the-same", "passwords must be the same");
            }
        }
    }
}
