package de.sportfest22.service.sportfestservice.controller;

import de.sportfest22.service.sportfestservice.dto.UserData;
import de.sportfest22.service.sportfestservice.repository.UserRepository;
import de.sportfest22.service.sportfestservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sportfest/login")
@CrossOrigin("*")
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public LoginController(UserRepository UserRepository, UserService userService) {
        this.userRepository = UserRepository;
        this.userService = userService;
    }


    @GetMapping("/exists/{name}/{password}")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public boolean getExistence(@PathVariable String name, @PathVariable String password) {
        return userRepository.existsBetreuerByNutzernameAndPasswort(name, password);
    }


    @GetMapping("/{name}/{password}")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public UserData getLogin(@PathVariable String name, @PathVariable String password) {
        return userService.getUserData(name, password);
    }

    @GetMapping("")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public List<UserData> getAll() {
        List<UserData> convertedUsers = new ArrayList<>();
        userRepository.findAll().forEach(betreuer -> convertedUsers.add(new UserData(
                betreuer.getNutzername(),
                betreuer.getPasswort(),
                betreuer.getName(),
                betreuer.getNachname(),
                userRepository.existsBetreuerByNutzernameAndPasswort(betreuer.getNutzername(),
                        betreuer.getPasswort()),
                betreuer.getAdmin(),
                betreuer.getStation().getId(),
                betreuer.getStation().getSpiel().getGametype()
        )));
        return convertedUsers;
    }
}
