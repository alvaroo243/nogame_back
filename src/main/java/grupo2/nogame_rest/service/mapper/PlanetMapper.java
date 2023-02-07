package grupo2.nogame_rest.service.mapper;

import java.util.List;
import java.util.Set;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.PlanetDb;
import grupo2.nogame_rest.model.dto.Edit.PlanetEdit;
import grupo2.nogame_rest.model.dto.List.PlanetList;
import grupo2.nogame_rest.model.dto.Info.PlanetInfoNombre;

@Mapper
public interface PlanetMapper {
    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    List<PlanetList> planetsToPlanetList(List<PlanetDb> planetDb);

    PlanetList PlanetDbToPlanetList(PlanetDb planetDb);

    PlanetEdit PlanetDbToPlanetEdit(PlanetDb planetDb);

    Set<PlanetInfoNombre> planetsDbToPlanetsInfoNombre(Set<PlanetDb> planetsDb);
}
