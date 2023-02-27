package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.ResourceDb;
import grupo2.nogame_rest.model.dto.List.ResourceList;

public interface ResourceService {
    public List<ResourceList> findAllResourceList();
    public Optional<ResourceDb> getResourceDbById(Long id);
}
