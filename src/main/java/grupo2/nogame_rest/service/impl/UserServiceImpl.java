package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.repository.UserRepository;
import grupo2.nogame_rest.service.UserService;
import grupo2.nogame_rest.service.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserList> findAllUserList() {
        return UserMapper.INSTANCE.usersToUserList(userRepository.findAll());
    }

    @Override
    public UserEdit save(UserEdit userEdit) {
        UserDb userDb = userRepository.save(UserMapper.INSTANCE.userEditToUserDb(userEdit));
        return UserMapper.INSTANCE.userDbToUserEdit(userDb);
    }

    @Override
    public Optional<UserEdit> getUserEditById(Long id) {
        Optional<UserDb> userDb=userRepository.findById(id);
        if (userDb.isPresent())
            return Optional.of(UserMapper.INSTANCE.userDbToUserEdit(userDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public Optional<UserEdit> getUserEditByEmail(String email) {
        Optional<UserDb> userDb=userRepository.findByEmail(email);
        if (userDb.isPresent())
            return Optional.of(UserMapper.INSTANCE.userDbToUserEdit(userDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public String deleteById(Long id) {
        return userRepository.findById(id)
        .map(a-> {
                userRepository.deleteById(id);
                return "Deleted";
            }).orElse("Not Deleted");
    }

    @Override
    public Optional<UserEdit> update(UserEdit userEdit) {
        Optional<UserEdit> userEditExistente = getUserEditById(userEdit.getId());
        if (userEditExistente.isPresent()) {
            userEdit.setCreated_ts(userEditExistente.get().getCreated_ts());
            deleteById(userEdit.getId());
            save(userEdit);
            return getUserEditById(userEdit.getId());
        } else {
            return Optional.empty();
        }
    }

}
