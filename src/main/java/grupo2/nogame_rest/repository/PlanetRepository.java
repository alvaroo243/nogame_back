package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlanetEditDb;

public interface PlanetRepository extends JpaRepository<PlanetDb, Long>{
    PlanetEditDb save(PlanetEditDb planetEditDb);
}