package de.nukingmoose.backend.repository;

import de.nukingmoose.backend.entity.Gig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GigRepository extends JpaRepository<Gig, Long> {
}
