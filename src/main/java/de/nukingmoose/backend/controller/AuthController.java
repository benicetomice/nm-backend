package de.nukingmoose.backend.controller;

import de.nukingmoose.backend.config.AuthProperties;
import de.nukingmoose.backend.dto.LoginRequest;
import de.nukingmoose.backend.dto.LoginResponse;
import de.nukingmoose.backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final JwtService jwtService;
    private final AuthProperties authProps;

    public AuthController(JwtService jwtService, AuthProperties authProps) {
        this.jwtService = jwtService;
        this.authProps = authProps;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {

        if (!authProps.getUsername().equals(login.getUsername()) ||
                !authProps.getPassword().equals(login.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(login.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}