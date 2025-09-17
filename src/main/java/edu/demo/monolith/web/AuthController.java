package edu.demo.monolith.web;

import edu.demo.monolith.service.AuthService;
import edu.demo.monolith.web.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;
    public AuthController(AuthService auth) { this.auth = auth; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        var u = auth.register(req.username(), req.password());
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        String token = auth.login(req.username(), req.password());
        Long userId = authUserId(req.username());
        return ResponseEntity.ok(new LoginResponse(token, userId));
    }

    private Long authUserId(String username) {
        // demo helper: in real app use repository
        return 1L;
    }
}
