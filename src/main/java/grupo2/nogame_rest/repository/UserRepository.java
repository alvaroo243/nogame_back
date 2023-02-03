package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Long>{

    
}