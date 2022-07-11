package de.sportfest22.service.sportfestservice.controller;

import de.sportfest22.service.sportfestservice.dto.MatchDto;
import de.sportfest22.service.sportfestservice.dto.UserData;
import de.sportfest22.service.sportfestservice.model.Klasse;
import de.sportfest22.service.sportfestservice.model.Match;
import de.sportfest22.service.sportfestservice.repository.ClassRepository;
import de.sportfest22.service.sportfestservice.repository.MatchRepository;
import de.sportfest22.service.sportfestservice.repository.UserRepository;
import de.sportfest22.service.sportfestservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sportfest/class")
@CrossOrigin("*")
public class ClassController {

    private final ClassRepository classRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public ClassController(ClassRepository classRepository, MatchRepository matchRepository) {
        this.classRepository = classRepository;
        this.matchRepository = matchRepository;
    }


    @GetMapping("/matches/{name}")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public List<MatchDto> getMatchesByClass(@PathVariable String name) {
        Klasse klasse = classRepository.findKlasseByName(name);
        if (klasse == null) return new ArrayList<>();
        List<Match> matchesByKlasse1 = matchRepository.findMatchesByKlasse1(klasse);
        matchesByKlasse1.addAll(matchRepository.findMatchesByKlasse2(klasse));

        List<MatchDto> convertedMatches = new ArrayList<>();
        for (Match match : matchesByKlasse1) {
            String gameName = match.getStation().getSpiel().getName();
            convertedMatches.add(new MatchDto(
                    match.getId(),
                    match.getKlasse1(),
                    match.getKlasse2(),
                    match.getDuration(),
                    match.get_public(),
                    match.getStart(),
                    match.getStation(),
                    "TBD",
                    "ERR",
                    gameName,
                    match.getStation().getName()
            ));
        }

        return convertedMatches;
    }
}
