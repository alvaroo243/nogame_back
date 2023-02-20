package grupo2.nogame_rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.dto.New.PlayerNew;
import grupo2.nogame_rest.model.dto.New.UserNew;
import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.UserLogin;
import grupo2.nogame_rest.model.dto.Edit.PlanetEdit;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.service.BcriptService;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.UserService;
import grupo2.nogame_rest.service.mapper.UserMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class UserRestController {
    
    private UserService userService;
    private PlayerService playerService;
    private PlanetService planetService;
    private BcriptService bcriptService;

    public UserRestController(UserService userService, PlayerService playerService, PlanetService planetService, BcriptService bcriptService) {
        this.userService = userService;
        this.playerService = playerService;
        this.planetService = planetService;
        this.bcriptService = bcriptService;
    }

    @GetMapping("/user")
    public List<UserList> getUsersList() {
        return userService.findAllUserList();
    }

    @PostMapping("/register")
    public UserNew registerUser(@Valid @RequestBody UserNew userNew) {
        String passwordCripted = bcriptService.hashPassword(userNew.getPassword());
        userNew.setPassword(passwordCripted);
        userNew.setCreated_ts(LocalDateTime.now());
        userService.save(userNew);
        PlayerNew playerNew = new PlayerNew();
        UserNew userNewSave = UserMapper.INSTANCE.userDbToUserNew(userService.getUserDbByEmail(userNew.getEmail()).get());
        playerNew.setUserId(userNewSave.getId());
        playerService.save(playerNew);
        Optional<PlayerDb> playerSave = playerService.getPlayerDbByUserEmail(userNewSave.getEmail());
        Optional<PlanetEdit> planetEdit = planetService.getPlanetWithoutPlayer();
        planetEdit.get().setPlayerId(playerSave.get().getId());
        planetService.update(planetEdit.get());
        return userNewSave;
    }

    @PutMapping("/user/edit")
    public ResponseEntity<UserEdit> updateUser(@Valid @RequestBody UserEdit userEdit) throws RuntimeException{
        Optional<UserEdit> userEditNuevo = userService.update(userEdit);
        if (userEditNuevo.isPresent()) {
            return ResponseEntity.ok().body(userEditNuevo.get());
        } else {
            throw new ResourceNotFoundException("User not found on :: "+userEditNuevo.get().getId());
        }
    }

    @DeleteMapping("/user/delete/{email}")
    public String deleteByEmail(@PathVariable(value = "email") String email) {
        return userService.deleteByEmail(email);
    } 

    @PostMapping("/login")
    public Optional<UserDb> loginUser(@Valid @RequestBody UserLogin userLogin) {
        Optional<UserEdit> userEditExistente = userService.getUserEditByEmail(userLogin.getEmail());
        if(userEditExistente.isPresent()) {
            if(bcriptService.verifyPassword(userLogin.getPassword(), userEditExistente.get().getPassword())){
                return Optional.of(UserMapper.INSTANCE.userEditToUserDb(userEditExistente.get()));
            }
        }
        return Optional.empty();
    }

}
