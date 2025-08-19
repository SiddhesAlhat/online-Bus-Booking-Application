package com.example.demo.Service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public User save(User user) { return repo.save(user); }
    public User findByUsername(String username) { return repo.findByUsername(username); }
}
