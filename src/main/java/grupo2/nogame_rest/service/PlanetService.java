package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlanetEditDb;
import grupo2.nogame_rest.model.dto.Edit.PlanetEdit;
import grupo2.nogame_rest.model.dto.List.PlanetList;

public interface PlanetService {
    public List<PlanetList> findAllPlanetList();    
    public Optional<PlanetDb> getPlanetDbById(Long id);
    public Optional<PlanetEdit> getPlanetWithoutPlayer();
    public Optional<PlanetEditDb> getPlanetEditDbById(Long id);
    public Optional<PlanetEdit> getPlanetEditById(Long id);
    public Optional<PlanetEdit> update(PlanetEdit planetEdit);
}
