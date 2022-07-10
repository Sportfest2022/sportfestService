package de.sportfest22.service.sportfestservice.service;

import de.sportfest22.service.sportfestservice.model.Betreuer;
import de.sportfest22.service.sportfestservice.model.Match;
import de.sportfest22.service.sportfestservice.model.Station;
import de.sportfest22.service.sportfestservice.repository.MatchRepository;
import de.sportfest22.service.sportfestservice.repository.Type1ResultRepository;
import de.sportfest22.service.sportfestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class MatchService {

    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private Type1ResultRepository type1ResultRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, UserRepository userRepository, Type1ResultRepository type1ResultRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.type1ResultRepository = type1ResultRepository;
    }

    public List<Match> getMatchesByUsername(String username) {
        Betreuer betreuer = this.userRepository.findBetreuerByNutzername(username);
        if (betreuer == null) return null;
        Station station = betreuer.getStation();
        if (station == null) return null;
        return this.matchRepository.findMatchesByStationId(station.getId());
    }

    public String getMatchResultStatus(Integer matchId) {
        // Possible States: TBD (GREY); RUNNING (LIGHT GREEN); DELAYED (ORANGE); DONE (DARKER GREEN)

        // If planned match start -> RUNNING
        Match match = this.matchRepository.findById(matchId);
        Instant officialStartDate = match.getStart();
        Integer duration = match.getDuration();

        // TODO: Check for 2nd matchType
        Boolean resultTurnedIn = this.type1ResultRepository.findById(matchId) != null;


        // Match has started more than duration + 5 minutes ago
        // Current time - duration + 5 minutes represents
        if (officialStartDate.isBefore(Instant.now()) &&
                officialStartDate.isBefore(
                        Instant.now().minus(
                                duration + 5, ChronoUnit.MINUTES))) {
            return "DELAYED";
        }


        if (officialStartDate.isBefore(Instant.now())) {
            return "RUNNING";
        }

        // Result was sent -> DONE
        if (resultTurnedIn) {
            return "DONE";
        }

        // Match has not officially started
        if (officialStartDate.isAfter(Instant.now())) {
            return "TBD";
        }

        return "TBD";
    }

    public String getResponsiveUserName(Integer matchId) {
        Match match = this.matchRepository.findById(matchId);
        List<Betreuer> betreuersByStationId = this.userRepository.findBetreuersByStationId(match.getStation().getId());

        StringBuilder result = new StringBuilder();
        for (Betreuer betreuer : betreuersByStationId) {
            result.append(betreuer.getName());
            result.append(" ");
            result.append(betreuer.getNachname());
            result.append(", ");
        }

        return result.toString();
    }
}
