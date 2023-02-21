package grupo2.nogame_rest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.security.entity.RolDb;
import grupo2.nogame_rest.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);
}
