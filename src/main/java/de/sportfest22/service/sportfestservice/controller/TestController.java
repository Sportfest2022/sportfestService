package de.sportfest22.service.sportfestservice.controller;

import de.sportfest22.service.sportfestservice.dto.ClassDto;
import de.sportfest22.service.sportfestservice.model.Klasse;
import de.sportfest22.service.sportfestservice.repository.ClassRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sportfest/classes")
@CrossOrigin("*")
public class TestController {

    private final ClassRepository classRepository;

    @Autowired
    public TestController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }



    @GetMapping("/stufe/{klassenStufeId}")
    @ApiOperation(value = "getall", notes = "Gibt eine Liste aller existierenden Städte zurück. Die DTOs sind hierbei auf für die Darstellung benötigte Informationen reduziert.")
    public List<Klasse> getAllByKlassenstufe(@PathVariable Integer klassenStufeId) {
        return classRepository.findAllByKlassenstufeId(klassenStufeId);
    }
}
