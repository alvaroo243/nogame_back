package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.PlayerDb;
import grupo2.nogame_rest.model.db.PlayerNewDb;
import grupo2.nogame_rest.model.db.PlayerEditDb;
import grupo2.nogame_rest.model.dto.Edit.PlayerEdit;
import grupo2.nogame_rest.model.dto.Info.PlayerInfo;
import grupo2.nogame_rest.model.dto.List.PlayerList;
import grupo2.nogame_rest.model.dto.New.PlayerNew;

@Mapper(uses = PlanetMapper.class)
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "userInfo", source = "userDb")
    @Mapping(target = "nameType", source = "typeDb.name")
    @Mapping(target = "planetsInfo", source = "planetsDb")
    PlayerList PlayerDbToPlayerList(PlayerDb playerDb);

    @Mapping(target = "typeId", source = "typeDb.id")
    @Mapping(target = "userId", source = "userDb.id")
    PlayerEdit playerDbToPlayerEdit(PlayerDb playerDb);

    @Mapping(target = "typeId", source = "typeDb.id")
    @Mapping(target = "userId", source = "userDb.id")
    PlayerEditDb playerDbToPlayerEditDb(PlayerDb playerDb);
    
    List<PlayerList> playersToPlayerList(List<PlayerDb> playersDb);

    PlayerEdit playerEditDbToPlayerEdit(PlayerEditDb playerEditDb);

    PlayerEditDb playerEditToPlayerEditDb(PlayerEdit playerEdit);

    PlayerNew playerNewDbToPlayerNew(PlayerNewDb playerDb);

    PlayerNewDb playerNewToPlayerNewDb(PlayerNew playerNew);

    @Mapping(target = "email", source = "userDb.email")
    @Mapping(target = "nameType", source = "typeDb.name")
    PlayerInfo PlayerDbToPlayerInfo(PlayerDb playerDb);

    void updatePlayerEditDbFromPlayerEdit(PlayerEdit playerEdit,@MappingTarget PlayerEditDb playerEditDb);
}

