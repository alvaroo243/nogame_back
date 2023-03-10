package grupo2.nogame_rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.Edit.PlayerEdit;
import grupo2.nogame_rest.model.dto.Info.PlanetInfo;
import grupo2.nogame_rest.model.dto.Info.PlayerInfo;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.mapper.PlayerMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class PlayerRestController {

    private PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping("/player")
    public List<PlayerList> getPlayersList() {
        return playerService.findAllPlayerList();
    }

    @GetMapping("/player/id/{id}")
    public PlayerInfo getPlayerListById(@PathVariable("id") Long id) throws RuntimeException{
        Optional<PlayerInfo> playerInfo = playerService.getPlayerInfoById(id);
        if (playerInfo.isPresent()) {
            return playerInfo.get();
        } else {
            throw new ResourceNotFoundException("Player not found :: "+id);
        }
    }

    @GetMapping("/player/{id}/hasType")
    public boolean hasType(@PathVariable("id") Long id) throws RuntimeException{
        Optional<PlayerDb> playerDb = playerService.getPlayerDbById(id);
        if (playerDb.isPresent()) {
            if (playerDb.get().getTypeDb() != null) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new ResourceNotFoundException("Player not found :: "+id);
        }
    }

    @GetMapping("/player/{idPlayer}/type/{idType}")
    public PlayerEdit addTypePlayer(@PathVariable ("idPlayer") Long idPlayer, @PathVariable("idType") Long idType) throws RuntimeException {
        Optional<PlayerDb> playerDb = playerService.getPlayerDbById(idPlayer);
        if (playerDb.isPresent()) {
            PlayerEdit playerEdit = PlayerMapper.INSTANCE.playerDbToPlayerEdit(playerDb.get());
            playerEdit.setTypeId(idType);
            return playerService.update(playerEdit).get();
        } else {
            throw new ResourceNotFoundException("Player not found :: "+idPlayer);
        }
    }

    @GetMapping("/player/email/{email}")
    public PlayerList getPlayerDbByEmail(@PathVariable ("email") String email) {
        Optional<PlayerDb> playerDb = playerService.getPlayerDbByUserEmail(email);
        if (playerDb.isPresent()) {
            return PlayerMapper.INSTANCE.PlayerDbToPlayerList(playerDb.get());
        } else {
            throw new ResourceNotFoundException("Player not found :: "+email);
        }
    }

    @PostMapping("/player/planets")
    public Set<PlanetInfo> getPlanetsInfo(@Valid @RequestBody PlayerList playerList) {
        Optional<PlayerDb> playerDb = playerService.getPlayerDbById(playerList.getId());
        if (playerDb.isPresent()) {
            return PlayerMapper.INSTANCE.PlayerDbToPlayerList(playerDb.get()).getPlanetsInfo();
        } else {
            throw new ResourceNotFoundException("Player not found :: "+playerList.getId());
        }
    }
}