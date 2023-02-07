package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.service.PlanetService;

@RestController
@RequestMapping("/api/v1/")
public class PlanetRestController {
    
    private PlanetService planetService;

    public PlanetRestController (PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planet")
    public List<PlanetList> getPlanetsList() {
        return planetService.findAllPlanetList();
    }

    @GetMapping("/planet/{id}/isColonized")
    public boolean isColonized(@PathVariable("id") Long id) {
        if (planetService.getPlayerDbById(id).get().getPlayerDb() != null) {
            return true;
        } else {
            return false;
        }
    }
}