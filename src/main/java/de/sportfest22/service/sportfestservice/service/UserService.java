package de.sportfest22.service.sportfestservice.service;

import de.sportfest22.service.sportfestservice.dto.UserData;
import de.sportfest22.service.sportfestservice.model.Betreuer;
import de.sportfest22.service.sportfestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserData getUserData(String name, String password) {
        Betreuer betreuer = userRepository.findBetreuerByNutzernameAndPasswort(name, password);
        if (betreuer == null) {
            return new UserData(
                    "undefined",
                    "undefined",
                    "undefined",
                    "undefined",
                    false,
                    false,
                    -1,
                    -1
            );
        }
        return new UserData(
                name,
                password,
                betreuer.getName(),
                betreuer.getNachname(),
                userRepository.existsBetreuerByNutzernameAndPasswort(name, password),
                betreuer.getAdmin(),
                betreuer.getStation().getId(),
                betreuer.getStation().getSpiel().getGametype()
        );
    }

}
