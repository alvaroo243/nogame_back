package grupo2.nogame_rest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.ResourceDb;
import grupo2.nogame_rest.model.db.StructureDb;
import grupo2.nogame_rest.model.dto.Info.PlanetInfo;
import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlanetNew;
import grupo2.nogame_rest.model.dto.New.Resource_StorageNew;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.ResourceService;
import grupo2.nogame_rest.service.Resource_storageService;
import grupo2.nogame_rest.service.StructureService;
import grupo2.nogame_rest.service.mapper.PlanetMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class PlanetRestController {
    
    private PlanetService planetService;
    private PlayerService playerService;
    private Resource_storageService resource_storageService;
    private ResourceService resourceService;
    private StructureService structureService;

    public PlanetRestController (PlanetService planetService, PlayerService playerService, Resource_storageService resource_storageService, ResourceService resourceService, StructureService structureService) {
        this.planetService = planetService;
        this.playerService = playerService;
        this.resource_storageService = resource_storageService;
        this.resourceService = resourceService;
        this.structureService = structureService;
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

    @PostMapping("/planet/explore")
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

    @GetMapping("planet/{idPlanet}/resource/{idResource}")
    public PlanetInfo addStorageToPlanet(@PathVariable("idPlanet") Long idPlanet, @PathVariable("idResource") Long idResource) throws RuntimeException{
        Optional<PlanetDb> planetDb = planetService.getPlanetDbById(idPlanet);
        if (planetDb.isPresent()) {
            Optional<ResourceDb> resourceDb = resourceService.getResourceDbById(idResource);
            Optional<StructureDb> structureDb = structureService.getStructureDbById(idResource);
            if (resourceDb.isPresent() && structureDb.isPresent()) {
                Resource_StorageNew resource_StorageNew = new Resource_StorageNew(resourceDb.get().getId(), structureDb.get().getId(), planetDb.get().getId(), 0);
                resource_storageService.save(resource_StorageNew);
                planetDb = planetService.getPlanetDbById(idPlanet);
                return PlanetMapper.INSTANCE.PlanetDbToPlanetInfo(planetDb.get());
            } else {
                throw new ResourceNotFoundException("Resource_Storage not found :: "+ idResource);
            }
        } else {
            throw new ResourceNotFoundException("Planet not found :: "+idPlanet);
        }
    }
}