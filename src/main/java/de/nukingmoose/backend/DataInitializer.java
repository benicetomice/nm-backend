package de.nukingmoose.backend;

import de.nukingmoose.backend.model.AppUser;
import de.nukingmoose.backend.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    public DataInitializer(AppUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (repo.findByUsername("admin").isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername("admin");
            user.setPassword(encoder.encode("password"));
            repo.save(user);
        }
    }
}
