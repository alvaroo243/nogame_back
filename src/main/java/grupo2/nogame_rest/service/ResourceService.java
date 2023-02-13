package grupo2.nogame_rest.service;

import java.util.List;

import grupo2.nogame_rest.model.dto.List.ResourceList;

public interface ResourceService {
    public List<ResourceList> findAllResourceList();
}
