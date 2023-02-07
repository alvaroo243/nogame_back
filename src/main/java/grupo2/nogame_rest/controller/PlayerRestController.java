package grupo2.nogame_rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.service.PlayerService;

@RestController
@RequestMapping("/api/v1/")
public class PlayerRestController {

    private PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping("/player")
    public List<PlayerList> getPlayersList() {
        return playerService.findAllPlayerList();
    }

    @GetMapping("/player/{id}")
    public PlayerList getPlayerListById(@PathVariable("id") Long id) throws RuntimeException{
        Optional<PlayerList> playerList = playerService.getPlayerListById(id);
        if (playerList.isPresent()) {
            return playerList.get();
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
}