package de.sportfest22.service.sportfestservice.repository;

import de.sportfest22.service.sportfestservice.model.Betreuer;
import de.sportfest22.service.sportfestservice.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findById(Integer id);
    Match findMatchByStartIsBefore(Instant timestamp);
    List<Match> findMatchesByStationId(Integer id);
}
