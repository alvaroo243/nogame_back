package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.Resource_storageList;
import grupo2.nogame_rest.repository.Resource_storageRepository;
import grupo2.nogame_rest.service.Resource_storageService;
import grupo2.nogame_rest.service.mapper.Resource_storageMapper;

@Service
public class Resource_storageServiceImpl implements Resource_storageService{
    
    private final Resource_storageRepository resource_storageRepository;

    public Resource_storageServiceImpl(Resource_storageRepository resource_storageRepository) {
        this.resource_storageRepository = resource_storageRepository;
    }

    @Override
    public List<Resource_storageList> findAllResource_storageList() {
        return Resource_storageMapper.INSTANCE.resources_storageToResource_storageList(resource_storageRepository.findAll());
    }
}
