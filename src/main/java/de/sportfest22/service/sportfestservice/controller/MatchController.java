package de.sportfest22.service.sportfestservice.controller;

import de.sportfest22.service.sportfestservice.dto.MatchDto;
import de.sportfest22.service.sportfestservice.model.Match;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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


    @GetMapping("all")
    public List<MatchDto> getAll() {
        List<MatchDto> convertedMatches = new ArrayList<>();
        List<Match> all = this.matchRepository.findAll();
        for (Match match : all) {
            String gameName = match.getStation().getSpiel().getName();
            convertedMatches.add(new MatchDto(
                    match.getId(),
                    match.getKlasse1(),
                    match.getKlasse2(),
                    match.getDuration(),
                    match.get_public(),
                    match.getStart().plusSeconds(60 * 60 * 2),
                    match.getStation(),
                    matchService.getMatchResultStatus(match.getId()),
                    matchService.getResponsiveUserName(match.getId()),
                    gameName,
                    match.getStation().getName()
            ));
        }
        convertedMatches.sort(Comparator.comparing(MatchDto::getStart));
        for (MatchDto match : convertedMatches) {
            System.out.println(match.getStart());
        }
        return convertedMatches;
    }

    @GetMapping("{username}")
    public List<MatchDto> getAll(@PathVariable String username) {
        List<MatchDto> convertedMatches = new ArrayList<>();
        for (Match match : matchService.getMatchesByUsernameWithoutAnswer(username)) {
            String gameName = match.getStation().getSpiel().getName();
            convertedMatches.add(new MatchDto(
                    match.getId(),
                    match.getKlasse1(),
                    match.getKlasse2(),
                    match.getDuration(),
                    match.get_public(),
                    match.getStart().plusSeconds(60 * 60 * 2),
                    match.getStation(),
                    matchService.getMatchResultStatus(match.getId()),
                    matchService.getResponsiveUserName(match.getId()),
                    gameName,
                    match.getStation().getName()
            ));
        }
        return convertedMatches;
    }

    @GetMapping("nextmatchtime/{username}")
    public String getNextMatchTime(@PathVariable String username) {
        Match closestMatch = this.matchService.getClosestMatchWithoutAnswer(username);
        if (closestMatch == null) return "ERROR";
        DateFormat df = new SimpleDateFormat("HH:mm");
        Timestamp timestamp = Timestamp.from(closestMatch.getStart());
        return df.format(timestamp);
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


    @GetMapping("/below5Min/{username}")
    public boolean below5Min(
            @PathVariable String username) {
        return this.matchService.isBelowFivMin(username);
    }
}
