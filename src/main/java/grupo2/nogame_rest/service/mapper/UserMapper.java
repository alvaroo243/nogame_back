package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.UserDb;
import grupo2.nogame_rest.model.db.UserEditDb;
import grupo2.nogame_rest.model.dto.List.UserList;
import grupo2.nogame_rest.model.dto.Edit.UserEdit;
import grupo2.nogame_rest.model.dto.Info.UserInfo;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserList UserDbToUserList(UserDb userDb);
    List<UserList> usersToUserList(List<UserDb> usersDb);

    UserInfo userDbToUserInfo(UserDb userDb);

    UserEdit userDbToUserEdit(UserDb userDb);

    UserEditDb UserDbToUserEditDb(UserDb userDb);

	void updateUserEditDbFromUserEdit(UserEdit userEdit,@MappingTarget UserEditDb userEditDb);
}
