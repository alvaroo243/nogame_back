package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.TroopsDb;
import grupo2.nogame_rest.model.dto.List.TroopsList;

@Mapper
public interface TroopsMapper {
    TroopsMapper INSTANCE = Mappers.getMapper(TroopsMapper.class);

    @Mapping(target = "isSpecial", expression = "java(troopsDb.isSpecial())")
    TroopsList TroopsDbToTroopsList(TroopsDb troopsDb);
    List<TroopsList> troopsToTroopsList(List<TroopsDb> troopsDb);
}
