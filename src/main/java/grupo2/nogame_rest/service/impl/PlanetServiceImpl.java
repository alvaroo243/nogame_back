package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlanetEditDb;
import grupo2.nogame_rest.model.dto.Edit.PlanetEdit;
import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.repository.PlanetRepository;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.mapper.PlanetMapper;

@Service
public class PlanetServiceImpl implements PlanetService{
    private final PlanetRepository planetRepository;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public List<PlanetList> findAllPlanetList() {
        return PlanetMapper.INSTANCE.planetsToPlanetList(planetRepository.findAll());
    }

    @Override
    public Optional<PlanetDb> getPlanetDbById(Long id){
        return planetRepository.findById(id);
    }

    @Override
    public Optional<PlanetEdit> getPlanetEditById(Long id) {
        Optional<PlanetEdit> planetEdit = Optional.of(PlanetMapper.INSTANCE.PlanetDbToPlanetEdit(planetRepository.findById(id).get()));
        if (planetEdit.isPresent()) {
            return planetEdit;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlanetEditDb> getPlanetEditDbById(Long id) {
        Optional<PlanetEditDb> planetEditDb = Optional.of(PlanetMapper.INSTANCE.PlanetDbToPlanetEditDb(planetRepository.findById(id).get()));
        if (planetEditDb.isPresent()) {
            return planetEditDb;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlanetEdit> getPlanetWithoutPlayer() {
        List<PlanetDb> planets = planetRepository.findAll();
        for (int i = 0; i < planets.size(); i++) {
            if (planets.get(i).getPlayerDb() == null) {
                return Optional.of(PlanetMapper.INSTANCE.PlanetDbToPlanetEdit(planets.get(i)));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlanetEdit> update(PlanetEdit planetEdit) {
        Optional<PlanetEditDb> planetEditDb = getPlanetEditDbById(planetEdit.getId());
        if (planetEditDb.isPresent()) {
            PlanetMapper.INSTANCE.updatePlanetEditDbFromPlanetEdit(planetEdit, planetEditDb.get());
            planetRepository.save(planetEditDb.get());
            return getPlanetEditById(planetEditDb.get().getId());
        } else {
            return Optional.empty();
        }
    }
}
