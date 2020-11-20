package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserCredentialsRegistration;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void register(UserCredentialsRegistration registerForm) {
        User user = new User();
        user.setLogin(registerForm.getLogin());
        user.setName(registerForm.getName());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerForm.getLogin(), registerForm.getPassword());
    }

    public void createPost(User user, Post post) {
        post.setId(0);
        user = userRepository.findByLogin(user.getLogin());
        user.addPost(post);
        userRepository.save(user);
    }
}
