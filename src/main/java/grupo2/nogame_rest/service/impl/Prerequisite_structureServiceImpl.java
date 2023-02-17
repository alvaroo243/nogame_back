package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.Prerequisite_structureList;
import grupo2.nogame_rest.repository.Prerequisite_structureRepository;
import grupo2.nogame_rest.service.Prerequisite_structureService;
import grupo2.nogame_rest.service.mapper.Prerequisite_structureMapper;

@Service
public class Prerequisite_structureServiceImpl implements Prerequisite_structureService{
    
    private final Prerequisite_structureRepository prerequisite_structureRepository;

    public Prerequisite_structureServiceImpl(Prerequisite_structureRepository prerequisite_structureRepository) {
        this.prerequisite_structureRepository = prerequisite_structureRepository;
    }

    @Override
    public List<Prerequisite_structureList> findAllPrerequisite_structureList() {
        return Prerequisite_structureMapper.INSTANCE.prerequisite_structuresToPrerequisite_structureList(prerequisite_structureRepository.findAll());
    }
}
