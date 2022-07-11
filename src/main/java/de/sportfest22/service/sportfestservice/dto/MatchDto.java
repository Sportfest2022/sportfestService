package de.sportfest22.service.sportfestservice.dto;

import de.sportfest22.service.sportfestservice.model.Klasse;
import de.sportfest22.service.sportfestservice.model.Station;
import lombok.Data;

import java.time.Instant;

@Data
public class MatchDto {
    private final Integer id;
    private final Klasse klasse1;
    private final Klasse klasse2;
    private final Integer duration;
    private final Boolean _public;
    private final Instant start;
    private final Station station;
    private final String status;
    private final String betreuer;
    private final String gamename;
    private final String stationname;
}
