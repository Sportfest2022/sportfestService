package de.sportfest22.service.sportfestservice.repository;


import de.sportfest22.service.sportfestservice.model.Klasse;
import de.sportfest22.service.sportfestservice.model.MatchTyp1ergebni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Type1ResultRepository extends JpaRepository<MatchTyp1ergebni, Long> {
    MatchTyp1ergebni findById(Integer id);
}
