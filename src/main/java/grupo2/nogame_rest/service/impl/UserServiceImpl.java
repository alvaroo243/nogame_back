package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.UserList;
import grupo2.nogame_rest.repository.UserRepository;
import grupo2.nogame_rest.service.UserService;
import grupo2.nogame_rest.service.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserList> findAllUserList() {
        return UserMapper.INSTANCE.usersToUserList(userRepository.findAll());
    }

}
