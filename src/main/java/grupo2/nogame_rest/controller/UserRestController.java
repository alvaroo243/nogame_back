package grupo2.nogame_rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.model.dto.Info.UserInfo;
import grupo2.nogame_rest.service.UserService;
import grupo2.nogame_rest.service.mapper.UserMapper;

@RestController
@RequestMapping("/api/")
public class UserRestController {
    
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserList> getUsersList() {
        return userService.findAllUserList();
    }

    @PostMapping("/register")
    public UserEdit registerUser(@Valid @RequestBody UserEdit userEdit) {
        userEdit.setCreated_ts(new Date());
        return userService.save(userEdit);
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<UserEdit> updateUser(@PathVariable(value = "id") Integer id,@Valid @RequestBody UserEdit userEdit) throws RuntimeException{
        Optional<UserEdit> userEditNuevo = userService.update(userEdit);
        if (userEditNuevo.isPresent()) {
            return ResponseEntity.ok().body(userEditNuevo.get());
        } else {
            throw new ResourceNotFoundException("User not found on :: "+id);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    } 

    @PostMapping("/login")
    public Optional<UserDb> loginUser(@Valid @RequestBody UserInfo userLogin) {
        Optional<UserEdit> userEditExistente = userService.getUserEditByEmail(userLogin.getEmail());
        if(userEditExistente.isPresent()) {
            if(userEditExistente.get().getPassword().equals(userLogin.getPassword())){
                return Optional.of(UserMapper.INSTANCE.userEditToUserDb(userEditExistente.get()));
            }
        }
        return Optional.empty();
    }


}
