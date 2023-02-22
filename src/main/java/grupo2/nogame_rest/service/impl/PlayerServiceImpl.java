package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.PlayerEditDb;
import grupo2.nogame_rest.model.db.PlayerNewDb;
import grupo2.nogame_rest.model.dto.Edit.PlayerEdit;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlayerNew;
import grupo2.nogame_rest.repository.PlayerRepository;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.mapper.PlayerMapper;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerList> findAllPlayerList() {
        return PlayerMapper.INSTANCE.playersToPlayerList(playerRepository.findAll());
    }

    @Override
    public Optional<PlayerDb> getPlayerDbById(Long id){
        return playerRepository.findById(id);
    }

    @Override
    public Optional<PlayerList> getPlayerListById(Long id){
        Optional<PlayerDb> playerDb = playerRepository.findById(id);
        if (playerDb.isPresent()) {
            return Optional.of(PlayerMapper.INSTANCE.PlayerDbToPlayerList(playerDb.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlayerEdit> getPlayerEditById(Long id){
        Optional<PlayerDb> playerDb = playerRepository.findById(id);
        if (playerDb.isPresent()) {
            return Optional.of(PlayerMapper.INSTANCE.playerDbToPlayerEdit(playerDb.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlayerEditDb> getPlayerEditDbById(Long id){
        Optional<PlayerEditDb> playerEditDb = Optional.of(PlayerMapper.INSTANCE.playerDbToPlayerEditDb(playerRepository.findById(id).get()));
        if (playerEditDb.isPresent()) {
            return playerEditDb;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlayerDb> getPlayerDbByUserEmail(String email) {
        List<PlayerDb> players = playerRepository.findAll();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUserDb().getEmail().equals(email)) {
                PlayerDb playerDb = players.get(i);
                return getPlayerDbById(playerDb.getId());
            }
        }
        return Optional.empty();
    }

    @Override
    public PlayerList getPlayerListByUserEmail(String email) {
        List<PlayerDb> players = playerRepository.findAll();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUserDb().getEmail().equals(email)) {
                PlayerDb playerDb = players.get(i);
                return PlayerMapper.INSTANCE.PlayerDbToPlayerList(playerDb);
            }
        }
        return null;
    }

    @Override
    public PlayerNew save(PlayerNew playerNew) {
        PlayerNewDb playerNewDb = playerRepository.save(PlayerMapper.INSTANCE.playerNewToPlayerNewDb(playerNew));
        return PlayerMapper.INSTANCE.playerNewDbToPlayerNew(playerNewDb);
    }

    @Override
    public Optional<PlayerEdit> update(PlayerEdit playerEdit) {
        Optional<PlayerEditDb> playerEditDb = getPlayerEditDbById(playerEdit.getId());
        if (playerEditDb.isPresent()) {
            PlayerMapper.INSTANCE.updatePlayerEditDbFromPlayerEdit(playerEdit, playerEditDb.get());
            playerRepository.save(playerEditDb.get());
            return getPlayerEditById(playerEdit.getId());
        } else {
            return Optional.empty();
        }
    }
}

