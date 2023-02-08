package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserList UserDbToUserList(UserDb userDb);
    List<UserList> usersToUserList(List<UserDb> userDb);

    UserDb userEditToUserDb(UserEdit userEdit);
    UserEdit userDbToUserEdit(UserDb userDb);
}
