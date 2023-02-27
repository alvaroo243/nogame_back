package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.StructureDb;
import grupo2.nogame_rest.model.dto.List.StructureList;

public interface StructureService {
    public List<StructureList> findAllStructureList();
    public Optional<StructureDb> getStructureDbById(Long id);
}
