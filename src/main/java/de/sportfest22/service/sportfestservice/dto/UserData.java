package de.sportfest22.service.sportfestservice.dto;

import lombok.Data;

@Data
public class UserData {
    private final String name;
    private final String password;

    private final String vorname;
    private final String lastname;

    private final boolean existing;
    private final boolean isAdmin;

    private final Integer stationId;
    private final Integer gameType;
}
