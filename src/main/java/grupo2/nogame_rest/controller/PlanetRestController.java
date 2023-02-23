package grupo2.nogame_rest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlanetNew;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.PlayerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class PlanetRestController {
    
    private PlanetService planetService;
    private PlayerService playerService;

    public PlanetRestController (PlanetService planetService, PlayerService playerService) {
        this.planetService = planetService;
        this.playerService = playerService;
    }

    @GetMapping("/planet")
    public List<PlanetList> getPlanetsList() {
        return planetService.findAllPlanetList();
    }

    @GetMapping("/planet/{id}/isColonized")
    public boolean isColonized(@PathVariable("id") Long id) {
        if (planetService.getPlanetDbById(id).get().getPlayerDb() != null) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/planet/explore")
    public boolean explorePlanet(@Valid @RequestBody PlayerList playerList) {
        Optional<PlayerDb> playerDb = playerService.getPlayerDbById(playerList.getId());
        Set<PlanetDb> planetsPlayer = playerDb.get().getPlanetsDb();
        PlanetDb lastPlanet = null;
        for (PlanetDb planetPlayer : planetsPlayer) {
            lastPlanet = planetPlayer;
        }
        LocalDate currentDate = LocalDate.now();
        if(!currentDate.isAfter(lastPlanet.getCreated_ts())) {
            return false;
        } else {
            PlanetNew planetNew = planetService.createRandomPlanet(playerList.getId(), false);
            planetService.save(planetNew);
            return true;
        }
    }
}