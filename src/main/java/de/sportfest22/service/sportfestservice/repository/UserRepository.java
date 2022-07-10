package de.sportfest22.service.sportfestservice.repository;

import de.sportfest22.service.sportfestservice.model.Betreuer;
import de.sportfest22.service.sportfestservice.model.Klasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Betreuer, Long> {
    boolean existsBetreuerByNutzernameAndPasswort(String nutzername, String passwort);
    Betreuer findBetreuerByNutzernameAndPasswort(String nutzername, String passwort);
    Betreuer findBetreuerByNutzername(String username);
    List<Betreuer> findBetreuersByStationId(Integer stationId);
}
