package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.dto.PlanetList;
import grupo2.nogame_rest.repository.PlanetRepository;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.mapper.PlanetMapper;

@Service
public class PlanetServiceImpl implements PlanetService{
    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<PlanetList> findAllPlanetList() {
        return PlanetMapper.INSTANCE.planetsToPlanetList(planetRepository.findAll());
    }

    public Optional<PlanetDb> getPlayerDbById(Long id){
        return planetRepository.findById(id);
    }
}
