package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.dto.List.Resource_storageList;
import grupo2.nogame_rest.model.dto.New.Resource_StorageNew;
import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;
import grupo2.nogame_rest.model.dto.Edit.Resource_StorageEdit;

public interface Resource_storageService {
    public List<Resource_storageList> findAllResource_storageList();
    public List<Resource_StorageEdit> findAllResource_storageEdit();
    public Optional<Resource_StorageEdit> getResource_StorageEditById(Long id);
    public Optional <Resource_storageDb> getStorageDbByID(Long id);
    public Optional<Resource_StorageEditDb> getStorageEditDbById(Long id);
    public Resource_storageDb getStorageByPlanetId(Long planetId);
    public Resource_StorageNew save(Resource_StorageNew resource_StorageNew);
    public Optional<Resource_StorageEdit> update(Resource_StorageEdit resourceStorageEdit);
}
