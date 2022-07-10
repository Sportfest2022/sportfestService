package de.sportfest22.service.sportfestservice.controller;

import de.sportfest22.service.sportfestservice.dto.MatchDto;
import de.sportfest22.service.sportfestservice.model.MatchTyp1ergebni;
import de.sportfest22.service.sportfestservice.repository.ClassRepository;
import de.sportfest22.service.sportfestservice.repository.MatchRepository;
import de.sportfest22.service.sportfestservice.repository.Type1ResultRepository;
import de.sportfest22.service.sportfestservice.repository.UserRepository;
import de.sportfest22.service.sportfestservice.service.MatchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sportfest/match")
@CrossOrigin("*")
public class MatchController {

    private final MatchRepository matchRepository;
    private final MatchService matchService;

    private final Type1ResultRepository type1ResultRepository;

    private final ClassRepository classRepository;

    private final UserRepository userRepository;

    @Autowired
    public MatchController(MatchRepository matchRepository,
                           MatchService matchService,
                           Type1ResultRepository type1ResultRepository,
                           ClassRepository classRepository,
                           UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.matchService = matchService;
        this.type1ResultRepository = type1ResultRepository;
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("{username}")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public List<MatchDto> getAll(@PathVariable String username) {

        /*LocalDateTime now = LocalDateTime.now();
        now = now.plusMinutes(30);

        return matchRepository.findMatchByStartIsBefore(Timestamp.valueOf(now).toInstant());*/

        //return matchService.getMatchesByUsername(username);

        List<MatchDto> convertedMatches = new ArrayList<>();

        matchService.getMatchesByUsername(username).forEach(match -> {
            convertedMatches.add(new MatchDto(
                    match.getId(),
                    match.getKlasse1(),
                    match.getKlasse2(),
                    match.getDuration(),
                    match.get_public(),
                    match.getStart(),
                    match.getStation(),
                    matchService.getMatchResultStatus(match.getId()),
                    matchService.getResponsiveUserName(match.getId())
            ));
        });

        return convertedMatches;
    }

    // Necessary:
    // --- Default ---
    // GET  Next match user specific being MAX 15min away
    // POST Result type1
    // POST Result type2
    // POST Result type3
    // --- Admin ---
    // GET Last committed results (ADMIN)
    // GET Next matches of same start date (ADMIN)


    @GetMapping("")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public List<MatchDto> getAll() {
        List<MatchDto> convertedMatches = new ArrayList<>();
        matchRepository.findAll().forEach(match -> {
            convertedMatches.add(new MatchDto(
                    match.getId(),
                    match.getKlasse1(),
                    match.getKlasse2(),
                    match.getDuration(),
                    match.get_public(),
                    match.getStart(),
                    match.getStation(),
                    matchService.getMatchResultStatus(match.getId()),
                    matchService.getResponsiveUserName(match.getId())
            ));
        });
        return convertedMatches;
    }

    @GetMapping("/result/type1/{userName}/{matchId}/{winningTeamname}")
    public boolean saveType1Result(
            @PathVariable String userName,
            @PathVariable Integer matchId,
            @PathVariable String winningTeamname) {

        // TODO: Alles mal validieren
        // TODO: Doppelte Einträge und so vermeiden

        type1ResultRepository.save(new MatchTyp1ergebni(null,
                this.classRepository.findKlasseByName(winningTeamname),
                this.userRepository.findBetreuerByNutzername(userName),
                this.matchRepository.findById(matchId),
                Instant.now()));
        return true;
    }
}
