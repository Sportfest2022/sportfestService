package de.sportfest22.service.sportfestservice.repository;

import de.sportfest22.service.sportfestservice.model.Klasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Klasse, Long> {
    List<Klasse> findAllByKlassenstufeId(Integer klassenstufeId);
}
