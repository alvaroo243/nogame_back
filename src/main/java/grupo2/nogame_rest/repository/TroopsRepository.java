package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.TroopsDb;

public interface TroopsRepository extends JpaRepository<TroopsDb, Long>{
    
}
