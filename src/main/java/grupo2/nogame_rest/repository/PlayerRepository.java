package grupo2.nogame_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.PlayerEditDb;
import grupo2.nogame_rest.model.db.PlayerNewDb;

public interface PlayerRepository extends JpaRepository<PlayerDb, Long>{
    PlayerNewDb save(PlayerNewDb playerNewDb);
    PlayerEditDb save(PlayerEditDb playerEditDb);
}