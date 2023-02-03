package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.UserList;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserList UserDbToUserList(UserDb userDb);
    List<UserList> usersToUserList(List<UserDb> userDb);
}
