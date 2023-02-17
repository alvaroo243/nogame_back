package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.ResearchList;
import grupo2.nogame_rest.repository.ResearchRepository;
import grupo2.nogame_rest.service.ResearchService;
import grupo2.nogame_rest.service.mapper.ResearchMapper;

@Service
public class ResearchServiceImpl implements ResearchService{
    
    private final ResearchRepository researchRepository;

    public ResearchServiceImpl(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    @Override
    public List<ResearchList> findAllResearchList() {
        return ResearchMapper.INSTANCE.researchToResearchList(researchRepository.findAll());
    }
}