package de.nukingmoose.backend.controller;

import de.nukingmoose.backend.dto.LoginRequest;
import de.nukingmoose.backend.dto.RegisterRequest;
import de.nukingmoose.backend.model.AppUser;
import de.nukingmoose.backend.repository.AppUserRepository;
import de.nukingmoose.backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public SecurityController(AppUserRepository repo, PasswordEncoder encoder, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        if (repo.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.status(400).body("User already exists");
        }

        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword())); // 🔒 Hash

        repo.save(user);

        return ResponseEntity.ok("User created");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {

        AppUser user = repo.findByUsername(login.getUsername())
                .orElseThrow();

        if (!encoder.matches(login.getPassword(), user.getPassword())) {

            System.out.println("INPUT PASSWORD: [" + login.getPassword() + "]");
            System.out.println("DB PASSWORD: [" + user.getPassword() + "]");
            System.out.println("MATCH: " + encoder.matches(login.getPassword(), user.getPassword()));

            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
}