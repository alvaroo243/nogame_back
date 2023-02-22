package grupo2.nogame_rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.exception.ResourceNotFoundException;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

}
