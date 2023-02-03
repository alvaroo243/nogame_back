package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.UserList;
import grupo2.nogame_rest.service.UserService;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
    
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserList> getUsersList() {
        return userService.findAllUserList();
    }
}
