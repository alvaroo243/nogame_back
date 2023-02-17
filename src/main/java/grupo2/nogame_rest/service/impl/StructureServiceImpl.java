package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.StructureList;
import grupo2.nogame_rest.repository.StructureRepository;
import grupo2.nogame_rest.service.StructureService;
import grupo2.nogame_rest.service.mapper.StructureMapper;

@Service
public class StructureServiceImpl implements StructureService{
    
    private final StructureRepository structureRepository;

    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    @Override
    public List<StructureList> findAllStructureList() {
        return StructureMapper.INSTANCE.structuresToStructureList(structureRepository.findAll());
    }
}