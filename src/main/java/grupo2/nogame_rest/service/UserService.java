package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;

public interface UserService {
    public List<UserList> findAllUserList();
    public UserEdit save(UserEdit userEdit);
    public Optional<UserEdit> update(UserEdit userEdit);
    public Optional<UserEdit> getUserEditById(Long id);
    public String deleteById(Long id);
    public Optional<UserEdit> getUserEditByEmail(String email);
    public Optional<UserDb> getUserDbById(Long id);
}
