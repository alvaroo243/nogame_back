package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.dto.PlayerList;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "nicknameUser", source = "userDb.nickname")
    @Mapping(target = "nameType", source = "typeDb.name")
    PlayerList PlayerDbToPlayerList(PlayerDb playerDb);
    
    List<PlayerList> playersToPlayerList(List<PlayerDb> playerDb);
}
