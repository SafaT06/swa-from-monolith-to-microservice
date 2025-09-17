package edu.demo.monolith.service;

import edu.demo.monolith.domain.User;
import edu.demo.monolith.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository users;
    public AuthService(UserRepository users) { this.users = users; }

    public User register(String username, String password) {
        if (users.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        return users.save(User.builder().username(username).password(password).build());
    }

    public String login(String username, String password) {
        var user = users.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        // Demo: return a fake token
        return "demo-" + UUID.randomUUID();
    }
}
