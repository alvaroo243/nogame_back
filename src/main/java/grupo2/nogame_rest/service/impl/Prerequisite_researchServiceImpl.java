package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.Prerequisite_researchList;
import grupo2.nogame_rest.repository.Prerequisite_researchRepository;
import grupo2.nogame_rest.service.Prerequisite_researchService;
import grupo2.nogame_rest.service.mapper.Prerequisite_researchMapper;

@Service
public class Prerequisite_researchServiceImpl implements Prerequisite_researchService{
    
    private final Prerequisite_researchRepository prerequisite_researchRepository;

    public Prerequisite_researchServiceImpl(Prerequisite_researchRepository prerequisite_researchRepository) {
        this.prerequisite_researchRepository = prerequisite_researchRepository;
    }

    public List<Prerequisite_researchList> findAllPrerequisite_researchList() {
        return Prerequisite_researchMapper.INSTANCE.prerequisite_researchsToPrerequisite_researchList(prerequisite_researchRepository.findAll());
    }
}
