package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.dto.List.PlanetList;

public interface PlanetService {
    public List<PlanetList> findAllPlanetList();    

    public Optional<PlanetDb> getPlayerDbById(Long id);
}
