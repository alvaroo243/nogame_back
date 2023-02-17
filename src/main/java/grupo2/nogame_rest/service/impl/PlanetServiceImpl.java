package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.PlanetDb;
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
    public Optional<PlanetDb> getPlayerDbById(Long id){
        return planetRepository.findById(id);
    }
}
