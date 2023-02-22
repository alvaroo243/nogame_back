package grupo2.nogame_rest.service.mapper;

import java.util.List;
import java.util.Set;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.db.PlanetEditDb;
import grupo2.nogame_rest.model.db.PlanetNewDb;
import grupo2.nogame_rest.model.dto.Edit.PlanetEdit;
import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.model.dto.New.PlanetNew;
import grupo2.nogame_rest.model.dto.Info.PlanetInfo;

@Mapper
public interface PlanetMapper {
    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    List<PlanetList> planetsToPlanetList(List<PlanetDb> planetDb);

    @Mapping(target = "first", expression = "java(planetDb.isFirst())")
    PlanetInfo PlanetDbToPlanetInfo(PlanetDb planetDb);

    Set<PlanetInfo> planetsDbToPlanetsInfoNombre(Set<PlanetDb> planetsDb);

    @Mapping(target = "nicknamePlayer", source = "playerDb.userDb.nickname")
    PlanetList PlanetDbToPlanetList(PlanetDb planetDb);

    @Mapping(target = "playerId", source = "playerDb.id")
    PlanetEdit PlanetDbToPlanetEdit(PlanetDb planetDb);

    @Mapping(target = "playerId", source = "playerDb.id")
    PlanetEditDb PlanetDbToPlanetEditDb(PlanetDb planetDb);

    PlanetEditDb planetEditToPlanetEditDb(PlanetEdit planetEdit);

    PlanetEdit planetEditDbToPlanetEdit(PlanetEditDb planetEditDb);

    PlanetNewDb planetNewToPlanetNewDb(PlanetNew planetNew);

    PlanetNew planetNewDbToPlanetNew(PlanetNewDb planetNewDb);

    void updatePlanetEditDbFromPlanetEdit(PlanetEdit planetEdit,@MappingTarget PlanetEditDb planetEditDb);

}
