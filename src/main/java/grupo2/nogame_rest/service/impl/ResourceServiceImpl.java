package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.ResourceList;
import grupo2.nogame_rest.repository.ResourceRepository;
import grupo2.nogame_rest.service.ResourceService;
import grupo2.nogame_rest.service.mapper.ResourceMapper;

@Service
public class ResourceServiceImpl implements ResourceService{
    
    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceList> findAllResourceList() {
        return ResourceMapper.INSTANCE.resourcesToResourceList(resourceRepository.findAll());
    }
}
