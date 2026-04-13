package de.nukingmoose.backend.service;

import de.nukingmoose.backend.entity.Gig;
import de.nukingmoose.backend.repository.GigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GigService {

    private final GigRepository repo;

    public GigService(GigRepository repo) {
        this.repo = repo;
    }

    public List<Gig> getAll() {
        return repo.findAll();
    }

    public Gig getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Gig create(Gig gig) {
        return repo.save(gig);
    }

    public Gig update(Long id, Gig updated) {
        Gig gig = getById(id);

        gig.setTitle(updated.getTitle());
        gig.setDescription(updated.getDescription());
        gig.setLocation(updated.getLocation());
        gig.setDate(updated.getDate());
        gig.setImageUrl(updated.getImageUrl());

        return repo.save(gig);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
