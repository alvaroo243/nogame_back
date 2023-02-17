package grupo2.nogame_rest.service;

import java.util.List;
import java.util.Optional;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.model.dto.New.UserNew;

public interface UserService {
    public List<UserList> findAllUserList();
    public UserNew save(UserNew userNew);
    public Optional<UserEdit> update(UserEdit userEdit);
    public String deleteById(Long id);
    public String deleteByEmail(String email);
    public Optional<UserEdit> getUserEditById(Long id);
    public Optional<UserEdit> getUserEditByEmail(String email);
    public Optional<UserDb> getUserDbById(Long id);
    public Optional<UserDb> getUserDbByEmail(String email);
}
