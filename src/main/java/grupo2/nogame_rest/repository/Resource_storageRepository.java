package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.Resource_storageDb;

public interface Resource_storageRepository extends JpaRepository<Resource_storageDb, Long>{
    
}
