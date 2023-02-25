package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;
import grupo2.nogame_rest.model.dto.Edit.Resource_StorageEdit;
import grupo2.nogame_rest.model.dto.List.Resource_storageList;


public interface Resource_storageService {
    public List<Resource_storageList> findAllResource_storageList();
    public Optional<Resource_StorageEdit> getResource_StorageEditById(Long id);
    public Optional <Resource_storageDb> getStorageDbByID(Long id);
    public Optional<Resource_StorageEditDb> getStorageEditDbById(Long id);
    public Optional<Resource_StorageEdit> update(Resource_StorageEdit ResourceStorageEdit);
    public Optional<Resource_StorageEdit> updateResource_storageEdit(Resource_StorageEdit resourceStorageEdit);
    public List<Resource_StorageEdit> findAllResource_storageEdit();
    public Resource_storageDb getStorageByPlanetId(Long planetId);
    
  
}
