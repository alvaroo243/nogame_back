package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.repository.PlayerRepository;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.mapper.PlayerMapper;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerList> findAllPlayerList() {
        return PlayerMapper.INSTANCE.playersToPlayerList(playerRepository.findAll());
    }

    public Optional<PlayerDb> getPlayerDbById(Long id){
        return playerRepository.findById(id);
    }

    public Optional<PlayerList> getPlayerListById(Long id){
        Optional<PlayerDb> playerDb = playerRepository.findById(id);
        if (playerDb.isPresent()) {
            return Optional.of(PlayerMapper.INSTANCE.PlayerDbToPlayerList(playerDb.get()));
        } else {
            return Optional.empty();
        }
    }
}

