package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.dto.PlanetEdit;
import grupo2.nogame_rest.model.dto.PlanetList;

@Mapper
public interface PlanetMapper {
    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    List<PlanetList> planetsToPlanetList(List<PlanetDb> planetDb);

    PlanetList PlanetDbToPlanetList(PlanetDb planetDb);

    PlanetEdit PlanetDbToPlanetEdit(PlanetDb planetDb);

    PlanetDb PlanetEditToPLanetEditDb(PlanetEdit planetEdit);
}
