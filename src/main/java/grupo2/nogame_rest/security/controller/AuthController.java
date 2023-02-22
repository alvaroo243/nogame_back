package grupo2.nogame_rest.security.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlanetNew;
import grupo2.nogame_rest.model.dto.New.PlayerNew;
import grupo2.nogame_rest.model.dto.New.UserNew;
import grupo2.nogame_rest.security.dto.JwtDto;
import grupo2.nogame_rest.security.dto.Mensaje;
import grupo2.nogame_rest.security.entity.RolDb;
import grupo2.nogame_rest.security.entity.UserLogin;
import grupo2.nogame_rest.security.enums.RolNombre;
import grupo2.nogame_rest.security.jwt.JwtProvider;
import grupo2.nogame_rest.security.service.RolService;
import grupo2.nogame_rest.service.PlanetService;
import grupo2.nogame_rest.service.PlayerService;
import grupo2.nogame_rest.service.UserService;
import grupo2.nogame_rest.service.mapper.UserMapper;

@RestController
@RequestMapping("/auth")
@CrossOrigin //Al no poner nada más permitimos acceder desde cualquier origen
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PlayerService playerService;

    @Autowired
    PlanetService planetService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> nuevo(@Valid @RequestBody UserNew userNew, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos o email inválido"));
        }
        if (userService.existsByNickname(userNew.getNickname())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El nickname del usuario ya existe"));
        }
        if (userService.existsByEmail(userNew.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El email del usuario ya existe"));
        }
        UserDb userDb = 
            new UserDb(userNew.getNickname(), userNew.getEmail(),
                passwordEncoder.encode(userNew.getPassword()), LocalDateTime.now());
        Set<RolDb> rolesDb = new HashSet<>();
        rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (userNew.getRoles().contains("admin")) {
            rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        userDb.setRoles(rolesDb);
        userService.save(userDb);

        PlayerNew playerNew = new PlayerNew();
        UserDb userDbNew = userService.getUserDbByEmail(userNew.getEmail()).get();
        playerNew.setUserId(userDbNew.getId());
        playerService.save(playerNew);

        Optional<PlayerDb> playerSave = playerService.getPlayerDbByUserEmail(userDbNew.getEmail());
        PlanetNew planetNew = planetService.createRandomPlanet(playerSave.get().getId(), true);
        planetService.save(planetNew);

        PlanetDb planetDbNew = planetService.getPlanetByPlayerId(playerSave.get().getId());
        UserEdit userEdit = UserMapper.INSTANCE.userDbToUserEdit(userDbNew);
        userEdit.setFirstPlanet(planetDbNew.getId());
        userService.update(userEdit);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario y Jugador creados"));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos"));
        }
        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword())
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        PlayerList playerList = playerService.getPlayerListByUserEmail(loginUsuario.getEmail());
        JwtDto jwtDto = new JwtDto(jwt, playerList, userDetails.getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }

}
