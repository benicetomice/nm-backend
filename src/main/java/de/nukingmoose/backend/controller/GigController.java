package de.nukingmoose.backend.controller;

import de.nukingmoose.backend.entity.Gig;
import de.nukingmoose.backend.service.GigService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gigs")
@CrossOrigin(origins = "http://localhost:4200")
public class GigController {

    private final GigService service;

    public GigController(GigService service) {
        this.service = service;
    }

    @GetMapping
    public List<Gig> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Gig getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Gig create(@RequestBody Gig gig) {
        return service.create(gig);
    }

    @PutMapping("/{id}")
    public Gig update(@PathVariable Long id, @RequestBody Gig gig) {
        return service.update(id, gig);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
