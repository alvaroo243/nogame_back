package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.dto.New.UserNew;
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

    @Override
    public List<UserList> findAllUserList() {
        return UserMapper.INSTANCE.usersToUserList(userRepository.findAll());
    }

    @Override
    public UserNew save(UserNew userNew) {
        UserDb userDb = userRepository.save(UserMapper.INSTANCE.userNewToUserDb(userNew));
        return UserMapper.INSTANCE.userDbToUserNew(userDb);
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
    public Optional<UserDb> getUserDbById(Long id) {
        Optional<UserDb> userDb=userRepository.findById(id);
        if (userDb.isPresent())
            return userDb;
        else 
            return Optional.empty();
    }

    @Override
    public Optional<UserDb> getUserDbByEmail(String email) {
        Optional<UserDb> userDb=userRepository.findByEmail(email);
        if (userDb.isPresent())
            return userDb;
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
    public String deleteByEmail(String email) {
        return userRepository.findByEmail(email)
        .map(a-> {
                userRepository.deleteByEmail(email);
                return "Deleted";
            }).orElse("Not Deleted");
    }

    @Override
    public Optional<UserEdit> update(UserEdit userEdit) {
        Optional<UserDb> userDb = getUserDbById(userEdit.getId());
        if (userDb.isPresent()) {
            userEdit.setCreated_ts(userDb.get().getCreated_ts());
            UserMapper.INSTANCE.updateUserDbFromUserEdit(userEdit, userDb.get());
            userRepository.save(userDb.get());
            return getUserEditById(userEdit.getId());
        } else {
            return Optional.empty();
        }
    }

}
