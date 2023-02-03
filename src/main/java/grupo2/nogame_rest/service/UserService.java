package grupo2.nogame_rest.service;

import java.util.List;

import grupo2.nogame_rest.model.dto.UserList;

public interface UserService {
    public List<UserList> findAllUserList();
}
