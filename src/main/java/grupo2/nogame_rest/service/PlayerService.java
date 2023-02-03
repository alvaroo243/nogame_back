package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.PlayerList;

public interface PlayerService {
    public List<PlayerList> findAllPlayerList();
    public Optional<PlayerDb> getPlayerDbById(Long id);
    public Optional<PlayerList> getPlayerListById(Long id);
}
