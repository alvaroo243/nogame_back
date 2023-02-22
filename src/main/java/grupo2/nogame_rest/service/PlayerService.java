package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.PlayerEditDb;
import grupo2.nogame_rest.model.dto.Edit.PlayerEdit;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlayerNew;

public interface PlayerService {
    public List<PlayerList> findAllPlayerList();
    public Optional<PlayerDb> getPlayerDbById(Long id);
    public Optional<PlayerList> getPlayerListById(Long id);
    public Optional<PlayerEdit> getPlayerEditById(Long id);
    public Optional<PlayerEditDb> getPlayerEditDbById(Long id);
    public Optional<PlayerDb> getPlayerDbByUserEmail(String email);
    public PlayerList getPlayerListByUserEmail(String email);
    public PlayerNew save(PlayerNew playerNew);
    public Optional<PlayerEdit> update(PlayerEdit playerEdit);
}
