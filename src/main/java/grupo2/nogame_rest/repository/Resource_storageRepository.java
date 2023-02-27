package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_StorageNewDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;

public interface Resource_storageRepository extends JpaRepository<Resource_storageDb, Long>{
    Resource_StorageEditDb save(Resource_StorageEditDb resource_StorageEditDb);
    Resource_StorageNewDb save(Resource_StorageNewDb resource_StorageNewDb);
}
