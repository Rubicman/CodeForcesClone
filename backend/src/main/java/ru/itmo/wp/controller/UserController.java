package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserCredentialsRegistration;
import ru.itmo.wp.form.validator.UserCredentialsRegistrationValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController extends ApiController {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserCredentialsRegistrationValidator userCredentialsRegistrationValidator;

    public UserController(UserService userService, JwtService jwtService, UserCredentialsRegistrationValidator userCredentialsRegistrationValidator) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userCredentialsRegistrationValidator = userCredentialsRegistrationValidator;
    }

    @InitBinder("userCredentialsRegistration")
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegistrationValidator);
    }

    @GetMapping("users/authorized")
    public User findAuthorized(User user) {
        return user;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("users/registration")
    public String registration(@RequestBody @Valid UserCredentialsRegistration userCredentialsRegistration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        userService.register(userCredentialsRegistration);
        return jwtService.create(userCredentialsRegistration.getLogin(), userCredentialsRegistration.getPassword());
    }
}
